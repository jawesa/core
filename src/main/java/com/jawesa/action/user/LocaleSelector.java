package com.jawesa.action.user;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jawesa.action.common.CookieSelector;
import com.jawesa.annotation.qualifier.user.DefaultLocale;
import com.jawesa.annotation.qualifier.user.Selected;

@Named
@SessionScoped
@SuppressWarnings("serial")
public class LocaleSelector extends CookieSelector<Locale> {
	@Inject
	@DefaultLocale
	private String defaultLocale;
	
	@Inject
	private List<Locale> locales;

	@Inject
	private Event<Locale> localeEvent;

	@Inject
	private transient ResourceBundle messages;
	
	@Produces
	@Selected
	@Named
	public Locale getSelectedLocale() {
		if(value == null) {
			return string2value(defaultLocale);
		} else {
			return value;
		}
	}

	@Override
	public void select(Locale value) {
		super.select(value);
		
		localeEvent.fire(getValue());
	}

	@Override
	protected void initSelector() {
		defaultValue = string2value(defaultLocale);
		selectorName = "localeSelector";

		super.initSelector();
	}
	
	@Override
	protected void loadAvailableValues() {
		this.availableValues = locales;
	}

	@Override
	protected String value2string(Locale value) {
		return value.toString();
	}

	@Override
	protected Locale string2value(String stringValue) {
		String[] values = stringValue.split("_");
		return (new Locale(values[0], values[1]));
	}

	@Override
	protected String getDisplayValue(Locale value) {
		return messages.getString("common.locale."+value.toString()+".label");
	}

	@Override
	protected boolean isMenuModelEnable() {
		return true;
	}

}
