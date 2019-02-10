package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.event.implementation;

import java.time.DayOfWeek;
import java.util.concurrent.TimeUnit;

public class Client {

	public static void main(String[] args) {
		TeamService teamService = new TeamService();
		TaskService taskService = new TaskService();

		EventPublisher eventPublisher = EventPublisher.instance();
		eventPublisher.reset();
		eventPublisher.subscribe(ResourceCapacityChangedEvent.class, teamService);
		eventPublisher.subscribe(ResourceCapacityChangedEvent.class, taskService);

		ResourceFactory resourceFactory = new ResourceFactory();
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);

		ResourceId resourceId = new ResourceId("john.doe");
		Capacity newCapacity = new Capacity(8, TimeUnit.HOURS, DayOfWeek.MONDAY);

		Resource resource = resourceRepository.get(resourceId);
		resource.changeCapacityTo(newCapacity);
		resourceRepository.save(resource);

		System.out.println(resource);
	}

}
