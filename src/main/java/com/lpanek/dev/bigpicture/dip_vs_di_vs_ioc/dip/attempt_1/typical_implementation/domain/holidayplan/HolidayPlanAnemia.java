package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.holidayplan;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Immutable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Immutable
final class HolidayPlanAnemia {

	private final HolidayPlanId id;

	private final Set<LocalDate> holidays;

	HolidayPlanAnemia(HolidayPlanId id, Set<LocalDate> holidays) {
		this.id = id;
		this.holidays = new HashSet<>(holidays);
	}

	HolidayPlanId id() {
		return id;
	}

	Set<LocalDate> holidays() {
		return Collections.unmodifiableSet(holidays);
	}
}
