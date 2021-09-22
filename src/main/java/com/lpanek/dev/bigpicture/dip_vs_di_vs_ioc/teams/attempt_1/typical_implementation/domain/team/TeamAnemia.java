package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.team;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.common.PercentageAvailability;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.resource.ResourceId;
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
