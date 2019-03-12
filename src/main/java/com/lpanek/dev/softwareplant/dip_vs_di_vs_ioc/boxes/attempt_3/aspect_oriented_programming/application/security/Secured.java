package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_3.aspect_oriented_programming.application.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {

}
