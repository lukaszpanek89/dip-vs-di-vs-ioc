package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.holidayplan.HolidayPlanId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.resource.ResourceId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.team.TeamId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.teams.attempt_1.typical_implementation.domain.workloadplan.WorkloadPlanId;

public class RepositoriesInitialData {

	public static WorkloadPlanId FULL_TIME_WORKLOAD_PLAN_ID = new WorkloadPlanId("Full-time workload plan");

	public static HolidayPlanId POLISH_HOLIDAY_PLAN_ID = new HolidayPlanId("Polish holiday plan");

	public static ResourceId JOHN_ID = new ResourceId("john.doe");

	public static ResourceId KATHY_ID = new ResourceId("kathy.stidolph");

	public static TeamId TEAM_OAKS_ID = new TeamId("Oaks");
}
