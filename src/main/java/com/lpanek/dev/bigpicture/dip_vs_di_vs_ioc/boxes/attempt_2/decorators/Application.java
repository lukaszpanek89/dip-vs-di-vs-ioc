package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.BoxId;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.BoxService;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.BoxServiceLogic;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.BoxServiceSecurity;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.BoxServiceTransaction;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.CreateBoxRequest;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.DatePeriod;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.security.SecurityManager;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.transaction.TransactionManager;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util;
import static com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Util.printApplicationMessage;

/**
 * <b>PROBLEM:</b>
 * <br>
 * How to handle aspects orthogonal to business logic (such as transactions, security, audit, logging)?
 * <br><br>
 * <b>SOLUTION:</b>
 * <br>
 * This example improves solution described in
 * {@link com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.Application}.
 * <br><br>
 * In this example, <b>Decorator design pattern</b> is introduced - {@link BoxService} becomes an interface and each of its implementations handles different
 * aspect ({@link BoxServiceLogic} handles business logic, {@link BoxServiceTransaction} handles transactions, {@link BoxServiceSecurity} handles security).
 * <br><br>
 * As a result, this example makes the code consistent with <b>Single Responsibility Principle</b> - each of {@link BoxService} implementations has only one
 * responsibility (one reason to change).
 */
public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		SecurityManager securityManager = new SecurityManager();
		TransactionManager transactionManager = new TransactionManager();
		BoxService boxService_Logic = new BoxServiceLogic();
		BoxService boxService_Transaction_Logic = new BoxServiceTransaction(transactionManager, boxService_Logic);
		BoxService boxService_Security_Transaction_Logic = new BoxServiceSecurity(securityManager, boxService_Transaction_Logic);
		BoxService boxService = boxService_Security_Transaction_Logic;

		// ===== 2. USE CASE =====
		printApplicationMessage("Boxes at the beginning: %s", boxService.getAllBoxes());

		CreateBoxRequest createRequest = new CreateBoxRequest("Brand New Box", period("2019-03-11", "2019-03-15"));
		BoxId boxId = boxService.createBox(createRequest);
		printApplicationMessage("Boxes after create Box: %s", boxService.getAllBoxes());

		boxService.deleteBox(boxId);
		printApplicationMessage("Boxes after delete Box: %s", boxService.getAllBoxes());
	}

	private static DatePeriod period(String startDate, String endDate) {
		return new DatePeriod(Util.date(startDate), Util.date(endDate));
	}
}
