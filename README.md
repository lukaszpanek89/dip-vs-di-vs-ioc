Dependency Inversion Principle (DIP) vs. Dependency Injection (DI) vs. Inversion of Control (IoC)
====================

This is a collection of simple code examples that I used during my presentations entitled _DIP vs. DI vs. IoC_. In these presentations I explain how DIP, DI, 
and IoC relate to each other, in what aspects they are similar, and in what ones they differ.

Examples for Dependency Inversion Principle (DIP)
-------------------------------------------------

Examples related to **Dependency Inversion Principle** are gathered under `com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip` package.

Examples for Inversion of Control (IoC)
---------------------------------------

Inversion of Control has 4 flavours:
* Dependency Injection (DI)
* Events
* Aspect-Oriented Programming (AOP)
* Frameworks in general

Examples related to **Dependency Injection** are gathered under `com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_di` package.

Examples related to **Events** are gathered under `com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events` package.

Examples related to **Aspect-Oriented Programming** are gathered under `com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_aop` package.

There are no examples related to **Frameworks** (yet?).

General notes on all examples
-----------------------------

1. Each example has `Application` class as its entry point. These are the classes with `main()` methods.
2. Each `Application` class is documented with JavaDoc, so one can exactly know what to expect from each example.

Enjoy!

Worth reading & watching
-----------------------------

1. Dependency Inversion Principle & Hexagonal Architecture
   * _Clean Architecture_ - Robert C. Martin
   * _Implementing Domain-Driven Design_, chapter _4. Architecture_ - Vaughn Vernon
   * [_DIP in the Wild_](https://martinfowler.com/articles/dipInTheWild.html) - Brett L. Schuchert (_martinfowler.com_)

2. Inversion of Control
   * [_Inversion of Control Containers and the Dependency Injection pattern_](https://martinfowler.com/articles/injection.html) - Martin Fowler (_martinfowler.com_)
   * (Polish) [_Four flavours of inverting (and losing) control_](https://bottega.com.pl/pdf/materialy/receptury/ioc.pdf) - Sławomir Sobótka (_bottega.com.pl_)
   * _Implementing Domain-Driven Design_, chapter _8. Domain Events_ - Vaughn Vernon

3. Other
   * (Polish) [_Experiment on keeping same level of abstraction in methods & functions_](https://bit.ly/panek-experiment) - Łukasz Panek
