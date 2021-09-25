package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_1.typical_implementation.domain;

import java.time.LocalDate;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.PrintUtil.printServiceMessage;

public class TaskService {

	public void handleResourceCapacityChange(ResourceId resourceId, LocalDate date, Capacity oldCapacity, Capacity newCapacity) {
		printServiceMessage(this, "Handles resource '%s' capacity change", resourceId);
	}
}
