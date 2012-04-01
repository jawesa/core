package com.jawesa.util.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@SuppressWarnings("serial")
@FacesConverter(value = "longConverter")
public class LongConverter implements Converter, Serializable {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(value == null) {
			return null;
		} else {
			return Long.parseLong(value);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value == null) {
			return null;
		} else {
			return value.toString();
		}
	}
	
}
