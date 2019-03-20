package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.transaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

@Aspect
@Service
public class TransactionAspect {

	private final TransactionManager transactionManager;

	@Autowired
	public TransactionAspect(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Around("@annotation(com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.transaction.Transactional)")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		printServiceMessage(this, "Before " + signatureOf(joinPoint));
		transactionManager.beginTransaction();
		try {
			Object proceedResult = joinPoint.proceed();
			transactionManager.commitTransaction();
			return proceedResult;
		} catch (Throwable throwable) {
			transactionManager.rollbackTransaction();
			throw throwable;
		} finally {
			printServiceMessage(this, "After " + signatureOf(joinPoint));
		}
	}

	private String signatureOf(ProceedingJoinPoint joinPoint) {
		return joinPoint.getSignature().toShortString();
	}
}
