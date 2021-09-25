package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.team;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.common.Capacity;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.common.DatePeriod;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.common.PercentageAvailability;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.resource.ResourceId;
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
