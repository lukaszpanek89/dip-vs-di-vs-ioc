package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_4.events_asynchronous.domain.event;

public interface EventSubscriber<EVENT_TYPE extends Event> {

	void handleEvent(EVENT_TYPE event);
}
