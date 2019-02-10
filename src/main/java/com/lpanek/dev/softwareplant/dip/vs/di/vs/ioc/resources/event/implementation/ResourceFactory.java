package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.event.implementation;

public class ResourceFactory {

	public Resource recreate(ResourceAnemia resourceAnemia) {
		return new Resource(resourceAnemia);
	}
}
