package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain;

import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class TeamService implements ResourceCapacityObserver {

	public void onCapacityChange(ResourceCapacityChangedEvent event) {
		printServiceMessage(this, "Handles event %s", event);
	}
}
