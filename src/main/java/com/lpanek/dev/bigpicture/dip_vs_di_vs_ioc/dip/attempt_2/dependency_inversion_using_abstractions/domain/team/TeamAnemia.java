package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.team;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.common.PercentageAvailability;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Immutable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Immutable
final class TeamAnemia {

	private final TeamId id;

	private final Map<ResourceId, PercentageAvailability> members;

	TeamAnemia(TeamId id, Map<ResourceId, PercentageAvailability> members) {
		this.id = id;
		this.members = new HashMap<>(members);
	}

	TeamId id() {
		return id;
	}

	Map<ResourceId, PercentageAvailability> members() {
		return Collections.unmodifiableMap(members);
	}
}
