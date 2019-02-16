package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.holidayplan.resource;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.holidayplan.HolidayPlan;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.holidayplan.HolidayPlanId;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.resource.ResourceHolidayProvider;
import java.time.LocalDate;

// TODO: Find better name
public class ResourceHolidayProviderImpl implements ResourceHolidayProvider {

	private final HolidayPlanRepository holidayPlanRepository;

	public ResourceHolidayProviderImpl(HolidayPlanRepository holidayPlanRepository) {
		this.holidayPlanRepository = holidayPlanRepository;
	}

	@Override
	public boolean isHolidayOn(LocalDate date, HolidayPlanId holidayPlanId) {
		HolidayPlan holidayPlan = holidayPlanRepository.get(holidayPlanId);
		return holidayPlan.isHolidayOn(date);
	}
}
