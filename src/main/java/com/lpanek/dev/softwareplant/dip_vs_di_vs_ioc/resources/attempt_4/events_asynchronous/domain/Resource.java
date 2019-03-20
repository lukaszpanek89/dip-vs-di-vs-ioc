package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_4.events_asynchronous.domain.event.EventPublisher;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Map;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printEntityMessage;

public class Resource {

	private final EventPublisher eventPublisher;

	private ResourceAnemia anemia;

	public Resource(ResourceAnemia anemia, EventPublisher eventPublisher) {
		this.anemia = anemia;
		this.eventPublisher = eventPublisher;
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
		printEntityMessage(this, "Capacity on date %s changed from %s to %s", date, oldCapacity, newCapacity);

		ResourceCapacityChangedEvent event = new ResourceCapacityChangedEvent(ZonedDateTime.now(), anemia.id(), date, oldCapacity, newCapacity);
		printEntityMessage(this, "Publishes event\n");
		eventPublisher.publish(event);
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
