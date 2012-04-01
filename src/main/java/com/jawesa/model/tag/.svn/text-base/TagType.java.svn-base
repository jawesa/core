package com.jawesa.model.tag;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.jawesa.model.common.AbstractLongIdEntity;
import com.jawesa.model.common.I18nLabel;
import com.jawesa.model.common.I18nUserType;

@Entity
public class TagType extends AbstractLongIdEntity {
	@Type(type = I18nUserType.NAME)
	private I18nLabel nameLabel;
	private int menuOrder;
	@OneToMany(mappedBy = "type")
	private Set<Tag> tags;

	public String getName() {
		return nameLabel.getValue();
	}

	public void setName(String name) {
		this.nameLabel.setValue(name);
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}
}
