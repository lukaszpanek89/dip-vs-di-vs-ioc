package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.event.EventStore;
import java.util.List;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

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
			printServiceMessage(this, "No unprocessed events found in store\n");
			return;
		}

		printServiceMessage(this, "Notifies %d observer(s) about %d event(s) found in store", observers.size(), events.size());
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
				}
				return;
			}
		}
		eventStore.markEventsUntilGivenAsProcessed(events.get(events.size() - 1));
	}
}
