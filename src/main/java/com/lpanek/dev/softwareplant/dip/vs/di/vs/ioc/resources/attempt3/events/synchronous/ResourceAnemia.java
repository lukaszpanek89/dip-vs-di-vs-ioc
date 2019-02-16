package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.attempt3.events.synchronous;

public final class ResourceAnemia {

	private final ResourceId id;

	public ResourceAnemia(ResourceId id) {
		this.id = id;
	}

	public ResourceId id() {
		return id;
	}
}
