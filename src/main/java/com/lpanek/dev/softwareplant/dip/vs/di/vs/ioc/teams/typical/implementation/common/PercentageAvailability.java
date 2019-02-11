package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.teams.typical.implementation.common;

public final class PercentageAvailability {

	private static final double HUNDRED_PERCENT = 100.0;

	private final int availability;

	public PercentageAvailability(int availability) {
		if (availability < 0 || availability > 100) {
			throw new RuntimeException(String.format("Percentage availability has to be within range 0-100", availability));
		}
		this.availability = availability;
	}

	public double normalizedToOne() {
		return availability / HUNDRED_PERCENT;
	}
}
