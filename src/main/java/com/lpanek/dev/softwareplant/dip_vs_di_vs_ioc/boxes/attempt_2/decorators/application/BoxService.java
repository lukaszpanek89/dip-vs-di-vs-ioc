package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application;

import java.util.Set;

public interface BoxService {

	Set<Box> getAllBoxes();

	BoxId createBox(CreateBoxRequest request);

	void deleteBox(BoxId boxId);
}
