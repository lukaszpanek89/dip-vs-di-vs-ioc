package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.coffeemaker.attempt2.dependency.injection;

public interface Heater {

	void turnOn();

	void turnOff();

	boolean temperatureIsAbove(Integer temperature);
}
