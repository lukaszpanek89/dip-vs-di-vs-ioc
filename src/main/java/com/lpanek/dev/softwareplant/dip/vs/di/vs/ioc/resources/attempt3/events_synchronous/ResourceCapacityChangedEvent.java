package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.attempt3.events_synchronous;

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
}
