package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.resource;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.workloadplan.WorkloadPlanRepository;

public class ResourceFactory {

	private final WorkloadPlanRepository workloadPlanRepository;

	private final HolidayPlanRepository holidayPlanRepository;

	public ResourceFactory(WorkloadPlanRepository workloadPlanRepository, HolidayPlanRepository holidayPlanRepository) {
		this.workloadPlanRepository = workloadPlanRepository;
		this.holidayPlanRepository = holidayPlanRepository;
	}

	public Resource recreate(ResourceAnemia resourceAnemia) {
		return new Resource(resourceAnemia, workloadPlanRepository, holidayPlanRepository);
	}
}
