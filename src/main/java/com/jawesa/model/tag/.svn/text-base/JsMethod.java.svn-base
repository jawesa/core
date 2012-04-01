package com.jawesa.model.tag;

import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import com.jawesa.model.common.AbstractLongIdEntity;
import com.jawesa.model.common.I18nLabel;
import com.jawesa.model.common.I18nUserType;

@Entity
public class JsMethod extends AbstractLongIdEntity {
	private String name;
	private String returnValue;
	@Type(type = I18nUserType.NAME)
	private I18nLabel descriptionLabel;

	public String getDescription() {
		return descriptionLabel.getValue();
	}

	public void setDescription(String documentation) {
		this.descriptionLabel.setValue(documentation);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
}
