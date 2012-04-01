package com.jawesa.action.user;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jawesa.action.common.CookieSelector;
import com.jawesa.annotation.user.Theme;
import com.jawesa.util.ThemeBundles;

@Named
@SessionScoped
@SuppressWarnings("serial")
public class ThemeSelector extends CookieSelector<String> {

	@Inject
	private ThemeBundles themeBundles;

	private ResourceBundle theme;

	@Override
	protected String value2string(String value) {
		return value;
	}

	@Override
	protected String string2value(String stringValue) {
		return stringValue;
	}

	@Override
	protected void loadAvailableValues() {
		List<String> availableValues = new ArrayList<String>();
		availableValues.add("default");
		availableValues.add("dark");
		this.availableValues = availableValues;
	}

	@Override
	protected String getDisplayValue(String value) {
		return value.replaceFirst(".", (value.charAt(0) + "").toUpperCase());
	}

	@Override
	protected boolean isMenuModelEnable() {
		return true;
	}

	@Override
	protected void initSelector() {
		defaultValue = "default";
		selectorName = "themeSelector";

		super.initSelector();
	}

	@Theme
	@Produces
	@Named
	ResourceBundle getTheme() {
		if (theme == null || clearDirty()) {
			initTheme();
		}

		return theme;
	}

	public List<Entry<String, String>> getListTheme() {
		List<Entry<String, String>> result = new ArrayList<Entry<String, String>>();

		if (theme == null || clearDirty()) {
			initTheme();
		}

		for (String key : theme.keySet()) {
			result.add((new AbstractMap.SimpleEntry<String, String>(key, theme
					.getString(key))));
		}

		return result;
	}

	private void initTheme() {
		theme = themeBundles.get(getValue());
	}

}
