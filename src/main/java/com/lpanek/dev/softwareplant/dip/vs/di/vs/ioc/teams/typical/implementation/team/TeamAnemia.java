package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.team;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.common.PercentageAvailability;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.resource.ResourceId;
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
