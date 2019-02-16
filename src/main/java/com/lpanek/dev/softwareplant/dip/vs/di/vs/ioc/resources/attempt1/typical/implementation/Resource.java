package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.attempt1.typical.implementation;

public class Resource {

	private final ResourceAnemia anemia;

	private final TeamService teamService;

	private final TaskService taskService;

	public Resource(ResourceAnemia anemia, TeamService teamService, TaskService taskService) {
		this.anemia = anemia;
		this.teamService = teamService;
		this.taskService = taskService;
	}

	public ResourceId id() {
		return anemia.id();
	}

	public ResourceAnemia toAnemia() {
		// TODO: Implement this
		return null;
	}

	public void changeCapacityTo(Capacity newCapacity) {
		Capacity oldCapacity = null;
		// Capacity change is validated here...
		// Capacity change is done here...

		try {
			teamService.handleResourceCapacityChange(anemia.id(), oldCapacity, newCapacity);
		} catch (RuntimeException e) {
			// Exception handling goes here...
		}

		try {
			taskService.handleResourceCapacityChange(anemia.id(), oldCapacity, newCapacity);
		} catch (RuntimeException e) {
			// Exception handling goes here...
		}
	}
}
