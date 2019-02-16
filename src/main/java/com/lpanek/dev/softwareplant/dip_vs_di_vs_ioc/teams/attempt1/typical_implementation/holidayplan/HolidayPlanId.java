package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.holidayplan;

import com.google.common.base.Objects;

public final class HolidayPlanId {

	private final String id;

	public HolidayPlanId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		HolidayPlanId that = (HolidayPlanId) o;
		return Objects.equal(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return id;
	}
}
