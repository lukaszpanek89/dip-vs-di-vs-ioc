package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.resource;

import com.google.common.collect.Maps;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.common.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.WorkloadPlanId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.WorkloadProvider;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.WorkloadPlan;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.WorkloadPlanRepository;
import java.time.LocalDate;
import java.util.Map;

public class WorkloadProviderImpl implements WorkloadProvider {

	private final WorkloadPlanRepository workloadPlanRepository;

	// If this Provider is reused between use cases, then below cache should be cleared before each use case
	private final Map<WorkloadPlanId, WorkloadPlan> workloadPlanCache;

	public WorkloadProviderImpl(WorkloadPlanRepository workloadPlanRepository) {
		this.workloadPlanRepository = workloadPlanRepository;
		this.workloadPlanCache = Maps.newHashMap();
	}

	@Override
	public Capacity getWorkloadOn(LocalDate date, WorkloadPlanId workloadPlanId) {
		WorkloadPlan workloadPlan = getFromCache(workloadPlanId);
		return workloadPlan.getWorkloadOn(date);
	}

	private WorkloadPlan getFromCache(WorkloadPlanId workloadPlanId) {
		WorkloadPlan workloadPlan = workloadPlanCache.get(workloadPlanId);
		if (workloadPlan == null) {
			workloadPlan = workloadPlanRepository.get(toDomain(workloadPlanId));
			workloadPlanCache.put(workloadPlanId, workloadPlan);
		}
		return workloadPlan;
	}

	private com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.WorkloadPlanId toDomain(
			WorkloadPlanId workloadPlanId) {
		return new com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.WorkloadPlanId(
				workloadPlanId.internal());
	}
}
