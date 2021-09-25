package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.common.Capacity;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.common.DatePeriod;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.resource.Resource;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.resource.ResourceFactory;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.resource.ResourceRepository;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.team.Team;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.team.TeamFactory;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.team.TeamId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.team.TeamRepository;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.workloadplan.WorkloadPlanRepository;
import java.time.LocalDate;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.RepositoriesInitialData.TEAM_OAKS_ID;

/**
 * <b>PROBLEM:</b>
 * <br>
 * In what way any given object should cooperate with objects its depends upon?
 * <br><br>
 * <b>SOLUTION:</b>
 * <br>
 * This example shows a simple solution - each object cooperates with its dependencies directly (e.g., {@link Team} cooperates directly with
 * {@link ResourceRepository} and {@link Resource}).
 */
public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		WorkloadPlanRepository workloadPlanRepository = new WorkloadPlanRepository();
		HolidayPlanRepository holidayPlanRepository = new HolidayPlanRepository();

		ResourceFactory resourceFactory = new ResourceFactory(workloadPlanRepository, holidayPlanRepository);
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);

		TeamFactory teamFactory = new TeamFactory(resourceRepository);
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
