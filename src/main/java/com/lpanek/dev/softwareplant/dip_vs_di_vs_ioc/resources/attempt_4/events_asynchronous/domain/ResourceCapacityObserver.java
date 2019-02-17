package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain;

public interface ResourceCapacityObserver {

	void onCapacityChange(ResourceCapacityChangedEvent event);
}
