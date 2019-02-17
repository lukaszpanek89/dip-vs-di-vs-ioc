package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.event.EventSubscriber;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class TaskService implements EventSubscriber<ResourceCapacityChangedEvent> {

	public void handleEvent(ResourceCapacityChangedEvent event) {
		printServiceMessage(this, "Handles event %s", event);
	}
}
