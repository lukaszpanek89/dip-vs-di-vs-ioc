package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util;

import java.time.LocalDate;

public class DateUtil {

	private DateUtil() {
		// Intentionally left empty
	}

	public static LocalDate date(String date) {
		return LocalDate.parse(date);
	}
}
