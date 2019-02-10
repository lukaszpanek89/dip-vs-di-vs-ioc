package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.event.implementation;

public final class ResourceAnemia {

	private final ResourceId id;

	public ResourceAnemia(ResourceId id) {
		this.id = id;
	}

	public ResourceId id() {
		return id;
	}
}
