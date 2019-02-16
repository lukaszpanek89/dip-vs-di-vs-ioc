package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.team;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.common.PercentageAvailability;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt1.typical_implementation.resource.ResourceId;
import java.util.Collections;
import java.util.Map;

final class TeamAnemia {

	private final TeamId id;

	private final Map<ResourceId, PercentageAvailability> members;

	TeamAnemia(TeamId id, Map<ResourceId, PercentageAvailability> members) {
		this.id = id;
		this.members = members;
	}

	TeamId id() {
		return id;
	}

	Map<ResourceId, PercentageAvailability> members() {
		return Collections.unmodifiableMap(members);
	}
}
