package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.resource;

import com.google.common.collect.Maps;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.workloadplan.Workload;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.workloadplan.WorkloadPlan;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.workloadplan.WorkloadPlanId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.workloadplan.WorkloadPlanRepository;
import java.time.LocalDate;
import java.util.Map;

public class WorkloadProvider {

	private final WorkloadPlanRepository workloadPlanRepository;

	// If this Provider is reused between use cases, then below cache should be cleared before each use case
	private final Map<WorkloadPlanId, WorkloadPlan> workloadPlanCache;

	public WorkloadProvider(WorkloadPlanRepository workloadPlanRepository) {
		this.workloadPlanRepository = workloadPlanRepository;
		this.workloadPlanCache = Maps.newHashMap();
	}

	public Workload getWorkloadOn(LocalDate date, WorkloadPlanId workloadPlanId) {
		WorkloadPlan workloadPlan = getFromCache(workloadPlanId);
		return workloadPlan.getWorkloadOn(date);
	}

	private WorkloadPlan getFromCache(WorkloadPlanId workloadPlanId) {
		WorkloadPlan workloadPlan = workloadPlanCache.get(workloadPlanId);
		if (workloadPlan == null) {
			workloadPlan = workloadPlanRepository.get(workloadPlanId);
			workloadPlanCache.put(workloadPlan.id(), workloadPlan);
		}
		return workloadPlan;
	}
}
