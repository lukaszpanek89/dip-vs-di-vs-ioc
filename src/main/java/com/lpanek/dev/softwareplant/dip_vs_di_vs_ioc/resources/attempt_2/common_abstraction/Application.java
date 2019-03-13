package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction;

import com.google.common.collect.Lists;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.domain.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.domain.Resource;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.domain.ResourceCapacityObserver;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.domain.ResourceFactory;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.domain.ResourceId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.domain.ResourceRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.domain.TaskService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.domain.TeamService;
import java.time.LocalDate;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.date;
import static java.util.concurrent.TimeUnit.HOURS;

/**
 * <b>PROBLEM:</b>
 * <br>
 * How to notify dependent objects on changes in given object (i.e., in its state)?
 * <br><br>
 * <b>SOLUTION:</b>
 * <br>
 * This example improves solution described in
 * {@link com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_1.typical_implementation.Application}.
 * <br>
 * In this example, changed object ({@link Resource}) still notifies dependent objects ({@link TeamService} and {@link TaskService}) by itself, but this time
 * these dependent objects are hidden behind {@link ResourceCapacityObserver} abstraction.
 * <br>
 * As a result, this example makes the code consistent with:
 * <ul>
 * <li><b>Open-Closed Principle</b> - should any new dependent object appear, no code will be changed in {@link Resource} class,</li>
 * <li><b>Interface Segregation Principle</b> - thanks to {@link ResourceCapacityObserver} abstraction, {@link Resource} class has minimal knowledge of
 * dependent objects.</li>
 * </ul>
 */
public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		TeamService teamService = new TeamService();
		TaskService taskService = new TaskService();
		ResourceFactory resourceFactory = new ResourceFactory(Lists.newArrayList(teamService, taskService));
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
