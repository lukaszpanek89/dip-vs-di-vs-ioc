package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous.domain.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous.domain.Resource;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous.domain.ResourceCapacityChangedEvent;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous.domain.ResourceFactory;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous.domain.ResourceId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous.domain.ResourceRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous.domain.TaskService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous.domain.TeamService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous.domain.event.EventPublisher;
import java.time.LocalDate;
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

		eventPublisher.subscribe(ResourceCapacityChangedEvent.class, teamService);
		eventPublisher.subscribe(ResourceCapacityChangedEvent.class, taskService);

		// ===== 2. USE CASE =====
		ResourceId resourceId = new ResourceId("john.doe");
		LocalDate date = date("2019-02-25");
		Capacity newCapacity = new Capacity(8, HOURS);

		Resource resource = resourceRepository.get(resourceId);
		resource.changeCapacityOnDate(date, newCapacity);
		resourceRepository.save(resource);
	}

}
