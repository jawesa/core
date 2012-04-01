package com.jawesa.query.tag;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.jawesa.model.tag.TagAttributeType;
import com.jawesa.model.tag.TagDemoStatus;
import com.jawesa.model.tag.TagStatus;

public class TagEnumUtil {
	@Produces
	@Named
	@ApplicationScoped
	public List<TagAttributeType> getTagAttributeList() {
		return Arrays.asList(TagAttributeType.values());
	}
	
	@Produces
	@Named
	@ApplicationScoped
	public List<TagStatus> getTagStatusList() {
		return Arrays.asList(TagStatus.values());
	}
	
	@Produces
	@Named
	@ApplicationScoped
	public List<TagDemoStatus> getTagDemoStatusList() {
		return Arrays.asList(TagDemoStatus.values());
	}
}
