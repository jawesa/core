package com.jawesa.annotation.action;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Named;

@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE })
@Named
public @interface Action {

}
