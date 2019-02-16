package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.resource;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.common.Capacity;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.workloadplan.WorkloadPlanId;
import java.time.LocalDate;

public interface ResourceWorkloadProvider {

	Capacity getCapacityFor(LocalDate date, WorkloadPlanId workloadPlanId);
}
