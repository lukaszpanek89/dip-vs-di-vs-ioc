package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.workloadplan;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.RepositoriesInitialData.FULL_TIME_WORKLOAD_PLAN_ID;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.joda.time.DateTimeConstants.MINUTES_PER_HOUR;

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

		Map<DayOfWeek, Workload> dailyWorkloads = new HashMap<>();
		Workload from8amTo4pm = new Workload(8 * MINUTES_PER_HOUR, 16 * MINUTES_PER_HOUR);
		dailyWorkloads.put(MONDAY, from8amTo4pm);
		dailyWorkloads.put(TUESDAY, from8amTo4pm);
		dailyWorkloads.put(WEDNESDAY, from8amTo4pm);
		dailyWorkloads.put(THURSDAY, from8amTo4pm);
		dailyWorkloads.put(FRIDAY, from8amTo4pm);
		dailyWorkloads.put(SATURDAY, Workload.ZERO);
		dailyWorkloads.put(SUNDAY, Workload.ZERO);
		WorkloadPlanAnemia workloadPlanAnemia = new WorkloadPlanAnemia(FULL_TIME_WORKLOAD_PLAN_ID, dailyWorkloads);
		repository.put(workloadPlanAnemia.id(), workloadPlanAnemia);

		return repository;
	}
}
