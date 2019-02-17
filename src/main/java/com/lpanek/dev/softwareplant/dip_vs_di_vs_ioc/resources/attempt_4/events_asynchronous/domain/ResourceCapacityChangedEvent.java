package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain;

import com.google.common.base.Objects;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.event.Event;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public final class ResourceCapacityChangedEvent extends Event {

	private final ResourceId resourceId;

	private final LocalDate date;

	private final Capacity oldCapacity;

	private final Capacity newCapacity;

	public ResourceCapacityChangedEvent(ZonedDateTime timestamp, ResourceId resourceId, LocalDate date, Capacity oldCapacity, Capacity newCapacity) {
		super(timestamp);
		this.resourceId = resourceId;
		this.date = date;
		this.oldCapacity = oldCapacity;
		this.newCapacity = newCapacity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResourceCapacityChangedEvent that = (ResourceCapacityChangedEvent) o;
		return Objects.equal(id, that.id)
				&& Objects.equal(timestamp, that.timestamp)
				&& Objects.equal(resourceId, that.resourceId)
				&& Objects.equal(date, that.date)
				&& Objects.equal(oldCapacity, that.oldCapacity)
				&& Objects.equal(newCapacity, that.newCapacity);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, timestamp, resourceId, date, oldCapacity, newCapacity);
	}
}
