package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.BoxId;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.BoxService;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.CreateBoxRequest;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.DatePeriod;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.security.Secured;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.security.SecurityAspect;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.transaction.TransactionAspect;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.transaction.Transactional;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.date;
import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printApplicationMessage;

/**
 * <b>PROBLEM:</b>
 * <br>
 * How to handle aspects orthogonal to business logic (such as transactions, security, audit, logging)?
 * <br><br>
 * <b>SOLUTION:</b>
 * <br>
 * This example improves solution described in
 * {@link com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.Application}.
 * <br><br>
 * This example uses <b>Aspect-Oriented Programming</b>, one of <b>Inversion of Control</b> flavours - {@link BoxService} methods are annotated with
 * {@link Secured} and {@link Transactional} annotations, and appropriate aspects {@link SecurityAspect} and {@link TransactionAspect} handle them.
 */
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
