package com.jawesa.controller.tag;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.jawesa.controller.common.EntityLongIdController;
import com.jawesa.exception.tag.TagNotFounException;
import com.jawesa.model.tag.Tag;

@Stateless
@SuppressWarnings("serial")
public class TagController extends EntityLongIdController<Tag> {
	public List<Tag> getTags() {
		return createNamedQuery(Tag.FIND_ALL)
				.getResultList();
	}

	public Tag getTagByLibraryAndName(String libraryName, String tagName) {
		try {
			return (Tag) createNamedQuery(Tag.FIND_BY_LIBRARY_AND_NAME)
					.setParameter("libraryName", libraryName)
					.setParameter("tagName", tagName).getSingleResult();
		} catch (NoResultException nre) {
			throw new TagNotFounException();
		} catch (NonUniqueResultException nure) {
			throw new TagNotFounException();
		}
	}
	
	// Load a tag, including :
	// - library
	// - attributes
	// - apis
	// - jsMethods
	public Tag getFullTag(Long id) {
		return (Tag) createNamedQuery(Tag.FULL_TAG).setParameter("id", id).getSingleResult();
	}
	
	public Tag getFullTag(Tag tag) {
		return getFullTag(tag.getId());
	}
	
	public List<Tag> getAllFullTags() {
		return createNamedQuery(Tag.ALL_FULL_TAGS).getResultList();
	}
}
