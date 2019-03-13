package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.common.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.common.DatePeriod;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.resource.HolidayProvider;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.resource.ResourceFactory;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.resource.ResourceRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.resource.WorkloadProvider;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.team.ResourceCapacityProvider;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.team.Team;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.team.TeamFactory;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.team.TeamId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.team.TeamRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.workloadplan.WorkloadPlanRepository;
import java.time.LocalDate;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.RepositoriesInitialData.TEAM_OAKS_ID;

/**
 * <b>PROBLEM:</b>
 * <br>
 * In what way any given object should cooperate with objects its depends upon?
 * <br><br>
 * <b>SOLUTION:</b>
 * <br>
 * This example relaxes solution described in
 * {@link com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.Application}.
 * <br>
 * This example replaces abstractions that acted as bridges between packages (e.g., <code>ResourceCapacityProvider</code> interface,
 * <code>WorkloadProvider</code> interface) with facades (e.g., {@link ResourceCapacityProvider} concrete class, {@link WorkloadProvider} concrete class).
 */
public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		WorkloadPlanRepository workloadPlanRepository = new WorkloadPlanRepository();
		HolidayPlanRepository holidayPlanRepository = new HolidayPlanRepository();

		WorkloadProvider workloadProvider = new WorkloadProvider(workloadPlanRepository);
		HolidayProvider holidayProvider = new HolidayProvider(holidayPlanRepository);
		ResourceFactory resourceFactory = new ResourceFactory(workloadProvider, holidayProvider);
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);

		ResourceCapacityProvider resourceCapacityProvider = new ResourceCapacityProvider(resourceRepository);
		TeamFactory teamFactory = new TeamFactory(resourceCapacityProvider);
		TeamRepository teamRepository = new TeamRepository(teamFactory);

		// ===== 2. USE CASE =====
		TeamId teamId = TEAM_OAKS_ID;
		Team team = teamRepository.get(teamId);
		DatePeriod period = new DatePeriod(date("2019-05-01"), date("2019-05-10"));
		Capacity capacity = team.getCapacityFor(period);

		System.out.println(String.format("Team '%s' capacity over period %s is %s", teamId, period, capacity));
	}

	private static LocalDate date(String date) {
		return LocalDate.parse(date);
	}
}
