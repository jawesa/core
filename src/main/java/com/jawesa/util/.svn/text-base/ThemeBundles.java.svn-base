package com.jawesa.util;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@SuppressWarnings("serial")
public class ThemeBundles implements Serializable {
	// Map<theme name, bundle>
	private Map<String, ResourceBundle> bundles = new ConcurrentHashMap<String, ResourceBundle>();

	public ResourceBundle get(String theme) {
		if (!bundles.containsKey(theme)) {
			ResourceBundle bundle = ResourceBundle.getBundle(ThemeBundles
					.getThemeBundleNamePrefix() + theme);
			bundles.put(theme, bundle);
		}

		return bundles.get(theme);
	}

	public static String getThemeBundleNamePrefix() {
		return "theme.theme_";
	}
}
