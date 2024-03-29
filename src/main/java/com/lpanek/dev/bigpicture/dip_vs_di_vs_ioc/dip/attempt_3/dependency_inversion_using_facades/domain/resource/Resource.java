package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.resource;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.common.Capacity;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.common.DatePeriod;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.holidayplan.HolidayPlanId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.workloadplan.Workload;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.workloadplan.WorkloadPlanId;
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
				Workload workload = workloadProvider.getWorkloadOn(currentDate, workloadPlanId());
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
