package com.jawesa.model.tag;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.jawesa.model.common.AbstractLongIdEntity;

@Entity
@NamedQueries({ @NamedQuery(name = TagApi.FIND_ALL, query = "from TagApi"),
		@NamedQuery(name = TagApi.FIND_ALL_WITH_ATTRIBUTES, query = "select distinct tagApi from TagApi as tagApi left join fetch tagApi.attributes") })
public class TagApi extends AbstractLongIdEntity {
	public static final String FIND_ALL = "TagApi.findAll";
	public static final String FIND_ALL_WITH_ATTRIBUTES = "TagApi.findAllWithAttributes";
	private String name;
	private String description;
	@ManyToMany
	private Set<TagAttribute> attributes = new HashSet<TagAttribute>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<TagAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<TagAttribute> attributes) {
		this.attributes = attributes;
	}
}
