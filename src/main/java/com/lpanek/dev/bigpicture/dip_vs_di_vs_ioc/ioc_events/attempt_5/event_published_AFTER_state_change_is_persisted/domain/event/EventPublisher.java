package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_5.event_published_AFTER_state_change_is_persisted.domain.event;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.PrintUtil.printServiceMessage;

public class EventPublisher {

	private Multimap<Class<? extends Event>, EventSubscriber> subscribers = ArrayListMultimap.create();

	public <EVENT_TYPE extends Event> void subscribe(Class<EVENT_TYPE> eventClass, EventSubscriber<EVENT_TYPE> eventSubscriber) {
		subscribers.put(eventClass, eventSubscriber);
	}

	@SuppressWarnings("unchecked")
	public <EVENT_TYPE extends Event> void publish(EVENT_TYPE event) {
		Collection<EventSubscriber> subscribersOfGivenEvent = subscribers.get(event.getClass());
		Collection<EventSubscriber> subscribersOfAllEvents = subscribers.get(Event.class);
		Collection<EventSubscriber> subscribersSum = sum(subscribersOfGivenEvent, subscribersOfAllEvents);
		printServiceMessage(this, "About to notify %d subscriber(s) about event %s", subscribersSum.size(), event);

		for (EventSubscriber subscriber : subscribersSum) {
			try {
				subscriber.handleEvent(event);
			} catch (RuntimeException e) {
				// Exception handling goes here...
			}
		}

		printServiceMessage(this, "%d subscriber(s) notified about event %s", subscribersSum.size(), event);
	}

	private Collection<EventSubscriber> sum(Collection<EventSubscriber> subscribers1, Collection<EventSubscriber> subscribers2) {
		Set<EventSubscriber> sum = Sets.newHashSet();
		sum.addAll(subscribers1);
		sum.addAll(subscribers2);
		return sum;
	}

}
