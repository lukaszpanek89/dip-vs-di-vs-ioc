package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.holidayplan;

import java.time.LocalDate;

public class HolidayPlan {

	private final HolidayPlanAnemia anemia;

	public HolidayPlan(HolidayPlanAnemia anemia) {
		this.anemia = anemia;
	}

	public boolean isHolidayOn(LocalDate date) {
		return anemia.holidays().contains(date);
	}
}
