package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_5.event_published_AFTER_state_change_is_persisted.domain;

import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.PrintUtil.printServiceMessage;

public class TaskService implements ResourceCapacityObserver {

	public void onCapacityChange(ResourceCapacityChangedEvent event) {
		printServiceMessage(this, "Handles event %s", event);
	}
}
