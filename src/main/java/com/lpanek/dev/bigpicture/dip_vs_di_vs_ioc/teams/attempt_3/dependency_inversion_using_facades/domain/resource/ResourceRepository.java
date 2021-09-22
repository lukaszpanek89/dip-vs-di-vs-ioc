package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.resource;

import java.util.HashMap;
import java.util.Map;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.RepositoriesInitialData.FULL_TIME_WORKLOAD_PLAN_ID;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.RepositoriesInitialData.JOHN_ID;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.RepositoriesInitialData.KATHY_ID;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.RepositoriesInitialData.POLISH_HOLIDAY_PLAN_ID;

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
		repository.put(resource.id(), resource.toAnemia());
	}

	public void delete(ResourceId resourceId) {
		repository.remove(resourceId);
	}

	private Map<ResourceId, ResourceAnemia> createRepositoryWithInitialData() {
		Map<ResourceId, ResourceAnemia> repository = new HashMap<>();

		ResourceAnemia resource1Anemia = new ResourceAnemia(JOHN_ID, FULL_TIME_WORKLOAD_PLAN_ID, POLISH_HOLIDAY_PLAN_ID);
		repository.put(resource1Anemia.id(), resource1Anemia);

		ResourceAnemia resource2Anemia = new ResourceAnemia(KATHY_ID, FULL_TIME_WORKLOAD_PLAN_ID, POLISH_HOLIDAY_PLAN_ID);
		repository.put(resource2Anemia.id(), resource2Anemia);

		return repository;
	}
}
