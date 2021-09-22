package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain;

import com.google.common.collect.Maps;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain.event.EventPublisher;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.date;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.printServiceMessage;
import static java.util.concurrent.TimeUnit.HOURS;

public class ResourceRepository {

	private final Map<ResourceId, ResourceAnemia> repository = createRepositoryWithInitialData();

	private final ResourceFactory resourceFactory;

	private final EventPublisher eventPublisher;

	public ResourceRepository(ResourceFactory resourceFactory, EventPublisher eventPublisher) {
		this.resourceFactory = resourceFactory;
		this.eventPublisher = eventPublisher;
	}

	public Resource get(ResourceId resourceId) {
		ResourceAnemia resourceAnemia = repository.get(resourceId);
		if (resourceAnemia == null) {
			throw new RuntimeException(String.format("Resource with id '%s' does not exist", resourceId));
		}
		return resourceFactory.recreate(resourceAnemia);
	}

	public void save(Resource resource) {
		ResourceId resourceId = resource.id();
		if (repository.get(resourceId) == null) {
			throw new RuntimeException(String.format("Resource with id '%s' does not exist", resourceId));
		}
		ResourceAnemia resourceAnemia = resource.toAnemia();
		repository.put(resourceId, resourceAnemia);

		List<ResourceCapacityChangedEvent> resourceEvents = resource.flushEvents();
		printServiceMessage(this, "About to publish list of events: %s", resourceEvents);
		resourceEvents.forEach(eventPublisher::publish);
		printServiceMessage(this, "List of events: %s published\n", resourceEvents);
	}

	private Map<ResourceId, ResourceAnemia> createRepositoryWithInitialData() {
		Map<ResourceId, ResourceAnemia> repository = new HashMap<>();

		ResourceId resourceId = new ResourceId("john.doe");
		Map<LocalDate, Capacity> dailyCapacities = Maps.newHashMap();
		dailyCapacities.put(date("2019-02-25"), new Capacity(6, HOURS));
		ResourceAnemia resourceAnemia = new ResourceAnemia(resourceId, dailyCapacities);
		repository.put(resourceId, resourceAnemia);

		return repository;
	}
}
