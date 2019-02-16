package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.workloadplan;

import com.google.common.base.Objects;

public final class WorkloadPlanId {

	private final String id;

	public WorkloadPlanId(String id) {
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
		WorkloadPlanId that = (WorkloadPlanId) o;
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