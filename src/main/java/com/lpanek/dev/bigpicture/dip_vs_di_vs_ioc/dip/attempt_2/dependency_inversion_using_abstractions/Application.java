package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.RepositoriesInitialData;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.common.Capacity;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.common.DatePeriod;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.holidayplan.resource.HolidayProviderImpl;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.resource.HolidayProvider;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.resource.Resource;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.resource.ResourceFactory;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.resource.ResourceRepository;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.resource.WorkloadProvider;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.resource.team.ResourceCapacityProviderImpl;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.team.ResourceCapacityProvider;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.team.Team;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.team.TeamFactory;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.team.TeamId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.team.TeamRepository;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.WorkloadPlanRepository;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.resource.WorkloadProviderImpl;
import java.time.LocalDate;

/**
 * <b>PROBLEM:</b>
 * <br>
 * In what way any given object should cooperate with objects its depends upon?
 * <br><br>
 * <b>SOLUTION:</b>
 * <br>
 * This example improves solution described in
 * {@link com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.Application}.
 * <br><br>
 * This example uses <b>Dependency Inversion Principle</b> in order to invert source code dependencies between an object and objects it depends on (e.g.,
 * {@link Team} no longer directly cooperates with {@link ResourceRepository} and {@link Resource}, instead it cooperates with
 * {@link ResourceCapacityProvider} abstraction, which is on the same level of abstraction that {@link Team} is).
 */
public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		WorkloadPlanRepository workloadPlanRepository = new WorkloadPlanRepository();
		HolidayPlanRepository holidayPlanRepository = new HolidayPlanRepository();

		WorkloadProvider workloadProvider = new WorkloadProviderImpl(workloadPlanRepository);
		HolidayProvider holidayProvider = new HolidayProviderImpl(holidayPlanRepository);
		ResourceFactory resourceFactory = new ResourceFactory(workloadProvider, holidayProvider);
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);

		ResourceCapacityProvider resourceCapacityProvider = new ResourceCapacityProviderImpl(resourceRepository);
		TeamFactory teamFactory = new TeamFactory(resourceCapacityProvider);
		TeamRepository teamRepository = new TeamRepository(teamFactory);

		// ===== 2. USE CASE =====
		TeamId teamId = new TeamId(RepositoriesInitialData.TEAM_OAKS_ID);
		Team team = teamRepository.get(teamId);
		DatePeriod period = new DatePeriod(date("2019-05-01"), date("2019-05-10"));
		Capacity capacity = team.getCapacityFor(period);

		System.out.println(String.format("Team '%s' capacity over period %s is %s", teamId, period, capacity));
	}

	private static LocalDate date(String date) {
		return LocalDate.parse(date);
	}
}
