package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.team;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_1.typical_implementation.domain.resource.ResourceRepository;

public class TeamFactory {

	private final ResourceRepository resourceRepository;

	public TeamFactory(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	public Team recreate(TeamAnemia teamAnemia) {
		return new Team(teamAnemia, resourceRepository);
	}
}
