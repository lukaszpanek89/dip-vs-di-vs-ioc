package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.event;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.Collection;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class EventPublisher {

	private Multimap<Class<? extends Event>, EventSubscriber> subscribers = ArrayListMultimap.create();

	public <EVENT_TYPE extends Event> void subscribe(Class<EVENT_TYPE> eventClass, EventSubscriber<EVENT_TYPE> eventSubscriber) {
		subscribers.put(eventClass, eventSubscriber);
	}

	@SuppressWarnings("unchecked")
	public <EVENT_TYPE extends Event> void publish(EVENT_TYPE event) {
		Collection<EventSubscriber> eventSubscribers = subscribers.get(event.getClass());
		printServiceMessage(this, "Notifies %d subscriber(s) about 1 event", eventSubscribers.size());

		for (EventSubscriber eventSubscriber : eventSubscribers) {
			try {
				eventSubscriber.handleEvent(event);
			} catch (RuntimeException e) {
				// Exception handling goes here...
			}
		}
	}

}
