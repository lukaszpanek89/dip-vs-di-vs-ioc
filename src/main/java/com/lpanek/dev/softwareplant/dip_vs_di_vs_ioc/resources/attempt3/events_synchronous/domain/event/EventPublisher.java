package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous.domain.event;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class EventPublisher {

	private Multimap<Class<? extends Event>, EventSubscriber> subscribers = ArrayListMultimap.create();

	public <EVENT_TYPE extends Event> void subscribe(Class<EVENT_TYPE> eventClass, EventSubscriber<EVENT_TYPE> eventSubscriber) {
		subscribers.put(eventClass, eventSubscriber);
	}

	@SuppressWarnings("unchecked")
	public <EVENT_TYPE extends Event> void publish(EVENT_TYPE event) {
		printServiceMessage(this, "About to notify subscribers about event %s", event);

		Collection<EventSubscriber> subscribersOfGivenEvent = subscribers.get(event.getClass());
		Collection<EventSubscriber> subscribersOfAllEvents = subscribers.get(Event.class);
		for (EventSubscriber subscriber : sum(subscribersOfGivenEvent, subscribersOfAllEvents)) {
			try {
				subscriber.handleEvent(event);
			} catch (RuntimeException e) {
				// Exception handling goes here...
			}
		}

		printServiceMessage(this, "Subscribers notified about event %s", event);
	}

	private Collection<EventSubscriber> sum(Collection<EventSubscriber> subscribers1, Collection<EventSubscriber> subscribers2) {
		Set<EventSubscriber> sum = Sets.newHashSet();
		sum.addAll(subscribers1);
		sum.addAll(subscribers2);
		return sum;
	}

}
