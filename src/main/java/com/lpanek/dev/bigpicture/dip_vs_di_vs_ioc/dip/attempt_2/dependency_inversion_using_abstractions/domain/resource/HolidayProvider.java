package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.resource;

import java.time.LocalDate;

public interface HolidayProvider {

	boolean isHolidayOn(LocalDate date, HolidayPlanId holidayPlanId);
}
