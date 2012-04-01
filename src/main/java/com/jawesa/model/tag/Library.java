package com.jawesa.model.tag;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.jawesa.model.common.AbstractLongIdEntity;
import com.jawesa.model.common.I18nLabel;
import com.jawesa.model.common.I18nUserType;

@Entity
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQueries({ @NamedQuery(name = Library.FIND_ALL, query = "from Library") })
public class Library extends AbstractLongIdEntity {
	public static final String FIND_ALL = "Library.findAll";
	
	private String name;
	private String usedVersion;
	private String website;
	@Type(type = I18nUserType.NAME)
	private I18nLabel descriptionLabel;
	@OneToMany
	private Set<Tag> tags;
	
	public static String getAltKey(String libraryName) {
		return libraryName.toLowerCase();
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

	public String getUsedVersion() {
		return usedVersion;
	}

	public void setUsedVersion(String usedVersion) {
		this.usedVersion = usedVersion;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
}
