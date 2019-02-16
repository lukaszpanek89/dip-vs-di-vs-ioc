package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.holidayplan;

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
