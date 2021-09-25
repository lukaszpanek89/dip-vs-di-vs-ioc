package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_aop.attempt_1.typical_implementation.application.security;

import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.PrintUtil.printServiceMessage;

public class SecurityManager {

	public void validateHasPrivilege() {
		printServiceMessage(this, "Validates privilege");
	}
}
