package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.team;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.common.Capacity;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.common.DatePeriod;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.resource.ResourceId;

public interface ResourceCapacityProvider {

	Capacity getCapacityFor(DatePeriod period, ResourceId resourceId);
}
