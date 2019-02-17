package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt4.events_asynchronous.domain.event.EventStore;
import java.util.List;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class ResourceCapacityChangedEventProcessor {

	private final EventStore eventStore;

	private final TeamService teamService;

	private final TaskService taskService;

	public ResourceCapacityChangedEventProcessor(EventStore eventStore, TeamService teamService, TaskService taskService) {
		this.eventStore = eventStore;
		this.teamService = teamService;
		this.taskService = taskService;
	}

	public void processEvents() {
		List<ResourceCapacityChangedEvent> events = eventStore.getNotYetProcessedEvents(ResourceCapacityChangedEvent.class);
		if (events.isEmpty()) {
			printServiceMessage(this, "No events to process\n");
			return;
		}

		printServiceMessage(this, "About to process %d event(s)", events.size());
		for (int i = 0; i < events.size(); ++i) {
			ResourceCapacityChangedEvent event = events.get(i);
			try {
				teamService.handleEvent(event);
				taskService.handleEvent(event);
			} catch (RuntimeException e) {
				// Exception handling goes here...
				if (i > 0) {
					ResourceCapacityChangedEvent lastSuccessfullyProcessedEvent = events.get(i - 1);
					eventStore.markEventsUntilGivenAsProcessed(lastSuccessfullyProcessedEvent);
					printServiceMessage(this, "Processed only %d event(s) out of %d because of an error\n", i - 1, events.size());
				}
				return;
			}
		}
		eventStore.markEventsUntilGivenAsProcessed(events.get(events.size() - 1));
		printServiceMessage(this, "Processed all %d event(s)\n", events.size());
	}
}
