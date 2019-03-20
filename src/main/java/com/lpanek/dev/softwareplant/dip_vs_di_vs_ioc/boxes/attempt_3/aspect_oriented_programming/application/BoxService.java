package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.security.Secured;
import com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.transaction.Transactional;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class BoxService {

	private final Map<BoxId, Box> boxRepository = Maps.newHashMap();

	@Secured
	public Set<Box> getAllBoxes() {
		Set<Box> boxesSet = Sets.newHashSet(boxRepository.values());
		return Collections.unmodifiableSet(boxesSet);
	}

	@Secured
	@Transactional
	public BoxId createBox(CreateBoxRequest request) {
		Box box = Box.from(request);
		boxRepository.put(box.id(), box);
		return box.id();
	}

	@Secured
	@Transactional
	public void deleteBox(BoxId boxId) {
		boxRepository.remove(boxId);
	}
}
