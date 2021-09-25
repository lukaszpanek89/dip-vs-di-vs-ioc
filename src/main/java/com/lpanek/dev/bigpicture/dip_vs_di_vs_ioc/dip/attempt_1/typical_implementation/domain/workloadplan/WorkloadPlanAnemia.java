package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.workloadplan;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class WorkloadPlanAnemia {

	private final WorkloadPlanId id;

	private final Map<DayOfWeek, Workload> dailyWorkloads;

	WorkloadPlanAnemia(WorkloadPlanId id, Map<DayOfWeek, Workload> dailyWorkloads) {
		if (dailyWorkloads.size() != 7) {
			throw new RuntimeException(String.format("There should be daily workload for each day of week, but was for %d days only", dailyWorkloads.size()));
		}
		this.id = id;
		this.dailyWorkloads = new HashMap<>(dailyWorkloads);
	}

	WorkloadPlanId id() {
		return id;
	}

	Map<DayOfWeek, Workload> dailyWorkloads() {
		return Collections.unmodifiableMap(dailyWorkloads);
	}
}
