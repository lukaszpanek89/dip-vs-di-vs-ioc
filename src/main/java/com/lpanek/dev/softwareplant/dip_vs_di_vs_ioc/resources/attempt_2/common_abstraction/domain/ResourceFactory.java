package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.domain;

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
