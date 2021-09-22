package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_3.dependency_inversion_using_facades.domain.resource;

public class ResourceFactory {

	private final WorkloadProvider workloadProvider;

	private final HolidayProvider holidayProvider;

	public ResourceFactory(WorkloadProvider workloadProvider, HolidayProvider holidayProvider) {
		this.workloadProvider = workloadProvider;
		this.holidayProvider = holidayProvider;
	}

	public Resource recreate(ResourceAnemia resourceAnemia) {
		return new Resource(resourceAnemia, workloadProvider, holidayProvider);
	}
}
