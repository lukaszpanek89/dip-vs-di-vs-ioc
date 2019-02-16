package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous;

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
