package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.resource;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.common.Capacity;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.common.DatePeriod;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.holidayplan.HolidayPlan;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.holidayplan.HolidayPlanId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.workloadplan.Workload;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.workloadplan.WorkloadPlan;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.workloadplan.WorkloadPlanId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.workloadplan.WorkloadPlanRepository;
import java.time.LocalDate;

public class Resource {

	private final ResourceAnemia anemia;

	private final WorkloadPlanRepository workloadPlanRepository;

	private final HolidayPlanRepository holidayPlanRepository;

	public Resource(ResourceAnemia anemia, WorkloadPlanRepository workloadPlanRepository, HolidayPlanRepository holidayPlanRepository) {
		this.anemia = anemia;
		this.workloadPlanRepository = workloadPlanRepository;
		this.holidayPlanRepository = holidayPlanRepository;
	}

	public ResourceId id() {
		return anemia.id();
	}

	public Capacity getCapacityFor(DatePeriod period) {
		WorkloadPlan workloadPlan = workloadPlanRepository.get(workloadPlanId());
		HolidayPlan holidayPlan = holidayPlanRepository.get(holidayPlanId());

		Capacity capacityForPeriod = Capacity.ZERO;
		for (LocalDate currentDate = period.startDate(); !currentDate.isAfter(period.endDate()); currentDate = currentDate.plusDays(1)) {
			Capacity dailyCapacity;
			if (holidayPlan.isHolidayOn(currentDate)) {
				dailyCapacity = Capacity.ZERO;
			} else {
				Workload workload = workloadPlan.getWorkloadOn(currentDate);
				dailyCapacity = workload.toCapacity();
			}
			capacityForPeriod = capacityForPeriod.sum(dailyCapacity);
		}
		return capacityForPeriod;
	}

	public void changeWorkloadPlanTo(WorkloadPlanId newWorkloadPlanId) {
		// ...
	}

	ResourceAnemia toAnemia() {
		return anemia;
	}

	private WorkloadPlanId workloadPlanId() {
		return anemia.workloadPlanId();
	}

	private HolidayPlanId holidayPlanId() {
		return anemia.holidayPlanId();
	}
}
