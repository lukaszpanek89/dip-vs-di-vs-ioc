package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.Resource;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.ResourceCapacityChangedEventProcessor;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.ResourceFactory;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.ResourceId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.ResourceRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.TaskService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.TeamService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.event.Event;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.event.EventPublisher;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.event.EventStore;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.event.EventStoreAppender;
import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.date;
import static java.util.concurrent.TimeUnit.HOURS;

public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		TeamService teamService = new TeamService();
		TaskService taskService = new TaskService();
		EventPublisher eventPublisher = new EventPublisher();
		ResourceFactory resourceFactory = new ResourceFactory(eventPublisher);
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);

		EventStore eventStore = new EventStore();
		EventStoreAppender eventStoreAppender = new EventStoreAppender(eventStore);
		eventPublisher.subscribe(Event.class, eventStoreAppender);

		ResourceCapacityChangedEventProcessor eventProcessor = new ResourceCapacityChangedEventProcessor(eventStore, teamService, taskService);
		ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
		threadPool.scheduleWithFixedDelay(eventProcessor::processEvents, 10, 5, TimeUnit.SECONDS);

		// ===== 2. USE CASE =====
		ResourceId resourceId = new ResourceId("john.doe");
		LocalDate date = date("2019-02-25");
		Capacity newCapacity = new Capacity(8, HOURS);

		Resource resource = resourceRepository.get(resourceId);
		resource.changeCapacityOnDate(date, newCapacity);
		resourceRepository.save(resource);
	}

}
