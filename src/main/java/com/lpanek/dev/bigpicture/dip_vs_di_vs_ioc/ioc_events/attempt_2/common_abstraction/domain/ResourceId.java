package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_2.common_abstraction.domain;

import com.google.common.base.Objects;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Immutable;

@Immutable
public final class ResourceId {

	private final String id;

	public ResourceId(String id) {
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
		ResourceId that = (ResourceId) o;
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
