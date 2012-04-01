package com.jawesa.util.producer;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.jawesa.annotation.qualifier.user.Selected;

public class ResourceBundleProducer {
	@Inject
	private FacesContext facesContext;

	@Inject
	@Selected
	private Locale locale;

	@Produces
	@Named
	public ResourceBundle getMessages() {
		return ResourceBundle.getBundle(facesContext.getApplication()
				.getMessageBundle(), locale);
	}
}
