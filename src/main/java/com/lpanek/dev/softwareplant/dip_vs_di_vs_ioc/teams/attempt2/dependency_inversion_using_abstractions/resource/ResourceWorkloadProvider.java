package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.resource;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.common.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.workloadplan.WorkloadPlanId;
import java.time.LocalDate;

public interface ResourceWorkloadProvider {

	Capacity getCapacityFor(LocalDate date, WorkloadPlanId workloadPlanId);
}
