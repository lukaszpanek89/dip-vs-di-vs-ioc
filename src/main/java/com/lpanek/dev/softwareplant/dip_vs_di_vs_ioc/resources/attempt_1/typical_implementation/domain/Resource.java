package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_1.typical_implementation.domain;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import java.time.LocalDate;
import java.util.Map;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printEntityMessage;

public class Resource {

	private final TeamService teamService;

	private final TaskService taskService;

	private ResourceAnemia anemia;

	public Resource(ResourceAnemia anemia, TeamService teamService, TaskService taskService) {
		this.anemia = anemia;
		this.teamService = teamService;
		this.taskService = taskService;
	}

	public ResourceId id() {
		return anemia.id();
	}

	public Capacity capacityOnDate(LocalDate date) {
		Capacity capacity = anemia.dailyCapacities().get(date);
		if (capacity == null) {
			return Capacity.ZERO;
		}
		return capacity;
	}

	public void changeCapacityOnDate(LocalDate date, Capacity newCapacity) {
		Capacity oldCapacity = capacityOnDate(date);
		Map<LocalDate, Capacity> newDailyCapacities = Maps.newHashMap(anemia.dailyCapacities());
		newDailyCapacities.put(date, newCapacity);
		anemia = new ResourceAnemia(anemia.id(), newDailyCapacities);
		printEntityMessage(this, "Capacity on date %s changed from %s to %s\n", date, oldCapacity, newCapacity);

		try {
			printEntityMessage(this, "About to notify %s about the change", teamService.getClass().getSimpleName());
			teamService.handleResourceCapacityChange(anemia.id(), date, oldCapacity, newCapacity);
			printEntityMessage(this, "Notified %s about the change\n", teamService.getClass().getSimpleName());
		} catch (RuntimeException e) {
			// Exception handling goes here...
		}

		try {
			printEntityMessage(this, "About to notify %s about the change", taskService.getClass().getSimpleName());
			taskService.handleResourceCapacityChange(anemia.id(), date, oldCapacity, newCapacity);
			printEntityMessage(this, "Notified %s about the change\n", taskService.getClass().getSimpleName());
		} catch (RuntimeException e) {
			// Exception handling goes here...
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
}
