package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_3.events_synchronous.domain.event;

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

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
