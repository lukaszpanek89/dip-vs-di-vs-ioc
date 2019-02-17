package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.common.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.common.DatePeriod;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.holidayplan.HolidayPlanId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.workloadplan.WorkloadPlanId;
import java.time.LocalDate;

public class Resource {

	private final ResourceAnemia anemia;

	private final WorkloadProvider workloadProvider;

	private final HolidayProvider holidayProvider;

	public Resource(ResourceAnemia anemia, WorkloadProvider workloadProvider, HolidayProvider holidayProvider) {
		this.anemia = anemia;
		this.workloadProvider = workloadProvider;
		this.holidayProvider = holidayProvider;
	}

	public ResourceId id() {
		return anemia.id();
	}

	public Capacity getCapacityFor(DatePeriod period) {
		Capacity capacityForPeriod = Capacity.ZERO;
		for (LocalDate currentDate = period.startDate(); !currentDate.isAfter(period.endDate()); currentDate = currentDate.plusDays(1)) {
			Capacity dailyCapacity;
			if (holidayProvider.isHolidayOn(currentDate, holidayPlanId())) {
				dailyCapacity = Capacity.ZERO;
			} else {
				dailyCapacity = workloadProvider.getWorkloadOn(currentDate, workloadPlanId());
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
