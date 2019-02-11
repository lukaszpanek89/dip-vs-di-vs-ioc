package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.workloadplan;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.common.Capacity;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WorkloadPlan {

	private final WorkloadPlanAnemia anemia;

	public WorkloadPlan(WorkloadPlanAnemia anemia) {
		this.anemia = anemia;
	}

	public Capacity getCapacityFor(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		return anemia.dailyCapacities().get(dayOfWeek);
	}
}
