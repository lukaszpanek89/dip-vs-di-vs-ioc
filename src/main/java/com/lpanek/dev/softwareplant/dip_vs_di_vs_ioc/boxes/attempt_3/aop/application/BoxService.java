package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aop.application;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aop.application.security.Secured;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aop.application.transaction.Transactional;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class BoxService {

	private final Map<BoxId, Box> boxesMap = Maps.newHashMap();

	@Secured
	public Set<Box> getAllBoxes() {
		Set<Box> boxesSet = Sets.newHashSet(boxesMap.values());
		return Collections.unmodifiableSet(boxesSet);
	}

	@Secured
	@Transactional
	public BoxId createBox(CreateBoxRequest request) {
		Box box = Box.from(request);
		boxesMap.put(box.id(), box);
		return box.id();
	}

	@Secured
	@Transactional
	public void deleteBox(BoxId boxId) {
		boxesMap.remove(boxId);
	}
}
