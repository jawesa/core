package com.jawesa.controller.tag;

import java.util.List;

import javax.ejb.Stateless;

import com.jawesa.controller.common.EntityLongIdController;
import com.jawesa.model.tag.Library;

@Stateless
@SuppressWarnings("serial")
public class LibraryController extends EntityLongIdController<Library> {
	@SuppressWarnings("unchecked")
	public List<Library> findAll() {
		return createNamedQuery(Library.FIND_ALL).getResultList();
	}
}
