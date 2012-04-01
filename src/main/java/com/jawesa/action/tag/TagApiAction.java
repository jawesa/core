package com.jawesa.action.tag;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jawesa.action.common.Action;
import com.jawesa.controller.tag.TagApiController;
import com.jawesa.model.tag.TagApi;

@SuppressWarnings("serial")
@ViewScoped
public class TagApiAction extends Action {
	@Inject
	private TagApiController tagApiController;

	@Produces
	@Named
	public List<TagApi> getTagApis() {
		return tagApiController.getTagApis();
	}
	
	@Produces
	@Named
	public List<TagApi> getTagApisWithAttributes() {
		return tagApiController.getTagApisWithAttributes();
	}
}
