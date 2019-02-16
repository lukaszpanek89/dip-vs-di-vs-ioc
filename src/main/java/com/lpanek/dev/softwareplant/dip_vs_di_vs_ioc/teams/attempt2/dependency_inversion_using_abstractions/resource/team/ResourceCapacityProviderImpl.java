package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.resource.team;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.common.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.common.DatePeriod;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.resource.Resource;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.resource.ResourceId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.resource.ResourceRepository;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.team.ResourceCapacityProvider;

// TODO: Find better name
public class ResourceCapacityProviderImpl implements ResourceCapacityProvider {

	private final ResourceRepository resourceRepository;

	public ResourceCapacityProviderImpl(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	@Override
	public Capacity getCapacityFor(DatePeriod period, ResourceId resourceId) {
		Resource resource = resourceRepository.get(resourceId);
		return resource.getCapacityFor(period);
	}
}
