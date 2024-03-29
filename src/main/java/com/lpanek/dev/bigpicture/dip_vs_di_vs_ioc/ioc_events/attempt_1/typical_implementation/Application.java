package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_1.typical_implementation;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_1.typical_implementation.domain.Capacity;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_1.typical_implementation.domain.Resource;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_1.typical_implementation.domain.ResourceFactory;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_1.typical_implementation.domain.ResourceId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_1.typical_implementation.domain.ResourceRepository;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_1.typical_implementation.domain.TaskService;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_1.typical_implementation.domain.TeamService;
import java.time.LocalDate;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.DateUtil.date;
import static java.util.concurrent.TimeUnit.HOURS;

/**
 * <b>PROBLEM:</b>
 * <br>
 * How to notify dependent objects on changes in given object (i.e., in its state)?
 * <br><br>
 * <b>SOLUTION:</b>
 * <br>
 * This example shows a simple solution - changed object ({@link Resource}) notifies dependent objects ({@link TeamService} and {@link TaskService}) by itself.
 */
public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		TeamService teamService = new TeamService();
		TaskService taskService = new TaskService();
		ResourceFactory resourceFactory = new ResourceFactory(teamService, taskService);
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);

		// ===== 2. USE CASE =====
		ResourceId resourceId = new ResourceId("john.doe");
		LocalDate date = date("2019-02-25");
		Capacity newCapacity = new Capacity(8, HOURS);

		Resource resource = resourceRepository.get(resourceId);
		resource.changeCapacityOn(date, newCapacity);
		resourceRepository.save(resource);
	}

}
