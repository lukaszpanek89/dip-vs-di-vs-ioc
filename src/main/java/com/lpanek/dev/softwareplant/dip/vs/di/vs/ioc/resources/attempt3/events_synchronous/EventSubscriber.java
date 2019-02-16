package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.attempt3.events_synchronous;

public interface EventSubscriber<EVENT_TYPE extends Event> {

	void handleEvent(EVENT_TYPE event);
}
