package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_3.dependency_inversion_using_facades.domain.common;

public final class PercentageAvailability {

	private static final double HUNDRED_PERCENT = 100.0;

	private final int availability;

	public PercentageAvailability(int availability) {
		if (availability < 0 || availability > 100) {
			throw new RuntimeException(String.format("Percentage availability has to be within range 0-100", availability));
		}
		this.availability = availability;
	}

	double normalizedToOne() {
		return availability / HUNDRED_PERCENT;
	}
}
