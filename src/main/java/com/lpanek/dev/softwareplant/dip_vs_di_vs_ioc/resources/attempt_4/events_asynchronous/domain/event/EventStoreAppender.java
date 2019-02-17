package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.event;

import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class EventStoreAppender implements EventSubscriber<Event> {

	private final EventStore eventStore;

	public EventStoreAppender(EventStore eventStore) {
		this.eventStore = eventStore;
	}

	@Override
	public void handleEvent(Event event) {
		printServiceMessage(this, "Handles event %s", event);
		eventStore.append(event);
	}
}
