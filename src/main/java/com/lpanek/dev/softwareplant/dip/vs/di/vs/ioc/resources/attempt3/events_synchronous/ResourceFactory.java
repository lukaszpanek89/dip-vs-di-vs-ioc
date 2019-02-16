package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.attempt3.events_synchronous;

public class ResourceFactory {

	public Resource recreate(ResourceAnemia resourceAnemia) {
		return new Resource(resourceAnemia);
	}
}
