package com.jawesa.model.tag;

public enum TagAttributeType {

	string("String", "java.lang"), bool("Boolean"), integer("Integer"), longNumber(
			"Long"), doubleNumber("Double"), uiComponent("UIComponent",
			"javax.faces.component");

	private String name;
	private String packageName;
	private boolean elAccepted;

	private TagAttributeType(String name) {
		this(name, null);
	}

	private TagAttributeType(String name, String packageName) {
		this(name, packageName, true);
	}

	private TagAttributeType(String name, String packageName, boolean elAccepted) {
		this.name = name;
		this.packageName = packageName;
		this.elAccepted = elAccepted;
	}

	public String getDisplayValue() {
		StringBuffer sb = new StringBuffer();

		if (getPackageName() != null) {
			sb.append(getPackageName()).append(".");
		}

		sb.append(getName());

		if (isElAccepted()) {
			sb.append(" (EL accepted)");
		} else {
			sb.append(" (EL not accepted)");
		}

		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public boolean isElAccepted() {
		return elAccepted;
	}

	public void setElAccepted(boolean elAccepted) {
		this.elAccepted = elAccepted;
	}

}
