package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.security.SecurityManager;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_1.typical_implementation.application.transaction.TransactionManager;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class BoxService {

	private final SecurityManager securityManager;

	private final TransactionManager transactionManager;

	private final Map<BoxId, Box> boxesMap = Maps.newHashMap();

	public BoxService(SecurityManager securityManager, TransactionManager transactionManager) {
		this.securityManager = securityManager;
		this.transactionManager = transactionManager;
	}

	public Set<Box> getAllBoxes() {
		securityManager.validateHasPrivilege();

		Set<Box> boxesSet = Sets.newHashSet(boxesMap.values());
		return Collections.unmodifiableSet(boxesSet);
	}

	public BoxId createBox(CreateBoxRequest request) {
		securityManager.validateHasPrivilege();
		transactionManager.beforeInvocation();
		try {
			Box box = Box.from(request);
			BoxId boxId = box.id();
			boxesMap.put(boxId, box);
			transactionManager.afterSuccessfulInvocation();
			return boxId;
		} catch (Exception e) {
			transactionManager.afterFailedInvocation();
			throw e;
		}
	}

	public void deleteBox(BoxId boxId) {
		securityManager.validateHasPrivilege();
		transactionManager.beforeInvocation();
		try {
			boxesMap.remove(boxId);
			transactionManager.afterSuccessfulInvocation();
		} catch (Exception e) {
			transactionManager.afterFailedInvocation();
			throw e;
		}
	}
}
