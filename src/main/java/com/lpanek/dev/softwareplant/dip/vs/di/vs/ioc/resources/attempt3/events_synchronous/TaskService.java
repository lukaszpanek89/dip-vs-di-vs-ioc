package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.attempt3.events_synchronous;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TaskService implements EventSubscriber<ResourceCapacityChangedEvent> {

	public void handleEvent(ResourceCapacityChangedEvent event) {
		// TODO: Implement this
		System.out.println(String.format("%s handles %s with timestamp %s",
				getClass().getSimpleName(), event.getClass().getSimpleName(), format(event.timestamp())));
	}

	private String format(ZonedDateTime timestamp) {
		return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
