package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_2.common_abstraction.domain;

import com.google.common.collect.Maps;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.DateUtil.date;
import static java.util.concurrent.TimeUnit.HOURS;

public class ResourceRepository {

	private final Map<ResourceId, ResourceAnemia> repository = createRepositoryWithInitialData();

	private final ResourceFactory resourceFactory;

	public ResourceRepository(ResourceFactory resourceFactory) {
		this.resourceFactory = resourceFactory;
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
