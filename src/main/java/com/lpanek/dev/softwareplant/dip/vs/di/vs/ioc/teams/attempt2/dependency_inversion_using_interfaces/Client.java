package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.common.Capacity;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.common.DatePeriod;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.holidayplan.resource.ResourceHolidayProviderImpl;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.resource.ResourceFactory;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.resource.ResourceHolidayProvider;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.resource.ResourceRepository;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.resource.ResourceWorkloadProvider;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.resource.team.ResourceCapacityProviderImpl;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.team.ResourceCapacityProvider;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.team.Team;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.team.TeamFactory;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.team.TeamId;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.team.TeamRepository;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.workloadplan.WorkloadPlanRepository;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.workloadplan.resource.ResourceWorkloadPlanProviderImpl;
import java.time.LocalDate;
import static com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.Constants.TEAM_OAKS_ID;

public class Client {

	public static void main(String[] args) {
		WorkloadPlanRepository workloadPlanRepository = new WorkloadPlanRepository();
		HolidayPlanRepository holidayPlanRepository = new HolidayPlanRepository();

		ResourceWorkloadProvider resourceWorkloadProvider = new ResourceWorkloadPlanProviderImpl(workloadPlanRepository);
		ResourceHolidayProvider resourceHolidayProvider = new ResourceHolidayProviderImpl(holidayPlanRepository);
		ResourceFactory resourceFactory = new ResourceFactory(resourceWorkloadProvider, resourceHolidayProvider);
		ResourceRepository resourceRepository = new ResourceRepository(resourceFactory);

		ResourceCapacityProvider resourceCapacityProvider = new ResourceCapacityProviderImpl(resourceRepository);
		TeamFactory teamFactory = new TeamFactory(resourceCapacityProvider);
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
