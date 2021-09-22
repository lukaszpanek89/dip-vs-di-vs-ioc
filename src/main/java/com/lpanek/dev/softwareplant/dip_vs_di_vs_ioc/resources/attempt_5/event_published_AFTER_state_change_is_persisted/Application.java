package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted;

import com.google.common.collect.Lists;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.Resource;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.ResourceCapacityChangedEvent;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.ResourceCapacityChangedEventAppender;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.ResourceCapacityChangedEventProcessor;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.ResourceFactory;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.ResourceId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.ResourceRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.TaskService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.TeamService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.event.EventPublisher;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.event.EventStore;
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
 * {@link com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.Application}.
 * <br>
 * In this example, {@link Resource} no longer publishes its event {@link ResourceCapacityChangedEvent} by itself. Instead it only creates this event and then
 * lets {@link ResourceRepository} publish it. {@link ResourceRepository} publishes the event only after it successfully persists changes in
 * {@link Resource}'s state (see: {@link ResourceRepository#save(Resource)}).
 * <br>
 * Thanks to that change, there is no longer risk that changes in {@link Resource}'s state will be cancelled (rolled back) <em>AFTER</em> observers
 * reacted to them.
 */
public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		TeamService teamService = new TeamService();
		TaskService taskService = new TaskService();
		EventPublisher eventPublisher = new EventPublisher();
		ResourceFactory resourceFactory = new ResourceFactory();
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory, eventPublisher);

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
