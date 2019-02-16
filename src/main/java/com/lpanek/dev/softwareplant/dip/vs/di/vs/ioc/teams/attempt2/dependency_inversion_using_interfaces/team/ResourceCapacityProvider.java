package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.team;

import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.common.Capacity;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.common.DatePeriod;
import com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency_inversion_using_interfaces.resource.ResourceId;

public interface ResourceCapacityProvider {

	Capacity getCapacityFor(DatePeriod period, ResourceId resourceId);
}
