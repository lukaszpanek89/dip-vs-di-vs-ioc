package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.team;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.common.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.common.DatePeriod;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.Resource;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.ResourceRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.team.ResourceCapacityProvider;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.team.ResourceId;

public class ResourceCapacityProviderImpl implements ResourceCapacityProvider {

	private final ResourceRepository resourceRepository;

	public ResourceCapacityProviderImpl(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	@Override
	public Capacity getCapacityFor(DatePeriod period, ResourceId resourceId) {
		Resource resource = resourceRepository.get(toDomain(resourceId));
		return resource.getCapacityFor(period);
	}

	private com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.ResourceId toDomain(
			ResourceId resourceId) {
		return new com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.resource.ResourceId(resourceId.internal());
	}
}
