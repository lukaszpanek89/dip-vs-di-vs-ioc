package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.team;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.common.PercentageAvailability;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.resource.ResourceId;
import java.util.Collections;
import java.util.Map;

public class TeamAnemia {

	private final TeamId id;

	private final Map<ResourceId, PercentageAvailability> members;

	public TeamAnemia(TeamId id, Map<ResourceId, PercentageAvailability> members) {
		this.id = id;
		this.members = members;
	}

	public TeamId id() {
		return id;
	}

	public Map<ResourceId, PercentageAvailability> members() {
		return Collections.unmodifiableMap(members);
	}
}
