package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.team;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.common.Capacity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.common.DatePeriod;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt_2.dependency_inversion_using_abstractions.domain.common.PercentageAvailability;
import java.util.Map;

public class Team {

	private final TeamAnemia anemia;

	private final ResourceCapacityProvider resourceCapacityProvider;

	public Team(TeamAnemia anemia, ResourceCapacityProvider resourceCapacityProvider) {
		this.anemia = anemia;
		this.resourceCapacityProvider = resourceCapacityProvider;
	}

	public Capacity getCapacityFor(DatePeriod period) {
		Capacity teamCapacity = Capacity.ZERO;
		for (Map.Entry<ResourceId, PercentageAvailability> memberEntry : anemia.members().entrySet()) {
			ResourceId memberId = memberEntry.getKey();
			PercentageAvailability memberAvailability = memberEntry.getValue();

			Capacity resourceCapacity = resourceCapacityProvider.getCapacityFor(period, memberId);
			Capacity memberCapacity = resourceCapacity.multipliedBy(memberAvailability);
			teamCapacity = teamCapacity.sum(memberCapacity);
		}
		return teamCapacity;
	}
}
