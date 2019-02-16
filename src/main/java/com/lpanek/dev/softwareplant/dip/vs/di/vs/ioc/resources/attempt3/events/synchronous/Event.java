package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.attempt3.events.synchronous;

import java.time.ZonedDateTime;

public abstract class Event {

	private final ZonedDateTime timestamp;

	protected Event(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public ZonedDateTime timestamp() {
		return timestamp;
	}
}
