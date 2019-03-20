package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain;

import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class TaskService implements ResourceCapacityObserver {

	public void onCapacityChange(ResourceCapacityChangedEvent event) {
		printServiceMessage(this, "Handles event\n");
	}
}
