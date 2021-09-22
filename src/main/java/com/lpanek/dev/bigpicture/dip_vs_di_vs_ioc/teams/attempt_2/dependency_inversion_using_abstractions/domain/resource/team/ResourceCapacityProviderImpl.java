package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.team;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.common.Capacity;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.common.DatePeriod;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.Resource;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.ResourceId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.ResourceRepository;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.team.ResourceCapacityProvider;

public class ResourceCapacityProviderImpl implements ResourceCapacityProvider {

	private final ResourceRepository resourceRepository;

	public ResourceCapacityProviderImpl(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	@Override
	public Capacity getCapacityFor(DatePeriod period, com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.team.ResourceId resourceId) {
		Resource resource = resourceRepository.get(toDomain(resourceId));
		return resource.getCapacityFor(period);
	}

	private ResourceId toDomain(
			com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.team.ResourceId resourceId) {
		return new ResourceId(resourceId.internal());
	}
}
