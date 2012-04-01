package com.jawesa.model.tag;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.jawesa.action.tag.TagDemoSourceCodeAction;
import com.jawesa.model.common.AbstractLongIdEntity;
import com.jawesa.model.common.I18nLabel;
import com.jawesa.model.common.I18nUserType;
import com.jawesa.model.user.User;
import com.jawesa.rest.JaxRsActivator;

@Entity
public class TagDemo extends AbstractLongIdEntity {
	private String slug;
	@Column(columnDefinition="TEXT")
	private String sourceCode;
	@Type(type = I18nUserType.NAME)
	private I18nLabel nameLabel;
	@Type(type = I18nUserType.NAME)
	private I18nLabel descriptionBeforeLabel;
	@Type(type = I18nUserType.NAME)
	private I18nLabel descriptionAfterLabel;
	@Enumerated(EnumType.STRING)
	private TagDemoStatus status;
	@ManyToOne
	private User author;
	@ManyToMany
	private Set<SourceCode> sourceCodes;
	@OneToMany
	private Set<TagDemoComment> comments;
	
	public static String getAltKey(String libraryName, String tagName, String tagDemoSlug) {
		return (Tag.getAltKey(libraryName, tagName)+"-"+tagDemoSlug).toLowerCase();
	}
	
	public String getRestSourceCodePath() {
	    StringBuilder path = new StringBuilder();
	    // TODO
	    path.append("http://localhost:8080/jawesa");
	    path.append(JaxRsActivator.REST_PATH);
	    path.append(TagDemoSourceCodeAction.REST_PATH);
	    path.append("/");
	    path.append(getId());
	    path.append(TagDemoSourceCodeAction.REST_PATH_EXTENSION);
	    return path.toString();
	}
	
	public String getDescriptionBefore() {
		return descriptionBeforeLabel.getValue();
	}

	public void setDescriptionBefore(String description) {
		this.descriptionBeforeLabel.setValue(description);
	}

	public String getDescriptionAfter() {
		return descriptionAfterLabel.getValue();
	}

	public void setDescriptionAfter(String description) {
		this.descriptionAfterLabel.setValue(description);
	}

	public String getName() {
		return nameLabel.getValue();
	}

	public void setName(String name) {
		nameLabel.setValue(name);
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public TagDemoStatus getStatus() {
		return status;
	}

	public void setStatus(TagDemoStatus status) {
		this.status = status;
	}

	public Set<SourceCode> getSourceCodes() {
		return sourceCodes;
	}

	public void setSourceCodes(Set<SourceCode> sourceCodes) {
		this.sourceCodes = sourceCodes;
	}

	public Set<TagDemoComment> getComments() {
		return comments;
	}

	public void setComments(Set<TagDemoComment> comments) {
		this.comments = comments;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public I18nLabel getNameLabel() {
		return nameLabel;
	}
}
