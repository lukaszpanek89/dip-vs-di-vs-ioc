package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.workloadplan;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WorkloadPlan {

	private final WorkloadPlanAnemia anemia;

	public WorkloadPlan(WorkloadPlanAnemia anemia) {
		this.anemia = anemia;
	}

	public Workload getWorkloadOn(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		return anemia.dailyWorkloads().get(dayOfWeek);
	}
}
