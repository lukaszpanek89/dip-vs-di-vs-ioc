package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.common;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Immutable;
import java.util.concurrent.TimeUnit;

@Immutable
public final class Capacity {

	private static final TimeUnit INTERNAL_TIME_UNIT = TimeUnit.MINUTES;

	public static final Capacity ZERO = new Capacity(0, INTERNAL_TIME_UNIT);

	private final long timeAmount;

	public Capacity(long timeAmount, TimeUnit timeUnit) {
		if (timeAmount < 0) {
			throw new RuntimeException(String.format("Capacity's time amount %s cannot be less than 0", timeAmount));
		}
		this.timeAmount = INTERNAL_TIME_UNIT.convert(timeAmount, timeUnit);
	}

	private Capacity(long timeAmount) {
		this(timeAmount, INTERNAL_TIME_UNIT);
	}

	public Capacity sum(Capacity other) {
		if (this == ZERO) {
			return other;
		}
		if (other == ZERO) {
			return this;
		}
		return new Capacity(this.timeAmount + other.timeAmount);
	}

	public Capacity multipliedBy(PercentageAvailability availability) {
		return new Capacity((long) (timeAmount * availability.normalizedToOne()));
	}

	@Override
	public String toString() {
		long capacityInHours = TimeUnit.HOURS.convert(timeAmount, INTERNAL_TIME_UNIT);
		return String.format("%s hours", capacityInHours);
	}
}
