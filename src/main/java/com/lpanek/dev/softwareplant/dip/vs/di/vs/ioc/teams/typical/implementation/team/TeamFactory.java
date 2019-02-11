package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.team;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.resource.ResourceRepository;

public class TeamFactory {

	private final ResourceRepository resourceRepository;

	public TeamFactory(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	public Team recreate(TeamAnemia teamAnemia) {
		return new Team(teamAnemia, resourceRepository);
	}
}
