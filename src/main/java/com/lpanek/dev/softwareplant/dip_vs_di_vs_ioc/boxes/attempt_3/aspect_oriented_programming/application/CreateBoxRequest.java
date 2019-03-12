package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application;

import org.apache.commons.lang3.StringUtils;

public final class CreateBoxRequest {

	private final String name;

	private final DatePeriod period;

	public CreateBoxRequest(String name, DatePeriod period) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("Name must not be blank");
		}
		this.name = name;
		this.period = period;
	}

	public String name() {
		return name;
	}

	public DatePeriod period() {
		return period;
	}
}
