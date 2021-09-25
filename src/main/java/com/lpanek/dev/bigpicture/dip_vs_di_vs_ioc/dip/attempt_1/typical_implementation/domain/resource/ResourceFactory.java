package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.resource;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.workloadplan.WorkloadPlanRepository;

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
