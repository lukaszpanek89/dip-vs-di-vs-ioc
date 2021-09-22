package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.resources.attempt_2.common_abstraction.domain;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.printEntityMessage;

public class Resource {

	private final List<ResourceCapacityObserver> resourceCapacityObservers;

	private ResourceAnemia anemia;

	public Resource(ResourceAnemia anemia, List<ResourceCapacityObserver> resourceCapacityObservers) {
		this.anemia = anemia;
		this.resourceCapacityObservers = resourceCapacityObservers;
	}

	public ResourceId id() {
		return anemia.id();
	}

	public Capacity getCapacityOn(LocalDate date) {
		Capacity capacity = anemia.dailyCapacities().get(date);
		if (capacity == null) {
			return Capacity.ZERO;
		}
		return capacity;
	}

	public void changeCapacityOn(LocalDate date, Capacity newCapacity) {
		Capacity oldCapacity = getCapacityOn(date);
		doChangeCapacity(date, newCapacity);
		printEntityMessage(this, "Capacity on date %s changed from %s to %s\n", date, oldCapacity, newCapacity);

		for (ResourceCapacityObserver observer : resourceCapacityObservers) {
			try {
				printEntityMessage(this, "About to notify observer %s about the change", observer.getClass().getSimpleName());
				observer.onResourceCapacityChange(anemia.id(), date, oldCapacity, newCapacity);
				printEntityMessage(this, "Notified observer %s about the change\n", observer.getClass().getSimpleName());
			} catch (RuntimeException e) {
				// Exception handling goes here...
			}
		}
	}

	ResourceAnemia toAnemia() {
		return anemia;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Resource that = (Resource) o;
		return Objects.equal(id(), that.id());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id());
	}

	@Override
	public String toString() {
		return id().toString();
	}

	private void doChangeCapacity(LocalDate date, Capacity newCapacity) {
		Map<LocalDate, Capacity> newDailyCapacities = Maps.newHashMap(anemia.dailyCapacities());
		newDailyCapacities.put(date, newCapacity);
		anemia = new ResourceAnemia(anemia.id(), newDailyCapacities);
	}
}
