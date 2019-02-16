package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.attempt3.events.synchronous;

import java.time.ZonedDateTime;

public class Resource {

	private final ResourceAnemia anemia;

	public Resource(ResourceAnemia anemia) {
		this.anemia = anemia;
	}

	public ResourceId id() {
		return anemia.id();
	}

	public ResourceAnemia toAnemia() {
		// TODO: Implement this
		return null;
	}

	public void changeCapacityTo(Capacity newCapacity) {
		Capacity oldCapacity = null;
		// Capacity change is validated here...
		// Capacity change is done here...

		ResourceCapacityChangedEvent event = new ResourceCapacityChangedEvent(ZonedDateTime.now(), anemia.id(), oldCapacity, newCapacity);
		EventPublisher.instance().publish(event);
	}
}
