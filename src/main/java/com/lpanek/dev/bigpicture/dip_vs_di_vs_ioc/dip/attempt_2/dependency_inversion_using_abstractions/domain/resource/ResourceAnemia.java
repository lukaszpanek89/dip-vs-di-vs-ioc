package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.resource;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Immutable;

@Immutable
final class ResourceAnemia {

	private final ResourceId id;

	private final WorkloadPlanId workloadPlanId;

	private final HolidayPlanId holidayPlanId;

	ResourceAnemia(ResourceId id, WorkloadPlanId workloadPlanId, HolidayPlanId holidayPlanId) {
		this.id = id;
		this.workloadPlanId = workloadPlanId;
		this.holidayPlanId = holidayPlanId;
	}

	ResourceId id() {
		return id;
	}

	WorkloadPlanId workloadPlanId() {
		return workloadPlanId;
	}

	HolidayPlanId holidayPlanId() {
		return holidayPlanId;
	}
}
