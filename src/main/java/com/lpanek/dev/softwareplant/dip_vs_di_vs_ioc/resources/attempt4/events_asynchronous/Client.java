package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.event.Event;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.event.EventPublisher;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.event.EventStore;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.event.EventStoreAppender;
import java.time.DayOfWeek;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Client {

	public static void main(String[] args) {
		EventStore eventStore = new EventStore();
		EventStoreAppender eventStoreAppender = new EventStoreAppender(eventStore);

		EventPublisher eventPublisher = EventPublisher.instance();
		eventPublisher.reset();
		eventPublisher.subscribe(Event.class, eventStoreAppender);

		ResourceFactory resourceFactory = new ResourceFactory();
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);

		ResourceId resourceId = new ResourceId("john.doe");
		Capacity newCapacity = new Capacity(8, TimeUnit.HOURS, DayOfWeek.MONDAY);

		Resource resource = resourceRepository.get(resourceId);
		resource.changeCapacityTo(newCapacity);
		resourceRepository.save(resource);

		System.out.println(resource);

		TeamService teamService = new TeamService();
		TaskService taskService = new TaskService();

		Runnable processorOfResourceCapacityChangedEvent = () -> {
			List<ResourceCapacityChangedEvent> events = eventStore.getNotYetProcessedEvents(ResourceCapacityChangedEvent.class);
			if (events.isEmpty()) {
				return;
			}

			for (int i = 0; i < events.size(); ++i) {
				ResourceCapacityChangedEvent event = events.get(i);
				try {
					taskService.handleEvent(event);
					teamService.handleEvent(event);
				} catch (RuntimeException e) {
					if (i > 0) {
						ResourceCapacityChangedEvent lastSuccessfullyProcessedEvent = events.get(i - 1);
						eventStore.markEventsUntilGivenAsProcessed(lastSuccessfullyProcessedEvent);
					}
					return;
				}
			}
			eventStore.markEventsUntilGivenAsProcessed(events.get(events.size() - 1));
		};
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
		executorService.scheduleWithFixedDelay(processorOfResourceCapacityChangedEvent, 5, 5, TimeUnit.SECONDS);
	}

}
