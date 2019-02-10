package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.event.implementation;

public interface EventSubscriber<EVENT_TYPE extends Event> {

	void handleEvent(EVENT_TYPE event);
}
