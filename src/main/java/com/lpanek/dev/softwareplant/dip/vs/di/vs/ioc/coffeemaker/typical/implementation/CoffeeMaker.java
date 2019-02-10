package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.coffeemaker.typical.implementation;

public class CoffeeMaker {

	private final Mill mill;

	private final Heater heater;

	private final Brewer brewer;

	public CoffeeMaker() {
		this.mill = new HarioMill();
		this.heater = new TurboHeater();
		this.brewer = new ManualBrewer();
	}

	public Coffee makeCoffee(CoffeeBeans beans) {
		mill.grind(beans);
		heater.turnOn();
		while (heater.temperatureIsAbove(90)) {
			wait5Seconds();
		}
		Coffee coffee = brewer.brew(beans);
		heater.turnOff();
		return coffee;
	}

	private void wait5Seconds() {
		// TODO: Implement this
	}
}
