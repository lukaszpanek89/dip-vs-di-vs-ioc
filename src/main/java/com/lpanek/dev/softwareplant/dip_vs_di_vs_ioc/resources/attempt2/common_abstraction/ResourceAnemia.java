package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt2.common_abstraction;

public final class ResourceAnemia {

	private final ResourceId id;

	public ResourceAnemia(ResourceId id) {
		this.id = id;
	}

	public ResourceId id() {
		return id;
	}
}
