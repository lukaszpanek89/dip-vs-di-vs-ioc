package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.workloadplan;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.common.Capacity;
import java.time.DayOfWeek;
import java.util.Collections;
import java.util.Map;

final class WorkloadPlanAnemia {

	private final WorkloadPlanId id;

	private final Map<DayOfWeek, Capacity> dailyCapacities;

	WorkloadPlanAnemia(WorkloadPlanId id, Map<DayOfWeek, Capacity> dailyCapacities) {
		if (dailyCapacities.size() != 7) {
			throw new RuntimeException(String.format("There should be daily capacity for each day of week, but was for %d days only", dailyCapacities.size()));
		}
		this.id = id;
		this.dailyCapacities = dailyCapacities;
	}

	WorkloadPlanId id() {
		return id;
	}

	Map<DayOfWeek, Capacity> dailyCapacities() {
		return Collections.unmodifiableMap(dailyCapacities);
	}
}
