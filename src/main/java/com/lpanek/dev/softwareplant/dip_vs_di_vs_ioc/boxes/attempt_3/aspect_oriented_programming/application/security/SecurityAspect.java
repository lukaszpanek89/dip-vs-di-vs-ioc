package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

@Aspect
@Service
public class SecurityAspect {

	private final SecurityManager securityManager;

	@Autowired
	public SecurityAspect(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	@Around("@annotation(com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.security.Secured)")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		printServiceMessage(this, "Before " + signatureOf(joinPoint));
		securityManager.validateHasPrivilege();
		try {
			return joinPoint.proceed();
		} finally {
			printServiceMessage(this, "After " + signatureOf(joinPoint));
		}
	}

	private String signatureOf(ProceedingJoinPoint joinPoint) {
		return joinPoint.getSignature().toShortString();
	}
}
