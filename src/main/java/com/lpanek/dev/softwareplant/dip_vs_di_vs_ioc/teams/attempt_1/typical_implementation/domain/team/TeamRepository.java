package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.team;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.common.PercentageAvailability;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.resource.ResourceId;
import java.util.HashMap;
import java.util.Map;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.RepositoriesInitialData.JOHN_ID;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.RepositoriesInitialData.KATHY_ID;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.RepositoriesInitialData.TEAM_OAKS_ID;

public class TeamRepository {

	private final Map<TeamId, TeamAnemia> repository = createRepositoryWithInitialData();

	private final TeamFactory teamFactory;

	public TeamRepository(TeamFactory teamFactory) {
		this.teamFactory = teamFactory;
	}

	public Team get(TeamId teamId) {
		TeamAnemia teamAnemia = repository.get(teamId);
		if (teamAnemia == null) {
			throw new RuntimeException(String.format("Team with id '%s' does not exist", teamId));
		}
		return teamFactory.recreate(teamAnemia);
	}

	private Map<TeamId, TeamAnemia> createRepositoryWithInitialData() {
		Map<TeamId, TeamAnemia> repository = new HashMap<>();

		Map<ResourceId, PercentageAvailability> teamMembers = new HashMap<>();
		teamMembers.put(JOHN_ID, new PercentageAvailability(50));
		teamMembers.put(KATHY_ID, new PercentageAvailability(100));
		TeamAnemia teamAnemia = new TeamAnemia(TEAM_OAKS_ID, teamMembers);
		repository.put(teamAnemia.id(), teamAnemia);

		return repository;
	}
}
