package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.workloadplan;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.common.Capacity;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WorkloadPlan {

	private final WorkloadPlanAnemia anemia;

	public WorkloadPlan(WorkloadPlanAnemia anemia) {
		this.anemia = anemia;
	}

	public WorkloadPlanId id() {
		return anemia.id();
	}

	public Capacity getWorkloadOn(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		return anemia.dailyCapacities().get(dayOfWeek);
	}
}
