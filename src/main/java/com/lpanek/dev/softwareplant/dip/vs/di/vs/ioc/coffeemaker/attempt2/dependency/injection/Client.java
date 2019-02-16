package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.coffeemaker.attempt2.dependency.injection;

public class Client {

	public static void main(String[] args) {
		Mill mill = new HarioMill();
		Heater heater = new TurboHeater();
		Brewer brewer = new ManualBrewer();
		CoffeeMaker coffeeMaker = new CoffeeMaker(mill, heater, brewer);

		CoffeeBeans beans = new CoffeeBeans(5, BeanSize.BIG);
		Coffee coffee = coffeeMaker.makeCoffee(beans);

		System.out.println(coffee);
	}
}
