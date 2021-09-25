package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_4.events_asynchronous.domain;

public interface ResourceCapacityObserver {

	void onCapacityChange(ResourceCapacityChangedEvent event);
}
