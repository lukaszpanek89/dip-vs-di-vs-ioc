package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.workloadplan;

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