package com.jawesa.home.tag;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.jawesa.home.common.EntityLongIdHome;
import com.jawesa.model.tag.Tag;
import com.jawesa.model.tag.TagApi;
import com.jawesa.model.tag.TagAttribute;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class TagAttributeHome extends EntityLongIdHome<TagAttribute> {
	@Override
	protected void preDelete() {
		super.preDelete();
		
		TagAttribute instance = getInstance();
		for(TagApi api : instance.getApis()) {
			api.getAttributes().remove(instance);
		}
		
		for(Tag tag : instance.getTags()) {
			tag.getAttributes().remove(instance);
		}
	}
}
