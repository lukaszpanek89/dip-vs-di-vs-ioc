package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.coffeemaker.di.implementation;

public interface Heater {

	void turnOn();

	void turnOff();

	boolean temperatureIsAbove(Integer temperature);
}
