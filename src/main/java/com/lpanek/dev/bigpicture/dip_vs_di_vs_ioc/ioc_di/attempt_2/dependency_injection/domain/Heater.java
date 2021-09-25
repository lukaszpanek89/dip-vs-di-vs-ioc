package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_2.dependency_injection.domain;

public interface Heater {

	void turnOn();

	void turnOff();

	boolean temperatureIsAbove(Integer temperature);
}
