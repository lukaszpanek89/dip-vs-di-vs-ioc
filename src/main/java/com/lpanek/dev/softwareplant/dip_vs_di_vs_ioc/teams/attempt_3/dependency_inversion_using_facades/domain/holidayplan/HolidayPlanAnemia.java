package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.holidayplan;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

final class HolidayPlanAnemia {

	private final HolidayPlanId id;

	private final Set<LocalDate> holidays;

	HolidayPlanAnemia(HolidayPlanId id, Set<LocalDate> holidays) {
		this.id = id;
		this.holidays = holidays;
	}

	HolidayPlanId id() {
		return id;
	}

	Set<LocalDate> holidays() {
		return Collections.unmodifiableSet(holidays);
	}
}
