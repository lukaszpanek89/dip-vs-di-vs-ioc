package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.BoxService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.CreateBoxRequest;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.DatePeriod;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.security.SecurityManager;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.transaction.TransactionManager;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.date;

/**
 * <b>PROBLEM:</b>
 * <br>
 * How to handle aspects orthogonal to business logic (such as transactions, security, audit, logging)?
 * <br><br>
 * <b>SOLUTION:</b>
 * <br>
 * This example shows a simple solution - service with business logic ({@link BoxService}) handles orthogonal aspects by itself.
 */
public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		SecurityManager securityManager = new SecurityManager();
		TransactionManager transactionManager = new TransactionManager();
		BoxService boxService = new BoxService(securityManager, transactionManager);

		// ===== 2. USE CASE =====
		CreateBoxRequest createRequest = new CreateBoxRequest("Brand New Box", period("2019-03-11", "2019-03-15"));
		boxService.createBox(createRequest);
	}

	private static DatePeriod period(String startDate, String endDate) {
		return new DatePeriod(date(startDate), date(endDate));
	}
}
