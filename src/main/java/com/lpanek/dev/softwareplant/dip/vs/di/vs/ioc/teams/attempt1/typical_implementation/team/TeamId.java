package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical_implementation.team;

import com.google.common.base.Objects;

public final class TeamId {

	private final String id;

	public TeamId(String id) {
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
		TeamId that = (TeamId) o;
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
