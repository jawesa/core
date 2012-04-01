package com.jawesa.controller.tag;

import java.util.List;

import javax.ejb.Stateless;

import com.jawesa.controller.common.EntityLongIdController;
import com.jawesa.model.tag.TagApi;

@Stateless
@SuppressWarnings("serial")
public class TagApiController extends EntityLongIdController<TagApi> {
	public List<TagApi> getTagApis() {
		return createNamedQuery(TagApi.FIND_ALL)
				.getResultList();
	}
	
	public List<TagApi> getTagApisWithAttributes() {
		return createNamedQuery(TagApi.FIND_ALL_WITH_ATTRIBUTES)
				.getResultList();
	}
}
