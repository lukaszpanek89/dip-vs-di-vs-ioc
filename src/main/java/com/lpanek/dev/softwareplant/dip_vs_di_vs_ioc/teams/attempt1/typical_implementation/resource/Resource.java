package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.resource;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.common.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.common.DatePeriod;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.holidayplan.HolidayPlan;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.workloadplan.WorkloadPlan;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.workloadplan.WorkloadPlanRepository;
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
		WorkloadPlan workloadPlan = workloadPlanRepository.get(anemia.workloadPlanId());
		HolidayPlan holidayPlan = holidayPlanRepository.get(anemia.holidayPlanId());

		Capacity capacityForPeriod = Capacity.ZERO;
		for (LocalDate currentDate = period.startDate(); !currentDate.isAfter(period.endDate()); currentDate = currentDate.plusDays(1)) {
			Capacity dailyCapacity;
			if (holidayPlan.isHolidayOn(currentDate)) {
				dailyCapacity = Capacity.ZERO;
			} else {
				dailyCapacity = workloadPlan.getCapacityFor(currentDate);
			}
			capacityForPeriod = capacityForPeriod.sum(dailyCapacity);
		}
		return capacityForPeriod;
	}
}
