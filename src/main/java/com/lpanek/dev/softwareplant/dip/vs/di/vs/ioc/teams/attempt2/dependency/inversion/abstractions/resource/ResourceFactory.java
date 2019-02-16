package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.attempt2.dependency.inversion.abstractions.resource;

public class ResourceFactory {

	private final ResourceWorkloadProvider resourceWorkloadProvider;

	private final ResourceHolidayProvider resourceHolidayProvider;

	public ResourceFactory(ResourceWorkloadProvider resourceWorkloadProvider, ResourceHolidayProvider resourceHolidayProvider) {
		this.resourceWorkloadProvider = resourceWorkloadProvider;
		this.resourceHolidayProvider = resourceHolidayProvider;
	}

	public Resource recreate(ResourceAnemia resourceAnemia) {
		return new Resource(resourceAnemia, resourceWorkloadProvider, resourceHolidayProvider);
	}
}
