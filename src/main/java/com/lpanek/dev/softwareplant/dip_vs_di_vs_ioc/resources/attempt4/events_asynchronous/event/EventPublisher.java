package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.event;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Streams;
import java.util.Collection;

public class EventPublisher {

	private static final ThreadLocal<Multimap<Class<? extends Event>, EventSubscriber>> SUBSCRIBERS = ThreadLocal.withInitial(ArrayListMultimap::create);

	private static final ThreadLocal<Boolean> IS_PUBLISHING = ThreadLocal.withInitial(() -> Boolean.FALSE);

	private EventPublisher() {
		// Intentionally left empty
	}

	public static EventPublisher instance() {
		return new EventPublisher();
	}

	public <EVENT_TYPE extends Event> void subscribe(Class<EVENT_TYPE> eventClass, EventSubscriber<EVENT_TYPE> eventSubscriber) {
		if (isPublishing()) {
			return;
		}
		subscribers().put(eventClass, eventSubscriber);
	}

	@SuppressWarnings("unchecked")
	public <EVENT_TYPE extends Event> void publish(EVENT_TYPE event) {
		if (isPublishing()) {
			return;
		}
		setPublishing(true);
		try {
			Collection<EventSubscriber> subscribersOfGivenEvent = subscribers().get(event.getClass());
			Collection<EventSubscriber> subscribersOfAllEvents = subscribers().get(Event.class);
			Streams.concat(subscribersOfGivenEvent.stream(), subscribersOfAllEvents.stream()).forEach(
					subscriber -> subscriber.handleEvent(event)
			);
		} finally {
			setPublishing(false);
		}
	}

	public void reset() {
		if (isPublishing()) {
			return;
		}
		subscribers().clear();
	}

	private Multimap<Class<? extends Event>, EventSubscriber> subscribers() {
		return SUBSCRIBERS.get();
	}

	private Boolean isPublishing() {
		return IS_PUBLISHING.get();
	}

	private void setPublishing(Boolean isPublishing) {
		IS_PUBLISHING.set(isPublishing);
	}
}
