package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_1.typical_implementation;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_1.typical_implementation.domain.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_1.typical_implementation.domain.Resource;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_1.typical_implementation.domain.ResourceFactory;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_1.typical_implementation.domain.ResourceId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_1.typical_implementation.domain.ResourceRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_1.typical_implementation.domain.TaskService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_1.typical_implementation.domain.TeamService;
import java.time.LocalDate;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.date;
import static java.util.concurrent.TimeUnit.HOURS;

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
