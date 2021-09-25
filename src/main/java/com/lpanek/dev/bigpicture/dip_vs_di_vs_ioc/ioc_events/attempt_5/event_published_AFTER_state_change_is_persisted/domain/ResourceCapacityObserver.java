package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_5.event_published_AFTER_state_change_is_persisted.domain;

public interface ResourceCapacityObserver {

	void onCapacityChange(ResourceCapacityChangedEvent event);
}
