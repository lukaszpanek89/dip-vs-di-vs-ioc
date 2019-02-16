package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.coffeemaker.attempt1.typical.implementation;

public class Client {

	public static void main(String[] args) {
		CoffeeMaker coffeeMaker = new CoffeeMaker();

		CoffeeBeans beans = new CoffeeBeans(5, BeanSize.BIG);
		Coffee coffee = coffeeMaker.makeCoffee(beans);

		System.out.println(coffee);
	}
}