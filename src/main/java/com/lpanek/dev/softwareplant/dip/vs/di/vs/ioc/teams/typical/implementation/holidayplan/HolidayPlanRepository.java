package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.holidayplan;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.Constants.POLISH_HOLIDAY_PLAN_ID;

public class HolidayPlanRepository {

	private final Map<HolidayPlanId, HolidayPlanAnemia> repository = createPrepopulatedRepository();

	public HolidayPlan get(HolidayPlanId holidayPlanId) {
		HolidayPlanAnemia holidayPlanAnemia = repository.get(holidayPlanId);
		if (holidayPlanAnemia == null) {
			throw new RuntimeException(String.format("Holiday plan with id '%s' does not exist", holidayPlanId));
		}
		return new HolidayPlan(holidayPlanAnemia);
	}

	private Map<HolidayPlanId, HolidayPlanAnemia> createPrepopulatedRepository() {
		Map<HolidayPlanId, HolidayPlanAnemia> repository = new HashMap<>();

		Set<LocalDate> holidays = new HashSet<>();
		holidays.add(date("2019-05-01"));
		holidays.add(date("2019-05-03"));
		HolidayPlanAnemia holidayPlanAnemia = new HolidayPlanAnemia(POLISH_HOLIDAY_PLAN_ID, holidays);
		repository.put(holidayPlanAnemia.id(), holidayPlanAnemia);

		return repository;
	}

	private LocalDate date(String date) {
		return LocalDate.parse(date);
	}
}
