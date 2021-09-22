package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain;

public class ResourceFactory {

	public Resource recreate(ResourceAnemia resourceAnemia) {
		return new Resource(resourceAnemia);
	}
}
