package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.holidayplan;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

public class HolidayPlanAnemia {

	private final HolidayPlanId id;

	private final Set<LocalDate> holidays;

	public HolidayPlanAnemia(HolidayPlanId id, Set<LocalDate> holidays) {
		this.id = id;
		this.holidays = holidays;
	}

	public HolidayPlanId id() {
		return id;
	}

	public Set<LocalDate> holidays() {
		return Collections.unmodifiableSet(holidays);
	}
}
