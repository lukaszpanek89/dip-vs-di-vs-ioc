package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.coffeemaker.attempt_1.typical_implementation.domain;

public interface Heater {

	void turnOn();

	void turnOff();

	boolean temperatureIsAbove(Integer temperature);
}