package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.attempt1.typical_implementation;

public final class ResourceAnemia {

	private final ResourceId id;

	public ResourceAnemia(ResourceId id) {
		this.id = id;
	}

	public ResourceId id() {
		return id;
	}
}
