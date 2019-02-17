package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_1.typical_implementation;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_1.typical_implementation.domain.BeanSize;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_1.typical_implementation.domain.Coffee;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_1.typical_implementation.domain.CoffeeBeans;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_1.typical_implementation.domain.CoffeeMaker;

public class Application {

	public static void main(String[] args) {
		// ===== USE CASE =====
		CoffeeMaker coffeeMaker = new CoffeeMaker();
		CoffeeBeans beans = new CoffeeBeans(5, BeanSize.BIG);
		Coffee coffee = coffeeMaker.makeCoffee(beans);

		System.out.println(coffee);
	}
}
