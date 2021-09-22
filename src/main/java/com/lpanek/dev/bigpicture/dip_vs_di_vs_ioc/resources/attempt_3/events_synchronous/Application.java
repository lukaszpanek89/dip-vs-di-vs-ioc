package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous.domain.Capacity;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous.domain.Resource;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous.domain.ResourceCapacityChangedEvent;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous.domain.ResourceFactory;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous.domain.ResourceId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous.domain.ResourceRepository;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous.domain.TaskService;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous.domain.TeamService;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous.domain.event.EventPublisher;
import java.time.LocalDate;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.date;
import static java.util.concurrent.TimeUnit.HOURS;

/**
 * <b>PROBLEM:</b><br>
 * How to notify dependent objects on changes in given object (i.e., in its state)?
 * <br><br>
 * <b>SOLUTION:</b>
 * <br>
 * This example improves solution described in
 * {@link com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.Application}.
 * <br><br>
 * This example uses <b>Events</b>, one of <b>Inversion of Control</b> flavours - changed object ({@link Resource}) no longer notifies dependent objects
 * ({@link TeamService} and {@link TaskService}) by itself, this time it emits {@link ResourceCapacityChangedEvent} event, which dependent objects are
 * subscribed to. This event is passed to dependent objects synchronously.
 */
public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		TeamService teamService = new TeamService();
		TaskService taskService = new TaskService();
		EventPublisher eventPublisher = new EventPublisher();
		ResourceFactory resourceFactory = new ResourceFactory(eventPublisher);
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);

		eventPublisher.subscribe(ResourceCapacityChangedEvent.class, teamService);
		eventPublisher.subscribe(ResourceCapacityChangedEvent.class, taskService);

		// ===== 2. USE CASE =====
		ResourceId resourceId = new ResourceId("john.doe");
		LocalDate date = date("2019-02-25");
		Capacity newCapacity = new Capacity(8, HOURS);

		Resource resource = resourceRepository.get(resourceId);
		resource.changeCapacityOn(date, newCapacity);
		resourceRepository.save(resource);
	}

}
