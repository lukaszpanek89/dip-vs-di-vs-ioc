package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.resource;

import com.google.common.collect.Maps;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.holidayplan.HolidayPlan;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.holidayplan.HolidayPlanId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.holidayplan.HolidayPlanRepository;
import java.time.LocalDate;
import java.util.Map;

public class ResourceHolidayProvider {

	private final HolidayPlanRepository holidayPlanRepository;

	private final Map<HolidayPlanId, HolidayPlan> holidayPlanCache;

	public ResourceHolidayProvider(HolidayPlanRepository holidayPlanRepository) {
		this.holidayPlanRepository = holidayPlanRepository;
		this.holidayPlanCache = Maps.newHashMap();
	}

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
