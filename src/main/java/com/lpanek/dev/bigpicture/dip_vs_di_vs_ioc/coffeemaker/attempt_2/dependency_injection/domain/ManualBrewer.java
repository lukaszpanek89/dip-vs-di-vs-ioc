package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.coffeemaker.attempt_2.dependency_injection.domain;

public class ManualBrewer implements Brewer {

	public Coffee brew(CoffeeBeans beans) {
		// ...
		return new Coffee();
	}
}
