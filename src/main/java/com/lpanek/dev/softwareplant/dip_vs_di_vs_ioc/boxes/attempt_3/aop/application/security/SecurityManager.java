package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aop.application.security;

import org.springframework.stereotype.Service;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

@Service
public class SecurityManager {

	public void validateHasPrivilege() {
		printServiceMessage(this, "Validates privilege");
	}
}
