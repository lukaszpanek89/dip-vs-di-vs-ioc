package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.event.EventSubscriber;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TeamService implements EventSubscriber<ResourceCapacityChangedEvent> {

	public void handleEvent(ResourceCapacityChangedEvent event) {
		// TODO: Implement this
		System.out.println(String.format("%s handles %s with timestamp %s",
				getClass().getSimpleName(), event.getClass().getSimpleName(), format(event.timestamp())));
	}

	private String format(ZonedDateTime timestamp) {
		return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
