package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.team;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.resource.ResourceRepository;

public class TeamFactory {

	private final ResourceRepository resourceRepository;

	public TeamFactory(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	public Team recreate(TeamAnemia teamAnemia) {
		return new Team(teamAnemia, resourceRepository);
	}
}
