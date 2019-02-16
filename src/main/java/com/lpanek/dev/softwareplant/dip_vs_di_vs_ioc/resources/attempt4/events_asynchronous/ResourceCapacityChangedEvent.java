package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous;

import com.google.common.base.Objects;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.event.Event;
import java.time.ZonedDateTime;

public final class ResourceCapacityChangedEvent extends Event {

	private final ResourceId resourceId;

	private final Capacity oldCapacity;

	private final Capacity newCapacity;

	public ResourceCapacityChangedEvent(ZonedDateTime timestamp, ResourceId resourceId, Capacity oldCapacity, Capacity newCapacity) {
		super(timestamp);
		this.resourceId = resourceId;
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
				&& Objects.equal(oldCapacity, that.oldCapacity)
				&& Objects.equal(newCapacity, that.newCapacity);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, timestamp, resourceId, oldCapacity, newCapacity);
	}
}
