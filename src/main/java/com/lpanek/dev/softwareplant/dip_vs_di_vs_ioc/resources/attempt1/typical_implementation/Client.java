package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt1.typical_implementation;

import java.time.DayOfWeek;
import java.util.concurrent.TimeUnit;

public class Client {

	public static void main(String[] args) {
		TeamService teamService = new TeamService();
		TaskService taskService = new TaskService();
		ResourceFactory resourceFactory = new ResourceFactory(teamService, taskService);
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);

		ResourceId resourceId = new ResourceId("john.doe");
		Capacity newCapacity = new Capacity(8, TimeUnit.HOURS, DayOfWeek.MONDAY);

		Resource resource = resourceRepository.get(resourceId);
		resource.changeCapacityTo(newCapacity);
		resourceRepository.save(resource);

		System.out.println(resource);
	}

}