package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.resources.attempt_1.typical_implementation.domain;

public class ResourceFactory {

	private final TeamService teamService;

	private final TaskService taskService;

	public ResourceFactory(TeamService teamService, TaskService taskService) {
		this.teamService = teamService;
		this.taskService = taskService;
	}

	public Resource recreate(ResourceAnemia resourceAnemia) {
		return new Resource(resourceAnemia, teamService, taskService);
	}
}
