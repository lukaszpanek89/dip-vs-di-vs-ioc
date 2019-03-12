package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.BoxId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.BoxService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.CreateBoxRequest;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.DatePeriod;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.date;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printApplicationMessage;

public class Application {

	public static void main(String[] args) {
		// ===== 1. APPLICATION SETUP =====
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.scan("com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes");
		context.refresh();
		BoxService boxService = (BoxService) context.getBean("boxService");

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
