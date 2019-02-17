package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt1.typical_implementation.domain;

import java.time.LocalDate;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class TeamService {

	public void handleResourceCapacityChange(ResourceId resourceId, LocalDate date, Capacity oldCapacity, Capacity newCapacity) {
		printServiceMessage(this, "Handles resource '%s' capacity change", resourceId);
	}
}
