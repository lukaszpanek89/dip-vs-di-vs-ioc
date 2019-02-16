package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.common.Capacity;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.common.DatePeriod;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.resource.ResourceFactory;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.resource.ResourceRepository;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.team.Team;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.team.TeamFactory;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.team.TeamId;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.team.TeamRepository;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.workloadplan.WorkloadPlanRepository;
import java.time.LocalDate;
import static com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.Constants.TEAM_OAKS_ID;

public class Client {

	public static void main(String[] args) {
		WorkloadPlanRepository workloadPlanRepository = new WorkloadPlanRepository();
		HolidayPlanRepository holidayPlanRepository = new HolidayPlanRepository();
		ResourceFactory resourceFactory = new ResourceFactory(workloadPlanRepository, holidayPlanRepository);
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);
		TeamFactory teamFactory = new TeamFactory(resourceRepository);
		TeamRepository teamRepository = new TeamRepository(teamFactory);

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
