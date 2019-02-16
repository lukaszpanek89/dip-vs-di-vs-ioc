package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.workloadplan.resource;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.common.Capacity;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.resource.ResourceWorkloadProvider;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.workloadplan.WorkloadPlan;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.workloadplan.WorkloadPlanId;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.workloadplan.WorkloadPlanRepository;
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
