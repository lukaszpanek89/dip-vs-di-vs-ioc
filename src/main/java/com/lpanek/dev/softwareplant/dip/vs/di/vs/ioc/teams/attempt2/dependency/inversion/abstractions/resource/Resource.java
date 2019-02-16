package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.resource;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.common.Capacity;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.common.DatePeriod;
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
