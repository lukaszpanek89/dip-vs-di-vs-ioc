package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.attempt2.common_interface;

import java.util.HashMap;
import java.util.Map;

public class ResourceRepository {

	private final Map<ResourceId, ResourceAnemia> repository = createPrepopulatedRepository();

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

	private Map<ResourceId, ResourceAnemia> createPrepopulatedRepository() {
		Map<ResourceId, ResourceAnemia> repository = new HashMap<>();

		ResourceId resourceId = new ResourceId("john.doe");
		ResourceAnemia resourceAnemia = new ResourceAnemia(resourceId);
		repository.put(resourceId, resourceAnemia);

		return repository;
	}
}
