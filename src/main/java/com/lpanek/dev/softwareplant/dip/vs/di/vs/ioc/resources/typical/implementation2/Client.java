package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.typical.implementation2;

import com.google.common.collect.Lists;
import java.time.DayOfWeek;
import java.util.concurrent.TimeUnit;

public class Client {

	public static void main(String[] args) {
		TeamService teamService = new TeamService();
		TaskService taskService = new TaskService();
		ResourceFactory resourceFactory = new ResourceFactory(Lists.newArrayList(teamService, taskService));
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);

		ResourceId resourceId = new ResourceId("john.doe");
		Capacity newCapacity = new Capacity(8, TimeUnit.HOURS, DayOfWeek.MONDAY);

		Resource resource = resourceRepository.get(resourceId);
		resource.changeCapacityTo(newCapacity);
		resourceRepository.save(resource);

		System.out.println(resource);
	}

}
