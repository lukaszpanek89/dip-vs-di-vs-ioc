package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_aop.attempt_2.decorators.application;

import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_aop.attempt_2.decorators.application.security.SecurityManager;
import java.util.Set;

public class BoxServiceSecurity implements BoxService {

	private final SecurityManager securityManager;

	private final BoxService boxService;

	public BoxServiceSecurity(SecurityManager securityManager, BoxService boxService) {
		this.securityManager = securityManager;
		this.boxService = boxService;
	}

	public Set<Box> getAllBoxes() {
		securityManager.validateHasPrivilege();
		return boxService.getAllBoxes();
	}

	public BoxId createBox(CreateBoxRequest request) {
		securityManager.validateHasPrivilege();
		return boxService.createBox(request);
	}

	public void deleteBox(BoxId boxId) {
		securityManager.validateHasPrivilege();
		boxService.deleteBox(boxId);
	}
}
