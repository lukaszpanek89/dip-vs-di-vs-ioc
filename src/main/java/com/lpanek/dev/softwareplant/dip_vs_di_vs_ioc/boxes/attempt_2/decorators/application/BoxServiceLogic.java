package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class BoxServiceLogic implements BoxService {

	private final Map<BoxId, Box> boxRepository = Maps.newHashMap();

	public Set<Box> getAllBoxes() {
		Set<Box> boxesSet = Sets.newHashSet(boxRepository.values());
		return Collections.unmodifiableSet(boxesSet);
	}

	public BoxId createBox(CreateBoxRequest request) {
		Box box = Box.from(request);
		boxRepository.put(box.id(), box);
		return box.id();
	}

	public void deleteBox(BoxId boxId) {
		boxRepository.remove(boxId);
	}
}
