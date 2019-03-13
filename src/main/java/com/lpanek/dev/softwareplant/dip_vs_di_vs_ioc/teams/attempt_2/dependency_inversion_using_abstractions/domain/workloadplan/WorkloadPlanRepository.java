package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.common.Capacity;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.RepositoriesInitialData.FULL_TIME_WORKLOAD_PLAN_ID;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.util.concurrent.TimeUnit.HOURS;

public class WorkloadPlanRepository {

	private final Map<WorkloadPlanId, WorkloadPlanAnemia> repository = createRepositoryWithInitialData();

	public WorkloadPlan get(WorkloadPlanId workloadPlanId) {
		WorkloadPlanAnemia workloadPlanAnemia = repository.get(workloadPlanId);
		if (workloadPlanAnemia == null) {
			throw new RuntimeException(String.format("Workload plan with id '%s' does not exist", workloadPlanId));
		}
		return new WorkloadPlan(workloadPlanAnemia);
	}

	private Map<WorkloadPlanId, WorkloadPlanAnemia> createRepositoryWithInitialData() {
		Map<WorkloadPlanId, WorkloadPlanAnemia> repository = new HashMap<>();

		Map<DayOfWeek, Capacity> dailyCapacities = new HashMap<>();
		Capacity eightHours = new Capacity(8, HOURS);
		dailyCapacities.put(MONDAY, eightHours);
		dailyCapacities.put(TUESDAY, eightHours);
		dailyCapacities.put(WEDNESDAY, eightHours);
		dailyCapacities.put(THURSDAY, eightHours);
		dailyCapacities.put(FRIDAY, eightHours);
		dailyCapacities.put(SATURDAY, Capacity.ZERO);
		dailyCapacities.put(SUNDAY, Capacity.ZERO);
		WorkloadPlanAnemia workloadPlanAnemia = new WorkloadPlanAnemia(new WorkloadPlanId(FULL_TIME_WORKLOAD_PLAN_ID), dailyCapacities);
		repository.put(workloadPlanAnemia.id(), workloadPlanAnemia);

		return repository;
	}
}
