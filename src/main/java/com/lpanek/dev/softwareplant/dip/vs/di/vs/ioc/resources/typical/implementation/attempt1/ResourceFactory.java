package com.lpanek.dev.softwareplant.dip.vs.di.vs.ioc.resources.typical.implementation.attempt1;

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
