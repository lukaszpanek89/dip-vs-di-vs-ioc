package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.resource;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.common.Capacity;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.workloadplan.WorkloadPlanId;
import java.time.LocalDate;

public interface ResourceWorkloadProvider {

	Capacity getCapacityFor(LocalDate date, WorkloadPlanId workloadPlanId);
}
