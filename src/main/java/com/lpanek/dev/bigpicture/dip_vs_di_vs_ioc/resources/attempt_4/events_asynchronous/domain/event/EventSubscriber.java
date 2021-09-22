package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.event;

public interface EventSubscriber<EVENT_TYPE extends Event> {

	void handleEvent(EVENT_TYPE event);
}
