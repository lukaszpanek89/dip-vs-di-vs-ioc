package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt2.common_abstraction.domain;

import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class TeamService implements ResourceCapacityObserver {

	public void onResourceCapacityChange(ResourceId resourceId, Capacity oldCapacity, Capacity newCapacity) {
		printServiceMessage(this, "Handles resource '%s' capacity change", resourceId);
	}
}
