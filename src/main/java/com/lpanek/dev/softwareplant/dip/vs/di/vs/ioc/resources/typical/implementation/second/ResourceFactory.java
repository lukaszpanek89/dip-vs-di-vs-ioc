package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.typical.implementation.second;

import java.util.List;

public class ResourceFactory {

	private final List<ResourceCapacityObserver> resourceCapacityObservers;

	public ResourceFactory(List<ResourceCapacityObserver> resourceCapacityObservers) {
		this.resourceCapacityObservers = resourceCapacityObservers;
	}

	public Resource recreate(ResourceAnemia resourceAnemia) {
		return new Resource(resourceAnemia, resourceCapacityObservers);
	}
}
