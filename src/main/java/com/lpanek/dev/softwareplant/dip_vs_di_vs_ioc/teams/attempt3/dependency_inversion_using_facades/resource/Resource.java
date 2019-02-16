package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.resource;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.common.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.common.DatePeriod;
import java.time.LocalDate;

public class Resource {

	private final ResourceAnemia anemia;

	private final ResourceWorkloadProvider workloadProvider;

	private final ResourceHolidayProvider holidayProvider;

	public Resource(ResourceAnemia anemia, ResourceWorkloadProvider workloadProvider, ResourceHolidayProvider holidayProvider) {
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
			if (holidayProvider.isHolidayOn(currentDate, anemia.holidayPlanId())) {
				dailyCapacity = Capacity.ZERO;
			} else {
				dailyCapacity = workloadProvider.getCapacityFor(currentDate, anemia.workloadPlanId());
			}
			capacityForPeriod = capacityForPeriod.sum(dailyCapacity);
		}
		return capacityForPeriod;
	}
}
