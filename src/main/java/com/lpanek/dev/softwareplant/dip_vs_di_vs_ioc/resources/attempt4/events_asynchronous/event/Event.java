package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.event;

import java.time.ZonedDateTime;

public abstract class Event {

	protected final EventId id;

	protected final ZonedDateTime timestamp;

	protected Event(ZonedDateTime timestamp) {
		this.id = new EventId();
		this.timestamp = timestamp;
	}

	public EventId id() {
		return id;
	}

	public ZonedDateTime timestamp() {
		return timestamp;
	}
}
