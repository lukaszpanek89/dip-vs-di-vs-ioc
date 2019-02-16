package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.coffeemaker.attempt1.typical_implementation;

public interface Heater {

	void turnOn();

	void turnOff();

	boolean temperatureIsAbove(Integer temperature);
}
