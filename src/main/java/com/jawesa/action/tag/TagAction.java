package com.jawesa.action.tag;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jawesa.action.common.Action;
import com.jawesa.controller.tag.TagController;
import com.jawesa.model.tag.Tag;

@SuppressWarnings("serial")
@ViewScoped
public class TagAction extends Action {
	@Inject
	private TagController tagController;

	@Produces
	@Named
	public List<Tag> getTags() {
		return tagController.getTags();
	}
}
