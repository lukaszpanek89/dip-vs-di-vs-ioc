package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous.domain;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous.domain.event.EventSubscriber;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class TeamService implements EventSubscriber<ResourceCapacityChangedEvent> {

	public void handleEvent(ResourceCapacityChangedEvent event) {
		printServiceMessage(this, "Handles event %s", event);
	}
}
