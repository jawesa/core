package com.jawesa.util.producer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.jawesa.annotation.qualifier.user.DefaultLocale;


public class LocaleProducer {
	@Inject
	private FacesContext facesContext;
	
	@Produces
    @DefaultLocale
    public String getDefaultLocaleKey() {
            return facesContext.getApplication().getDefaultLocale().toString();
    }
    
    @Produces
    @ApplicationScoped
    public List<Locale> getLocales() {
            Iterator<Locale> supportedLocales = facesContext.getApplication().getSupportedLocales();
            
            List<Locale> locales = new ArrayList<Locale>();
            while(supportedLocales.hasNext()) {
            	locales.add(supportedLocales.next());
            }
            
            return locales;
    }
}