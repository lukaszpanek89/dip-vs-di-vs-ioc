package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_5.event_published_AFTER_state_change_is_persisted.domain;

public interface ResourceCapacityObserver {

	void onCapacityChange(ResourceCapacityChangedEvent event);
}
