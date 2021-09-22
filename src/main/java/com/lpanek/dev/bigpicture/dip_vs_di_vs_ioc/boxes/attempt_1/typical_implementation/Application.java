package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.BoxId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.BoxService;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.CreateBoxRequest;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.DatePeriod;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.security.SecurityManager;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.transaction.TransactionManager;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.date;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.printApplicationMessage;

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
		printApplicationMessage("Boxes at the beginning: %s", boxService.getAllBoxes());

		CreateBoxRequest createRequest = new CreateBoxRequest("Brand New Box", period("2019-03-11", "2019-03-15"));
		BoxId boxId = boxService.createBox(createRequest);
		printApplicationMessage("Boxes after create Box: %s", boxService.getAllBoxes());

		boxService.deleteBox(boxId);
		printApplicationMessage("Boxes after delete Box: %s", boxService.getAllBoxes());
	}

	private static DatePeriod period(String startDate, String endDate) {
		return new DatePeriod(date(startDate), date(endDate));
	}
}
