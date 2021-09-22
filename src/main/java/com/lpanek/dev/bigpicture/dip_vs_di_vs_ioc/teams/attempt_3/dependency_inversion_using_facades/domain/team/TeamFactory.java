package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.team;

public class TeamFactory {

	private final ResourceCapacityProvider resourceCapacityProvider;

	public TeamFactory(ResourceCapacityProvider resourceCapacityProvider) {
		this.resourceCapacityProvider = resourceCapacityProvider;
	}

	public Team recreate(TeamAnemia teamAnemia) {
		return new Team(teamAnemia, resourceCapacityProvider);
	}
}
