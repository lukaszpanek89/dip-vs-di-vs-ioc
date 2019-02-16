package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.event;

public class EventStoreAppender implements EventSubscriber<Event> {

	private final EventStore eventStore;

	public EventStoreAppender(EventStore eventStore) {
		this.eventStore = eventStore;
	}

	@Override
	public void handleEvent(Event event) {
		eventStore.append(event);
	}
}
