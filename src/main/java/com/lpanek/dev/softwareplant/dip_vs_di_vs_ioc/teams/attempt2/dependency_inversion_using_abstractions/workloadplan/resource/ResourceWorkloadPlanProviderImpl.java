package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.workloadplan.resource;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.common.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.resource.ResourceWorkloadProvider;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.workloadplan.WorkloadPlan;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.workloadplan.WorkloadPlanId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.workloadplan.WorkloadPlanRepository;
import java.time.LocalDate;

// TODO: Find better name
public class ResourceWorkloadPlanProviderImpl implements ResourceWorkloadProvider {

	private final WorkloadPlanRepository workloadPlanRepository;

	public ResourceWorkloadPlanProviderImpl(WorkloadPlanRepository workloadPlanRepository) {
		this.workloadPlanRepository = workloadPlanRepository;
	}

	@Override
	public Capacity getCapacityFor(LocalDate date, WorkloadPlanId workloadPlanId) {
		WorkloadPlan workloadPlan = workloadPlanRepository.get(workloadPlanId);
		return workloadPlan.getCapacityFor(date);
	}
}
