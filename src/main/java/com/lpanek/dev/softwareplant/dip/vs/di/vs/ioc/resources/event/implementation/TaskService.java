package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.event.implementation;

public class TaskService implements EventSubscriber<ResourceCapacityChangedEvent> {

	public void handleEvent(ResourceCapacityChangedEvent event) {
		// TODO: Implement this
		System.out.println(String.format("%s handles event %s with timestamp %s",
				getClass().getSimpleName(), event.getClass().getSimpleName(), event.timestamp()));
	}
}
