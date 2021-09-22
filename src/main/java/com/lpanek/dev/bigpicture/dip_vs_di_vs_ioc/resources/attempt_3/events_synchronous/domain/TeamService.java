package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous.domain;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_3.events_synchronous.domain.event.EventSubscriber;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class TeamService implements EventSubscriber<ResourceCapacityChangedEvent> {

	public void handleEvent(ResourceCapacityChangedEvent event) {
		printServiceMessage(this, "Handles event %s", event);
	}
}
