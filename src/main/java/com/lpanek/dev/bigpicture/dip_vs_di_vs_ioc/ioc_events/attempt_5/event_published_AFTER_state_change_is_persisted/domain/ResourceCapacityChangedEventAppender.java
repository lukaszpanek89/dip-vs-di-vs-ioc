package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_5.event_published_AFTER_state_change_is_persisted.domain;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_5.event_published_AFTER_state_change_is_persisted.domain.event.EventStore;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_5.event_published_AFTER_state_change_is_persisted.domain.event.EventSubscriber;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.PrintUtil.printServiceMessage;

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
