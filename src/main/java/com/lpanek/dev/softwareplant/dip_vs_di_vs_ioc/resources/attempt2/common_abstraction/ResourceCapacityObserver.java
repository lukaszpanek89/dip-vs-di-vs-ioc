package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt2.common_abstraction;

public interface ResourceCapacityObserver {

	void onResourceCapacityChange(ResourceId resourceId, Capacity oldCapacity, Capacity newCapacity);
}
