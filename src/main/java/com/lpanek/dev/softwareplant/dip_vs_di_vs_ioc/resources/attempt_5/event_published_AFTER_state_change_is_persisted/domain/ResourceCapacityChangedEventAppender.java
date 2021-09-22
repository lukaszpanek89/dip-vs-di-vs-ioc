package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.event.EventStore;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.event.EventSubscriber;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

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
