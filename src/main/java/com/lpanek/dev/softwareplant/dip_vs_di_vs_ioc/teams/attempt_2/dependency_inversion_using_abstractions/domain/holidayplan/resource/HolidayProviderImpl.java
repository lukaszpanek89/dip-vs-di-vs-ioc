package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.holidayplan.resource;

import com.google.common.collect.Maps;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.holidayplan.HolidayPlan;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.holidayplan.HolidayPlanRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.HolidayPlanId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.HolidayProvider;
import java.time.LocalDate;
import java.util.Map;

public class HolidayProviderImpl implements HolidayProvider {

	private final HolidayPlanRepository holidayPlanRepository;

	// If this Provider is reused between use cases, then below cache should be cleared before each use case
	private final Map<HolidayPlanId, HolidayPlan> holidayPlanCache;

	public HolidayProviderImpl(HolidayPlanRepository holidayPlanRepository) {
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
			holidayPlan = holidayPlanRepository.get(toDomain(holidayPlanId));
			holidayPlanCache.put(holidayPlanId, holidayPlan);
		}
		return holidayPlan;
	}

	private com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.holidayplan.HolidayPlanId toDomain(
			HolidayPlanId holidayPlanId) {
		return new com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.holidayplan.HolidayPlanId(
				holidayPlanId.internal());
	}
}
