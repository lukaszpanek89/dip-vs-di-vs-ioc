package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_interfaces.holidayplan.resource;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_interfaces.holidayplan.HolidayPlan;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_interfaces.holidayplan.HolidayPlanId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_interfaces.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_interfaces.resource.ResourceHolidayProvider;
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
