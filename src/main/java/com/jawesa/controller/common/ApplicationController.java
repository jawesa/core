package com.jawesa.controller.common;

import java.util.List;

import com.jawesa.model.common.Application;
import com.jawesa.model.tag.Tag;

@SuppressWarnings("serial")
public class ApplicationController extends EntityLongIdController<Application> {
	@SuppressWarnings("unchecked")
	public List<Tag> initTagCache() {
		return createNamedQuery(Application.INIT_TAG_CACHE).getResultList();
	}
}
