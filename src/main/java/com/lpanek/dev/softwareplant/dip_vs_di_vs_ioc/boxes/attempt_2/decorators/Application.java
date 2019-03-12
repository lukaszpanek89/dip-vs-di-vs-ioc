package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.BoxId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.BoxService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.BoxServiceLogic;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.BoxServiceSecurity;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.BoxServiceTransaction;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.CreateBoxRequest;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.DatePeriod;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.security.SecurityManager;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.transaction.TransactionManager;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.date;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printApplicationMessage;

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
		return new DatePeriod(date(startDate), date(endDate));
	}
}
