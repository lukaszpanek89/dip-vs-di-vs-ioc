package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_aop.attempt_1.typical_implementation.application;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_aop.attempt_1.typical_implementation.application.security.SecurityManager;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_aop.attempt_1.typical_implementation.application.transaction.TransactionManager;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class BoxService {

	private final SecurityManager securityManager;

	private final TransactionManager transactionManager;

	private final Map<BoxId, Box> boxRepository = Maps.newHashMap();

	public BoxService(SecurityManager securityManager, TransactionManager transactionManager) {
		this.securityManager = securityManager;
		this.transactionManager = transactionManager;
	}

	public Set<Box> getAllBoxes() {
		securityManager.validateHasPrivilege();
		Set<Box> boxesSet = Sets.newHashSet(boxRepository.values());
		return Collections.unmodifiableSet(boxesSet);
	}

	public BoxId createBox(CreateBoxRequest request) {
		securityManager.validateHasPrivilege();
		transactionManager.beginTransaction();
		try {
			Box box = Box.from(request);
			BoxId boxId = box.id();
			boxRepository.put(boxId, box);
			transactionManager.commitTransaction();
			return boxId;
		} catch (Exception e) {
			transactionManager.rollbackTransaction();
			throw e;
		}
	}

	public void deleteBox(BoxId boxId) {
		securityManager.validateHasPrivilege();
		transactionManager.beginTransaction();
		try {
			boxRepository.remove(boxId);
			transactionManager.commitTransaction();
		} catch (Exception e) {
			transactionManager.rollbackTransaction();
			throw e;
		}
	}
}
