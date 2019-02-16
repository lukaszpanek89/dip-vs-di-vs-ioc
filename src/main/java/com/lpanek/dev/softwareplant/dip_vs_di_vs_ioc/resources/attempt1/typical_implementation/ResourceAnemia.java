package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt1.typical_implementation;

public final class ResourceAnemia {

	private final ResourceId id;

	public ResourceAnemia(ResourceId id) {
		this.id = id;
	}

	public ResourceId id() {
		return id;
	}
}
