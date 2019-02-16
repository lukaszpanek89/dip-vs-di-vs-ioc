package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.resource.team;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.common.Capacity;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.common.DatePeriod;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.resource.Resource;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.resource.ResourceId;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.resource.ResourceRepository;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.team.ResourceCapacityProvider;

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
