package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.holidayplan.HolidayPlanId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.resource.ResourceId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.team.TeamId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.teams.attempt2.dependency_inversion_using_abstractions.workloadplan.WorkloadPlanId;

public class Constants {

	public static WorkloadPlanId FULL_TIME_WORKLOAD_PLAN_ID = new WorkloadPlanId("Full-time workload plan");

	public static HolidayPlanId POLISH_HOLIDAY_PLAN_ID = new HolidayPlanId("Polish holiday plan");

	public static ResourceId JOHN_ID = new ResourceId("john.doe");

	public static ResourceId KATHY_ID = new ResourceId("kathy.stidolph");

	public static TeamId TEAM_OAKS_ID = new TeamId("Oaks");
}
