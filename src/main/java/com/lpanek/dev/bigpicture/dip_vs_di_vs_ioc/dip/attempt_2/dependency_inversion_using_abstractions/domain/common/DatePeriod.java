package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.common;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Immutable;
import java.time.LocalDate;

@Immutable
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
