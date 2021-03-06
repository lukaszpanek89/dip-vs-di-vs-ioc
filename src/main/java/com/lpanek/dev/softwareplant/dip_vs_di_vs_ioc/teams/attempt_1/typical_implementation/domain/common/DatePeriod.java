package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.common;

import java.time.LocalDate;

public final class DatePeriod {

	private final LocalDate startDate;

	private final LocalDate endDate;

	public DatePeriod(LocalDate startDate, LocalDate endDate) {
		if (startDate.isAfter(endDate)) {
			throw new RuntimeException(String.format("Start date '%s' cannot be after end date '%s'", startDate, endDate));
		}
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public LocalDate startDate() {
		return startDate;
	}

	public LocalDate endDate() {
		return endDate;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", startDate, endDate);
	}
}
