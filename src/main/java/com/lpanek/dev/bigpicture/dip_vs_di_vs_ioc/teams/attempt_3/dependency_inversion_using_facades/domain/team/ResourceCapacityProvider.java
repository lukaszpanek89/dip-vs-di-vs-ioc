package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.team;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.common.Capacity;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.common.DatePeriod;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.resource.Resource;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.resource.ResourceId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.resource.ResourceRepository;

public class ResourceCapacityProvider {

	private final ResourceRepository resourceRepository;

	public ResourceCapacityProvider(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	public Capacity getCapacityFor(DatePeriod period, ResourceId resourceId) {
		Resource resource = resourceRepository.get(resourceId);
		return resource.getCapacityFor(period);
	}
}
