package com.jawesa.util.producer;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextProducer {
	@Produces
	@RequestScoped
	public FacesContext getFacesContext() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (ctx == null) {
			throw new ContextNotActiveException("FacesContext is not active");
		}
		return ctx;
	}
}
