package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.security;

import org.springframework.stereotype.Service;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

@Service
public class SecurityManager {

	public void validateHasPrivilege() {
		printServiceMessage(this, "Validates privilege");
	}
}
