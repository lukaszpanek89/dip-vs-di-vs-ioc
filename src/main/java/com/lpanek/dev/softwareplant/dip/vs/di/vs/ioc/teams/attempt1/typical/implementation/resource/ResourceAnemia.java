package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.resource;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.holidayplan.HolidayPlanId;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.workloadplan.WorkloadPlanId;

public class ResourceAnemia {

	private final ResourceId id;

	private final WorkloadPlanId workloadPlanId;

	private final HolidayPlanId holidayPlanId;

	public ResourceAnemia(ResourceId id, WorkloadPlanId workloadPlanId, HolidayPlanId holidayPlanId) {
		this.id = id;
		this.workloadPlanId = workloadPlanId;
		this.holidayPlanId = holidayPlanId;
	}

	public ResourceId id() {
		return id;
	}

	public WorkloadPlanId workloadPlanId() {
		return workloadPlanId;
	}

	public HolidayPlanId holidayPlanId() {
		return holidayPlanId;
	}
}
