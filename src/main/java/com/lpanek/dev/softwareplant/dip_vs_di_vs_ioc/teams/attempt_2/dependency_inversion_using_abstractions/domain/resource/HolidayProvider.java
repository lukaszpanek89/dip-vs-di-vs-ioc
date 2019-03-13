package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource;

import java.time.LocalDate;

public interface HolidayProvider {

	boolean isHolidayOn(LocalDate date, HolidayPlanId holidayPlanId);
}
