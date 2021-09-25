package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_aop.attempt_1.typical_implementation.application;

import com.google.common.base.Objects;
import java.util.UUID;

public class Box {

	private final BoxId id;

	private final String name;

	private final DatePeriod period;

	private Box(BoxId id, String name, DatePeriod period) {
		this.id = id;
		this.name = name;
		this.period = period;
	}

	public static Box from(CreateBoxRequest request) {
		return new Box(generateId(), request.name(), request.period());
	}

	private static BoxId generateId() {
		String uuid = UUID.randomUUID().toString();
		return new BoxId(uuid);
	}

	public BoxId id() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Box that = (Box) o;
		return Objects.equal(id(), that.id());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id());
	}

	@Override
	public String toString() {
		return String.format("'%s'", name);
	}
}
