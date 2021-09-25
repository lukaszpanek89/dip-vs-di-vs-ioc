package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_aop.attempt_1.typical_implementation.application;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Immutable;
import org.apache.commons.lang3.StringUtils;

@Immutable
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
