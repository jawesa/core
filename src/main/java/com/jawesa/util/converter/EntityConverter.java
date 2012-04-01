package com.jawesa.util.converter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.jawesa.model.common.AbstractLongIdEntity;

@SuppressWarnings("serial")
@FacesConverter(value = "entityConverter")
@ViewScoped
public class EntityConverter implements Converter, Serializable{

	private Map<String, AbstractLongIdEntity> entities = new HashMap<String,AbstractLongIdEntity>();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(value == null) {
			return null;
		} else {
			return entities.get(value);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value == null) {
			return null;
		} else if(value instanceof AbstractLongIdEntity) {
			AbstractLongIdEntity entity = (AbstractLongIdEntity) value;
			String id = entity.getClass().getCanonicalName() + "." + entity.getId().toString();
			entities.put(id, entity);
			return id;
		} else {
			return null;
		}
	}
	
}
