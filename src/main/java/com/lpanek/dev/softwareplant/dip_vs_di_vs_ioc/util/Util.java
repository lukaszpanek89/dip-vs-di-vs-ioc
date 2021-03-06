package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util;

import java.time.LocalDate;

public class Util {

	public static LocalDate date(String date) {
		return LocalDate.parse(date);
	}

	public static void printEntityMessage(Object entity, String message, Object... messageArguments) {
		String toPrint = String.format("%s '%s': %s", entity.getClass().getSimpleName(), entity, String.format(message, messageArguments));
		System.out.println(toPrint);
	}

	public static void printServiceMessage(Object service, String message, Object... messageArguments) {
		String toPrint = String.format("%s: %s", service.getClass().getSimpleName(), String.format(message, messageArguments));
		System.out.println(toPrint);
	}

	public static void printApplicationMessage(String message, Object... messageArguments) {
		String toPrint = String.format("APPLICATION: " + message + "\n", messageArguments);
		System.out.println(toPrint);
	}
}
