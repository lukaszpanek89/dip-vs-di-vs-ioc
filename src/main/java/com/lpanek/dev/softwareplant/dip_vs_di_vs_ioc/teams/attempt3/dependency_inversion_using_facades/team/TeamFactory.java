package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt3.dependency_inversion_using_facades.team;

public class TeamFactory {

	private final ResourceCapacityProvider resourceCapacityProvider;

	public TeamFactory(ResourceCapacityProvider resourceCapacityProvider) {
		this.resourceCapacityProvider = resourceCapacityProvider;
	}

	public Team recreate(TeamAnemia teamAnemia) {
		return new Team(teamAnemia, resourceCapacityProvider);
	}
}
