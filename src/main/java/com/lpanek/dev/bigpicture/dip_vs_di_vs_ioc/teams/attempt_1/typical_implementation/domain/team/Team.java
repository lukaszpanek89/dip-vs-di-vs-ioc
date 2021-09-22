package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.team;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.common.Capacity;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.common.DatePeriod;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.common.PercentageAvailability;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.resource.Resource;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.resource.ResourceId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.resource.ResourceRepository;
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
