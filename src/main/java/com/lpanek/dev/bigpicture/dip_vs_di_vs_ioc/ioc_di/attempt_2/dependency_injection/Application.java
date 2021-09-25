package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_2.dependency_injection;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_2.dependency_injection.domain.BeanSize;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_2.dependency_injection.domain.Brewer;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_2.dependency_injection.domain.Coffee;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_2.dependency_injection.domain.CoffeeBeans;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_2.dependency_injection.domain.CoffeeMaker;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_2.dependency_injection.domain.HarioMill;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_2.dependency_injection.domain.Heater;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_2.dependency_injection.domain.ManualBrewer;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_2.dependency_injection.domain.Mill;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_2.dependency_injection.domain.TurboHeater;

/**
 * <b>PROBLEM:</b>
 * <br>
 * How to construct object and its dependencies?
 * <br><br>
 * <b>SOLUTION:</b>
 * <br>
 * This example improves solution described in
 * {@link com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di.attempt_1.typical_implementation.Application}.
 * <br><br>
 * This example uses <b>Dependency Injection</b>, one of <b>Inversion of Control</b> flavours - dependencies of an object ({@link CoffeeMaker}) are injected
 * into that object via its constructor.
 */
public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		Mill mill = new HarioMill();
		Heater heater = new TurboHeater();
		Brewer brewer = new ManualBrewer();

		// ===== 2. USE CASE =====
		CoffeeMaker coffeeMaker = new CoffeeMaker(mill, heater, brewer);
		CoffeeBeans beans = new CoffeeBeans(5, BeanSize.BIG);
		Coffee coffee = coffeeMaker.makeCoffee(beans);

		System.out.println(coffee);
	}
}
