package com.jawesa.model.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Type;

import com.jawesa.model.common.AbstractLongIdEntity;
import com.jawesa.model.common.I18nLabel;
import com.jawesa.model.common.I18nUserType;

@Entity
public class Role extends AbstractLongIdEntity {
	private String slug;
	private boolean readOnly;
	private boolean hidden;
	@Type(type = I18nUserType.NAME)
	private I18nLabel nameLabel;
	@ManyToMany
	private Set<Permission> permissions;

	public String getName() {
		return this.nameLabel.getValue();
	}

	public void setName(String name) {
		this.nameLabel.setValue(name);
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
}
