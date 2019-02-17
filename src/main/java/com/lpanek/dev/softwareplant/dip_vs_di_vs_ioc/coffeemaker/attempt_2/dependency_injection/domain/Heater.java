package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_2.dependency_injection.domain;

public interface Heater {

	void turnOn();

	void turnOff();

	boolean temperatureIsAbove(Integer temperature);
}
