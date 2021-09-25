package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_5.event_published_AFTER_state_change_is_persisted.domain.event;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EventStore {

	private final Map<Class<? extends Event>, EventsOfClass> store;

	public EventStore() {
		this.store = Maps.newHashMap();
	}

	@SuppressWarnings("unchecked")
	public <EVENT_TYPE extends Event> void append(EVENT_TYPE event) {
		Class<EVENT_TYPE> eventClass = (Class<EVENT_TYPE>) event.getClass();
		EventsOfClass<EVENT_TYPE> eventsOfClass = getOrCreateEventsOfClass(eventClass);
		eventsOfClass.append(event);
	}

	public <EVENT_TYPE extends Event> List<EVENT_TYPE> getNotYetProcessedEvents(Class<EVENT_TYPE> eventClass) {
		EventsOfClass<EVENT_TYPE> eventsOfClass = getOrCreateEventsOfClass(eventClass);
		return eventsOfClass.getNotYetProcessedEvents();
	}

	@SuppressWarnings("unchecked")
	public <EVENT_TYPE extends Event> void markEventsUntilGivenAsProcessed(EVENT_TYPE lastProcessedEvent) {
		Class<EVENT_TYPE> eventClass = (Class<EVENT_TYPE>) lastProcessedEvent.getClass();
		EventsOfClass<EVENT_TYPE> eventsOfClass = getOrCreateEventsOfClass(eventClass);
		eventsOfClass.markEventsUntilGivenAsProcessed(lastProcessedEvent);
	}

	@SuppressWarnings("unchecked")
	private <EVENT_TYPE extends Event> EventsOfClass<EVENT_TYPE> getOrCreateEventsOfClass(Class<EVENT_TYPE> eventClass) {
		EventsOfClass<EVENT_TYPE> eventsOfClass = store.get(eventClass);
		if (eventsOfClass == null) {
			eventsOfClass = new EventsOfClass<>();
			store.put(eventClass, eventsOfClass);
		}
		return eventsOfClass;
	}

	class EventsOfClass<EVENT_TYPE extends Event> {

		private final List<EVENT_TYPE> events = Lists.newArrayList();

		private int indexOfFirstNotYetProcessedEvent = 0;

		void append(EVENT_TYPE event) {
			events.add(event);
		}

		List<EVENT_TYPE> getNotYetProcessedEvents() {
			if (indexOfFirstNotYetProcessedEvent == events.size()) {
				return Collections.emptyList();
			}
			List<EVENT_TYPE> notYetProcessedEvents = events.subList(indexOfFirstNotYetProcessedEvent, events.size());
			return Collections.unmodifiableList(notYetProcessedEvents);
		}

		void markEventsUntilGivenAsProcessed(EVENT_TYPE lastProcessedEvent) {
			for (int i = indexOfFirstNotYetProcessedEvent; i < events.size(); ++i) {
				EVENT_TYPE event = events.get(i);
				if (event.equals(lastProcessedEvent)) {
					indexOfFirstNotYetProcessedEvent = i + 1;
					return;
				}
			}
			throw new IllegalArgumentException(String.format("Event '%s' was not found (it does not exist or is already processed)", lastProcessedEvent.id()));
		}
	}
}
