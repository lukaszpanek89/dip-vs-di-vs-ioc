package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.typical.implementation.second;

import java.util.List;

public class Resource {

	private final ResourceAnemia anemia;

	private final List<ResourceCapacityObserver> resourceCapacityObservers;

	public Resource(ResourceAnemia anemia, List<ResourceCapacityObserver> resourceCapacityObservers) {
		this.anemia = anemia;
		this.resourceCapacityObservers = resourceCapacityObservers;
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

		for (ResourceCapacityObserver observer : resourceCapacityObservers) {
			try {
				observer.onResourceCapacityChange(anemia.id(), oldCapacity, newCapacity);
			} catch (RuntimeException e) {
				// Exception handling goes here...
			}
		}
	}
}
