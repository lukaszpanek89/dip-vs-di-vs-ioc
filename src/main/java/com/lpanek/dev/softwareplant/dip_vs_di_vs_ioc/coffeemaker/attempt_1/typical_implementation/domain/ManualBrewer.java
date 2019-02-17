package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_1.typical_implementation.domain;

public class ManualBrewer implements Brewer {

	public Coffee brew(CoffeeBeans beans) {
		// ...
		return new Coffee();
	}
}
