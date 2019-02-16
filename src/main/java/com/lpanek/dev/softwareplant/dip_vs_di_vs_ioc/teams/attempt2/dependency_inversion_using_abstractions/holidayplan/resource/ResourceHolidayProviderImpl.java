package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.holidayplan.resource;

import com.google.common.collect.Maps;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.holidayplan.HolidayPlan;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.holidayplan.HolidayPlanId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.resource.ResourceHolidayProvider;
import java.time.LocalDate;
import java.util.Map;

// TODO: Find better name
public class ResourceHolidayProviderImpl implements ResourceHolidayProvider {

	private final HolidayPlanRepository holidayPlanRepository;

	private final Map<HolidayPlanId, HolidayPlan> holidayPlanCache;

	public ResourceHolidayProviderImpl(HolidayPlanRepository holidayPlanRepository) {
		this.holidayPlanRepository = holidayPlanRepository;
		this.holidayPlanCache = Maps.newHashMap();
	}

	@Override
	public boolean isHolidayOn(LocalDate date, HolidayPlanId holidayPlanId) {
		HolidayPlan holidayPlan = getFromCache(holidayPlanId);
		return holidayPlan.isHolidayOn(date);
	}

	private HolidayPlan getFromCache(HolidayPlanId holidayPlanId) {
		HolidayPlan holidayPlan = holidayPlanCache.get(holidayPlanId);
		if (holidayPlan == null) {
			holidayPlan = holidayPlanRepository.get(holidayPlanId);
			holidayPlanCache.put(holidayPlan.id(), holidayPlan);
		}
		return holidayPlan;
	}
}
