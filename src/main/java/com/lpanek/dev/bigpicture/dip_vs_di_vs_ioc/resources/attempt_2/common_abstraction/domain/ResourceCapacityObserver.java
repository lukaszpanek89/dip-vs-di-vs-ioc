package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.domain;

import java.time.LocalDate;

public interface ResourceCapacityObserver {

	void onResourceCapacityChange(ResourceId resourceId, LocalDate date, Capacity oldCapacity, Capacity newCapacity);
}
