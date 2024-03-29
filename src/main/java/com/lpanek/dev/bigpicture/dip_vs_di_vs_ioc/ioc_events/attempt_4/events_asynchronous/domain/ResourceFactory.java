package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_4.events_asynchronous.domain;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_4.events_asynchronous.domain.event.EventPublisher;

public class ResourceFactory {

	private final EventPublisher eventPublisher;

	public ResourceFactory(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public Resource recreate(ResourceAnemia resourceAnemia) {
		return new Resource(resourceAnemia, eventPublisher);
	}
}
