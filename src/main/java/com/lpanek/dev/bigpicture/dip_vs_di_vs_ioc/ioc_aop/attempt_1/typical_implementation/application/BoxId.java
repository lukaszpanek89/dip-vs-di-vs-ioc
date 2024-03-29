package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_aop.attempt_1.typical_implementation.application;

import com.google.common.base.Objects;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Immutable;

@Immutable
public final class BoxId {

	private final String id;

	public BoxId(String id) {
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
		BoxId that = (BoxId) o;
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
