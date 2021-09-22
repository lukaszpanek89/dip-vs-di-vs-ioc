package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.event.EventStore;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.event.EventSubscriber;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class ResourceCapacityChangedEventAppender implements EventSubscriber<ResourceCapacityChangedEvent> {

	private final EventStore eventStore;

	public ResourceCapacityChangedEventAppender(EventStore eventStore) {
		this.eventStore = eventStore;
	}

	@Override
	public void handleEvent(ResourceCapacityChangedEvent event) {
		printServiceMessage(this, "Handles event %s", event);
		eventStore.append(event);
	}
}
