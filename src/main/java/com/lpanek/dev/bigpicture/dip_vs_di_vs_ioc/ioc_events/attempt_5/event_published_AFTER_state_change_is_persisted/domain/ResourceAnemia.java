package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_5.event_published_AFTER_state_change_is_persisted.domain;

import com.google.common.base.Objects;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

final class ResourceAnemia {

	private final ResourceId id;

	private final Map<LocalDate, Capacity> dailyCapacities;

	ResourceAnemia(ResourceId id, Map<LocalDate, Capacity> dailyCapacities) {
		this.id = id;
		this.dailyCapacities = Collections.unmodifiableMap(dailyCapacities);
	}

	ResourceId id() {
		return id;
	}

	Map<LocalDate, Capacity> dailyCapacities() {
		return dailyCapacities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResourceAnemia that = (ResourceAnemia) o;
		return Objects.equal(id, that.id)
				&& Objects.equal(dailyCapacities, that.dailyCapacities);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, dailyCapacities);
	}
}