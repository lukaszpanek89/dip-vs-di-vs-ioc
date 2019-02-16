package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.resource;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.holidayplan.HolidayPlanId;
import java.time.LocalDate;

public interface ResourceHolidayProvider {

	boolean isHolidayOn(LocalDate date, HolidayPlanId holidayPlanId);
}
