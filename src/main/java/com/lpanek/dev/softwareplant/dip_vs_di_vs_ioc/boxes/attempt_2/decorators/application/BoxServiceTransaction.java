package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application;

import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.transaction.TransactionManager;
import java.util.Set;

public class BoxServiceTransaction implements BoxService {

	private final TransactionManager transactionManager;

	private final BoxService boxService;

	public BoxServiceTransaction(TransactionManager transactionManager, BoxService boxService) {
		this.transactionManager = transactionManager;
		this.boxService = boxService;
	}

	public Set<Box> getAllBoxes() {
		return boxService.getAllBoxes();
	}

	public BoxId createBox(CreateBoxRequest request) {
		transactionManager.beforeInvocation();
		try {
			BoxId boxId = boxService.createBox(request);
			transactionManager.afterSuccessfulInvocation();
			return boxId;
		} catch (Exception e) {
			transactionManager.afterFailedInvocation();
			throw e;
		}
	}

	public void deleteBox(BoxId boxId) {
		transactionManager.beforeInvocation();
		try {
			boxService.deleteBox(boxId);
			transactionManager.afterSuccessfulInvocation();
		} catch (Exception e) {
			transactionManager.afterFailedInvocation();
			throw e;
		}
	}
}
