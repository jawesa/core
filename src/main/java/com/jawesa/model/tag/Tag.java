package com.jawesa.model.tag;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Type;

import com.jawesa.model.common.AbstractLongIdEntity;
import com.jawesa.model.common.I18nLabel;
import com.jawesa.model.common.I18nUserType;

@Entity
@NamedQueries({
		@NamedQuery(name = Tag.FIND_ALL, query = "from Tag"),
		@NamedQuery(name = Tag.FULL_TAG, query = "select tag from Tag as tag left join fetch tag.library left join fetch tag.attributes left join fetch tag.apis left join fetch tag.jsMethods where tag.id = :id"),
		@NamedQuery(name = Tag.ALL_FULL_TAGS, query = "select distinct tag from Tag as tag left join fetch tag.library left join fetch tag.attributes left join fetch tag.apis left join fetch tag.jsMethods"),
		@NamedQuery(name = Tag.FIND_BY_LIBRARY_AND_NAME, query = "select tag from Tag as tag join fetch tag.library as library where lower(tag.name) = lower(:tagName) and lower(library.name) = lower(:libraryName)")})
public class Tag extends AbstractLongIdEntity {
	public static final String FIND_ALL = "Tag.findAll";
	public static final String FULL_TAG = "Tag.fullTag";
	public static final String ALL_FULL_TAGS = "Tag.allFullTags";
	public static final String FIND_BY_LIBRARY_AND_NAME = "Tag.findByLibraryAndName";
	
	private String name;
	private String tagClass;
	private String tagType;
	private String tagFamily;
	private String renderedType;
	private String renderedClass;
	private String handlerClass;
	private String onlineDocumentation;
	@Type(type = I18nUserType.NAME)
	private I18nLabel documentationLabel;
	@ManyToOne
	private TagType type;
	@Enumerated(EnumType.STRING)
	private TagStatus status = TagStatus.DRAFT;
	@ManyToOne
	private Library library;
	@ManyToMany
	private Set<TagAttribute> attributes = new HashSet<TagAttribute>();
	@ManyToMany
	private Set<TagApi> apis = new HashSet<TagApi>();
	@ManyToMany
	private Set<JsMethod> jsMethods = new HashSet<JsMethod>();
	@ManyToMany
	private Set<TagDemo> demos = new HashSet<TagDemo>();
	
	public static String getAltKey(String libraryName, String tagName) {
		return (Library.getAltKey(libraryName)+"-"+tagName).toLowerCase();
	}

	public String getDocumentation() {
		return documentationLabel.getValue();
	}

	public void setDocumentation(String documentation) {
		this.documentationLabel.setValue(documentation);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTagClass() {
		return tagClass;
	}

	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getTagFamily() {
		return tagFamily;
	}

	public void setTagFamily(String tagFamily) {
		this.tagFamily = tagFamily;
	}

	public String getRenderedType() {
		return renderedType;
	}

	public void setRenderedType(String renderedType) {
		this.renderedType = renderedType;
	}

	public String getRenderedClass() {
		return renderedClass;
	}

	public void setRenderedClass(String renderedClass) {
		this.renderedClass = renderedClass;
	}

	public String getHandlerClass() {
		return handlerClass;
	}

	public void setHandlerClass(String handlerClass) {
		this.handlerClass = handlerClass;
	}

	public String getOnlineDocumentation() {
		return onlineDocumentation;
	}

	public void setOnlineDocumentation(String onlineDocumentation) {
		this.onlineDocumentation = onlineDocumentation;
	}

	public TagType getType() {
		return type;
	}

	public void setType(TagType type) {
		this.type = type;
	}

	public TagStatus getStatus() {
		return status;
	}

	public void setStatus(TagStatus status) {
		this.status = status;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public Set<TagAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<TagAttribute> attributes) {
		this.attributes = attributes;
	}

	public Set<TagApi> getApis() {
		return apis;
	}

	public void setApis(Set<TagApi> apis) {
		this.apis = apis;
	}

	public Set<JsMethod> getJsMethods() {
		return jsMethods;
	}

	public void setJsMethods(Set<JsMethod> jsMethods) {
		this.jsMethods = jsMethods;
	}

	public Set<TagDemo> getDemos() {
		return demos;
	}

	public void setDemos(Set<TagDemo> demos) {
		this.demos = demos;
	}
}
