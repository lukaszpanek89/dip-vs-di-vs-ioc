package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.resource;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.holidayplan.HolidayPlanId;
import java.time.LocalDate;

public interface ResourceHolidayProvider {

	boolean isHolidayOn(LocalDate date, HolidayPlanId holidayPlanId);
}
