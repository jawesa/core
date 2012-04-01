package com.jawesa.controller.common;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import com.jawesa.annotation.controller.ControllerQualifier;

@Stateless
@ControllerQualifier
@SuppressWarnings("serial")
public class Controller implements Serializable {
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public UIViewRoot getViewRoot() {
		return getFacesContext().getViewRoot();
	}

	public void debug(String message) {

	}

	public void info(String message) {

	}

	public void warn(String message) {

	}

	public void fatal(String message) {

	}
}
