package com.jawesa.util.producer;

import javax.el.ELContext;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class ELContextProducer {
	@Produces
	public ELContext getELContext() {
		return FacesContext.getCurrentInstance().getELContext();
	}
}
