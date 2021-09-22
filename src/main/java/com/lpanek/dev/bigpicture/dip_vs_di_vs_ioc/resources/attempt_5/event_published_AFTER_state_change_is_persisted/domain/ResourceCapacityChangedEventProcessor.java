package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.event.EventStore;
import java.util.List;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class ResourceCapacityChangedEventProcessor {

	private final EventStore eventStore;

	private final List<ResourceCapacityObserver> observers;

	public ResourceCapacityChangedEventProcessor(EventStore eventStore, List<ResourceCapacityObserver> observers) {
		this.eventStore = eventStore;
		this.observers = observers;
	}

	public void processEvents() {
		List<ResourceCapacityChangedEvent> events = eventStore.getNotYetProcessedEvents(ResourceCapacityChangedEvent.class);
		if (events.isEmpty()) {
			printServiceMessage(this, "No events to process\n");
			return;
		}

		printServiceMessage(this, "About to notify %d observer(s) about %d event(s)", observers.size(), events.size());
		for (int i = 0; i < events.size(); ++i) {
			ResourceCapacityChangedEvent event = events.get(i);
			try {
				for (ResourceCapacityObserver observer : observers) {
					observer.onCapacityChange(event);
				}
			} catch (RuntimeException e) {
				// Exception handling goes here...
				if (i > 0) {
					ResourceCapacityChangedEvent lastSuccessfullyProcessedEvent = events.get(i - 1);
					eventStore.markEventsUntilGivenAsProcessed(lastSuccessfullyProcessedEvent);
					printServiceMessage(this, "(s) processed only %d event(s) out of %d because of an error\n", observers.size(), i - 1, events.size());
				}
				return;
			}
		}
		eventStore.markEventsUntilGivenAsProcessed(events.get(events.size() - 1));
		printServiceMessage(this, "%d observer(s) processed %d event(s)\n", observers.size(), events.size());
	}
}
