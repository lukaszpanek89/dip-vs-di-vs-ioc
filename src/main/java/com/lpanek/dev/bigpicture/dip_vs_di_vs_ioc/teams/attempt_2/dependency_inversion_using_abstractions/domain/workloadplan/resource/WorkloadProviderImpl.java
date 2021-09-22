package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.resource;

import com.google.common.collect.Maps;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.Workload;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.WorkloadProvider;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.WorkloadPlan;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.WorkloadPlanId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.WorkloadPlanRepository;
import java.time.LocalDate;
import java.util.Map;

public class WorkloadProviderImpl implements WorkloadProvider {

	private final WorkloadPlanRepository workloadPlanRepository;

	// If this Provider is reused between use cases, then below cache should be cleared before each use case
	private final Map<com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.WorkloadPlanId, WorkloadPlan> workloadPlanCache;

	public WorkloadProviderImpl(WorkloadPlanRepository workloadPlanRepository) {
		this.workloadPlanRepository = workloadPlanRepository;
		this.workloadPlanCache = Maps.newHashMap();
	}

	@Override
	public Workload getWorkloadOn(LocalDate date, com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.WorkloadPlanId workloadPlanId) {
		WorkloadPlan workloadPlan = getFromCache(workloadPlanId);
		return convert(workloadPlan.getWorkloadOn(date));
	}

	private WorkloadPlan getFromCache(com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.WorkloadPlanId workloadPlanId) {
		WorkloadPlan workloadPlan = workloadPlanCache.get(workloadPlanId);
		if (workloadPlan == null) {
			workloadPlan = workloadPlanRepository.get(convert(workloadPlanId));
			workloadPlanCache.put(workloadPlanId, workloadPlan);
		}
		return workloadPlan;
	}

	private WorkloadPlanId convert(
			com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.WorkloadPlanId workloadPlanId) {
		return new WorkloadPlanId(
				workloadPlanId.internal());
	}

	private Workload convert(
			com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.Workload workload) {
		return new Workload(workload.startMinute(), workload.endMinute());
	}
}
