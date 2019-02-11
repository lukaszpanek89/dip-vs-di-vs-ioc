package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.typical.implementation.attempt1;

public final class ResourceAnemia {

	private final ResourceId id;

	public ResourceAnemia(ResourceId id) {
		this.id = id;
	}

	public ResourceId id() {
		return id;
	}
}
