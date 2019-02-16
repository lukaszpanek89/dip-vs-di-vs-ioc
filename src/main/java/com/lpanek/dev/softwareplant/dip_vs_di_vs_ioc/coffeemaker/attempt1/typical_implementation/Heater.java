package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt1.typical_implementation;

public interface Heater {

	void turnOn();

	void turnOff();

	boolean temperatureIsAbove(Integer temperature);
}
