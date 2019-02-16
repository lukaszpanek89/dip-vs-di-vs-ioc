package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.workloadplan;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.common.Capacity;
import java.time.DayOfWeek;
import java.util.Collections;
import java.util.Map;

public final class WorkloadPlanAnemia {

	private final WorkloadPlanId id;

	private final Map<DayOfWeek, Capacity> dailyCapacities;

	public WorkloadPlanAnemia(WorkloadPlanId id, Map<DayOfWeek, Capacity> dailyCapacities) {
		if (dailyCapacities.size() != 7) {
			throw new RuntimeException(String.format("There should be daily capacity for each day of week, but was for %d days only", dailyCapacities.size()));
		}
		this.id = id;
		this.dailyCapacities = dailyCapacities;
	}

	public WorkloadPlanId id() {
		return id;
	}

	public Map<DayOfWeek, Capacity> dailyCapacities() {
		return Collections.unmodifiableMap(dailyCapacities);
	}
}
