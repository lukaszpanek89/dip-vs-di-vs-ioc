package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt3.events_synchronous;

public class ResourceFactory {

	public Resource recreate(ResourceAnemia resourceAnemia) {
		return new Resource(resourceAnemia);
	}
}
