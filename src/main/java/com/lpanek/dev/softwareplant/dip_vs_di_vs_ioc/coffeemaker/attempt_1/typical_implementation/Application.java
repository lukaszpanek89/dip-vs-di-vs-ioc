package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_1.typical_implementation;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_1.typical_implementation.domain.BeanSize;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_1.typical_implementation.domain.Coffee;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_1.typical_implementation.domain.CoffeeBeans;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.coffeemaker.attempt_1.typical_implementation.domain.CoffeeMaker;

/**
 * <b>PROBLEM:</b>
 * <br>
 * How to construct object and its dependencies?
 * <br><br>
 * <b>SOLUTION:</b>
 * <br>
 * This example shows a simple solution - object ({@link CoffeeMaker}) creates its dependencies by itself in its constructor.
 */
public class Application {

	public static void main(String[] args) {
		// ===== USE CASE =====
		CoffeeMaker coffeeMaker = new CoffeeMaker();
		CoffeeBeans beans = new CoffeeBeans(5, BeanSize.BIG);
		Coffee coffee = coffeeMaker.makeCoffee(beans);

		System.out.println(coffee);
	}
}
