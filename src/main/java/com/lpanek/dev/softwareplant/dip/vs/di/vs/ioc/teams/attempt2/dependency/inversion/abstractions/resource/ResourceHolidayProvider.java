package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.resource;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.holidayplan.HolidayPlanId;
import java.time.LocalDate;

public interface ResourceHolidayProvider {

	boolean isHolidayOn(LocalDate date, HolidayPlanId holidayPlanId);
}
