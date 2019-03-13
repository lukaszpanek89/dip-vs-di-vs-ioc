package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.team;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.common.PercentageAvailability;
import java.util.HashMap;
import java.util.Map;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.Constants.JOHN_ID;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.Constants.KATHY_ID;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.Constants.TEAM_OAKS_ID;

public class TeamRepository {

	private final Map<TeamId, TeamAnemia> repository = createPrepopulatedRepository();

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

	private Map<TeamId, TeamAnemia> createPrepopulatedRepository() {
		Map<TeamId, TeamAnemia> repository = new HashMap<>();

		Map<ResourceId, PercentageAvailability> teamMembers = new HashMap<>();
		teamMembers.put(new ResourceId(JOHN_ID), new PercentageAvailability(50));
		teamMembers.put(new ResourceId(KATHY_ID), new PercentageAvailability(100));
		TeamAnemia teamAnemia = new TeamAnemia(new TeamId(TEAM_OAKS_ID), teamMembers);
		repository.put(teamAnemia.id(), teamAnemia);

		return repository;
	}
}
