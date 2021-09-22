package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.resource;

import com.google.common.collect.Maps;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.holidayplan.HolidayPlan;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.holidayplan.HolidayPlanId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.holidayplan.HolidayPlanRepository;
import java.time.LocalDate;
import java.util.Map;

public class HolidayProvider {

	private final HolidayPlanRepository holidayPlanRepository;

	// If this Provider is reused between use cases, then below cache should be cleared before each use case
	private final Map<HolidayPlanId, HolidayPlan> holidayPlanCache;

	public HolidayProvider(HolidayPlanRepository holidayPlanRepository) {
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
