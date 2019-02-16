package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.attempt2.common_interface;

public interface ResourceCapacityObserver {

	void onResourceCapacityChange(ResourceId resourceId, Capacity oldCapacity, Capacity newCapacity);
}
