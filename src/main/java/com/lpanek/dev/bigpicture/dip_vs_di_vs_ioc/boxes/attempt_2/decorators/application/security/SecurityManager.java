package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.security;

import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class SecurityManager {

	public void validateHasPrivilege() {
		printServiceMessage(this, "Validates privilege");
	}
}
