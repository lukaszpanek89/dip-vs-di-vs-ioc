package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous;

import com.google.common.collect.Lists;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.Resource;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.ResourceCapacityChangedEvent;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.ResourceCapacityChangedEventAppender;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.ResourceCapacityChangedEventProcessor;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.ResourceFactory;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.ResourceId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.ResourceRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.TaskService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.TeamService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.event.EventPublisher;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.event.EventStore;
import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.date;
import static java.util.concurrent.TimeUnit.HOURS;

/**
 * <b>PROBLEM:</b><br>
 * How to notify dependent objects on changes in given object (i.e., in its state)?
 * <br><br>
 * <b>SOLUTION:</b>
 * <br>
 * This example improves solution described in
 * {@link com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous.Application}.
 * <br><br>
 * In this example, changed object ({@link Resource}) still indirectly notifies dependent objects ({@link TeamService} and {@link TaskService}) via
 * {@link ResourceCapacityChangedEvent} event, but this time this event is passed to dependent objects asynchronously.
 */
public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		TeamService teamService = new TeamService();
		TaskService taskService = new TaskService();
		EventPublisher eventPublisher = new EventPublisher();
		ResourceFactory resourceFactory = new ResourceFactory(eventPublisher);
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);

		EventStore eventStore = new EventStore();
		ResourceCapacityChangedEventAppender eventAppender = new ResourceCapacityChangedEventAppender(eventStore);
		eventPublisher.subscribe(ResourceCapacityChangedEvent.class, eventAppender);

		ResourceCapacityChangedEventProcessor eventProcessor
				= new ResourceCapacityChangedEventProcessor(eventStore, Lists.newArrayList(teamService, taskService));
		ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);
		threadPool.scheduleWithFixedDelay(eventProcessor::processEvents, 10, 5, TimeUnit.SECONDS);

		// ===== 2. USE CASE =====
		ResourceId resourceId = new ResourceId("john.doe");
		LocalDate date = date("2019-02-25");
		Capacity newCapacity = new Capacity(8, HOURS);

		Resource resource = resourceRepository.get(resourceId);
		resource.changeCapacityOn(date, newCapacity);
		resourceRepository.save(resource);
	}

}
