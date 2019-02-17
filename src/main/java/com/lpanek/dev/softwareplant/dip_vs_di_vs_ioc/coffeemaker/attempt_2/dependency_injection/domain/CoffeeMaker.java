package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_2.dependency_injection.domain;

public class CoffeeMaker {

	private final Mill mill;

	private final Heater heater;

	private final Brewer brewer;

	public CoffeeMaker(Mill mill, Heater heater, Brewer brewer) {
		this.mill = mill;
		this.heater = heater;
		this.brewer = brewer;
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
		// ...
	}
}
