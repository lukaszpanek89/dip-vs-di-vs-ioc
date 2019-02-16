package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.resource;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.holidayplan.HolidayPlanId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.workloadplan.WorkloadPlanId;

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
