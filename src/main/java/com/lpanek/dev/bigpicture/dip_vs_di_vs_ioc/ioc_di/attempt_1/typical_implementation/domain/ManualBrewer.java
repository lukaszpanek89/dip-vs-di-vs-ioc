package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_1.typical_implementation.domain;

public class ManualBrewer implements Brewer {

	public Coffee brew(CoffeeBeans beans) {
		// ...
		return new Coffee();
	}
}
