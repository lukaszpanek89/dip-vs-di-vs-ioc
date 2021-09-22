package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.domain;

import java.time.LocalDate;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class TeamService implements ResourceCapacityObserver {

	public void onResourceCapacityChange(ResourceId resourceId, LocalDate date, Capacity oldCapacity, Capacity newCapacity) {
		printServiceMessage(this, "Handles resource '%s' capacity change", resourceId);
	}
}
