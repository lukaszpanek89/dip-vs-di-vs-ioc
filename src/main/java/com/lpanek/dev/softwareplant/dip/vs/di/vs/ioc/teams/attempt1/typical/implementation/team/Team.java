package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.team;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.common.Capacity;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.common.DatePeriod;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.common.PercentageAvailability;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.resource.Resource;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.resource.ResourceId;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt1.typical.implementation.resource.ResourceRepository;
import java.util.Map;

public class Team {

	private final TeamAnemia anemia;

	private final ResourceRepository resourceRepository;

	public Team(TeamAnemia anemia, ResourceRepository resourceRepository) {
		this.anemia = anemia;
		this.resourceRepository = resourceRepository;
	}

	public Capacity getCapacityFor(DatePeriod period) {
		Capacity teamCapacity = Capacity.ZERO;
		for (Map.Entry<ResourceId, PercentageAvailability> memberEntry : anemia.members().entrySet()) {
			ResourceId memberId = memberEntry.getKey();
			PercentageAvailability memberAvailability = memberEntry.getValue();

			Resource resource = resourceRepository.get(memberId);
			Capacity resourceCapacity = resource.getCapacityFor(period);
			Capacity memberCapacity = resourceCapacity.multipliedBy(memberAvailability);
			teamCapacity = teamCapacity.sum(memberCapacity);
		}
		return teamCapacity;
	}
}
