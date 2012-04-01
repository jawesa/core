package com.jawesa.model.tag;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Type;

import com.jawesa.model.common.AbstractLongIdEntity;
import com.jawesa.model.common.I18nLabel;
import com.jawesa.model.common.I18nUserType;

@Entity
@NamedQueries({ @NamedQuery(name = TagAttribute.FIND_ALL, query = "from TagAttribute") })
public class TagAttribute extends AbstractLongIdEntity {
	public static final String FIND_ALL = "TagAttribute.findAll";
	private String name;
	private boolean required;
	@Enumerated(EnumType.STRING)
	private TagAttributeType type;
	private String customType;
	@Type(type = I18nUserType.NAME)
	private I18nLabel descriptionLabel = new I18nLabel();
	@ManyToMany(mappedBy = "attributes")
	private Set<Tag> tags;
	@ManyToMany(mappedBy = "attributes")
	private Set<TagApi> apis;
	

	public int compareTo(TagAttribute o) {
		if(o == null) {
			return -1;
		} else if(getName() == null) {
			return (o.getName() == null ? 0 : 1);
		} else
		{
			return getName().compareToIgnoreCase(o.getName());
		}
	}
	
	public String getTypeDisplayValue() {
		if(getType() != null) {
			return getType().getDisplayValue();
		} else {
			return getCustomType();
		}
	}
	
	public String getDescription() {
		return descriptionLabel.getValue();
	}

	public void setDescription(String description) {
		this.descriptionLabel.setValue(description);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public TagAttributeType getType() {
		return type;
	}

	public void setType(TagAttributeType type) {
		this.type = type;
	}

	public String getCustomType() {
		return customType;
	}

	public void setCustomType(String customType) {
		this.customType = customType;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<TagApi> getApis() {
		return apis;
	}

	public void setApis(Set<TagApi> apis) {
		this.apis = apis;
	}
}
