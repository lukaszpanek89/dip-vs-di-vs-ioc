package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.resource;

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
