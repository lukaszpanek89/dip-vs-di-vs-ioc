package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.transaction.TransactionManager;
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
		transactionManager.beginTransaction();
		try {
			BoxId boxId = boxService.createBox(request);
			transactionManager.commitTransaction();
			return boxId;
		} catch (Exception e) {
			transactionManager.rollbackTransaction();
			throw e;
		}
	}

	public void deleteBox(BoxId boxId) {
		transactionManager.beginTransaction();
		try {
			boxService.deleteBox(boxId);
			transactionManager.commitTransaction();
		} catch (Exception e) {
			transactionManager.rollbackTransaction();
			throw e;
		}
	}
}
