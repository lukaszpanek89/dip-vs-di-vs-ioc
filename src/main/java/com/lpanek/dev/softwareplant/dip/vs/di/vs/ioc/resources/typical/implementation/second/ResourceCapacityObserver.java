package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.typical.implementation.second;

public interface ResourceCapacityObserver {

	void onResourceCapacityChange(ResourceId resourceId, Capacity oldCapacity, Capacity newCapacity);
}
