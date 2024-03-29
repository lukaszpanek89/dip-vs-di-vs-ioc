package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.resource;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.dip.attempt_2.dependency_inversion_using_abstractions.domain.common.Capacity;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Immutable;
import java.util.concurrent.TimeUnit;
import static org.joda.time.DateTimeConstants.MINUTES_PER_DAY;

@Immutable
public final class Workload {

	private final int startMinute;

	private final int endMinute;

	public Workload(int startMinute, int endMinute) {
		if (startMinute < 0 || startMinute > MINUTES_PER_DAY) {
			throw new RuntimeException(String.format("startMinute has to be between 0 and %s", MINUTES_PER_DAY));
		}
		if (endMinute < 0 || endMinute > MINUTES_PER_DAY) {
			throw new RuntimeException(String.format("endMinute has to be between 0 and %s", MINUTES_PER_DAY));
		}
		if (endMinute < startMinute) {
			throw new RuntimeException(String.format("endMinute %s has to be greater or equal to startMinute %s", endMinute, startMinute));
		}
		this.startMinute = startMinute;
		this.endMinute = endMinute;
	}

	public Capacity toCapacity() {
		return new Capacity(endMinute - startMinute, TimeUnit.MINUTES);
	}

	@Override
	public String toString() {
		long workloadInHours = TimeUnit.HOURS.convert(endMinute - startMinute, TimeUnit.MINUTES);
		return String.format("%s hours", workloadInHours);
	}
}
