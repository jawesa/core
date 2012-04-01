package com.jawesa.home.tag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jawesa.controller.tag.TagApiController;
import com.jawesa.controller.tag.TagAttributeController;
import com.jawesa.home.common.EntityLongIdHome;
import com.jawesa.model.tag.Tag;
import com.jawesa.model.tag.TagApi;
import com.jawesa.model.tag.TagAttribute;
import com.jawesa.query.common.LongIdListDataModel;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class TagHome extends EntityLongIdHome<Tag> implements Serializable {
	@Inject
	private TagApiController tagApiController;
	
	@Inject
	private TagAttributeController tagAttributeController;
	
	@Inject
	private TagAttributeHome tagAttributeHome;
	
	private boolean isInit = false;
	
	// --APIs (and their attributes)--
	private TagApi[] selectedApis;
	private LongIdListDataModel<TagApi> apisModel;
	// No direct link between Tag and TagAttribute
	private Set<TagAttribute> apisAttributes;
	
	// --ATTRIBUTES--
	// Direct link between Tag and TagAttribute
	private TagAttribute[] selectedAttributes;
	private LongIdListDataModel<TagAttribute> attributesModel;
	// Not yet persisted, direct link
	private List<TagAttribute> newAttributes;

	public void init() {
		if (!isInit) {
			Set<TagApi> apis = getInstance().getApis();
			selectedApis = new TagApi[apis.size()];
			selectedApis = apis.toArray(selectedApis);
			apisAttributes = new HashSet<TagAttribute>();
			Set<TagAttribute> attributes = getInstance().getAttributes();
			for (TagApi api : apis) {
				attributes.addAll(api.getAttributes());
				apisAttributes.addAll(api.getAttributes());
			}
			selectedAttributes = new TagAttribute[attributes.size()];
			selectedAttributes = attributes.toArray(selectedAttributes);
			apisModel = new LongIdListDataModel<TagApi>(
					tagApiController.getTagApisWithAttributes());
			attributesModel = new LongIdListDataModel<TagAttribute>(
					tagAttributeController.getTagAttributes());
			newAttributes = new ArrayList<TagAttribute>();
			isInit = true;
		}
	}
	
	public List<TagAttribute> getAllTagAttributes() {
		Set<TagAttribute> resultSet = new HashSet<TagAttribute>();
		resultSet.addAll(apisAttributes);
		resultSet.addAll(Arrays.asList(selectedAttributes));
		List<TagAttribute> result = new ArrayList<TagAttribute>(resultSet);
		Collections.sort(result);
		return result;
	}

	public void reportApisSelection() {
		Set<TagApi> apis = new HashSet<TagApi>(Arrays.asList(selectedApis));
		getInstance().setApis(apis);

		apisAttributes = new HashSet<TagAttribute>();
		for (TagApi api : apis) {
			apisAttributes.addAll(api.getAttributes());
		}
	}

	public void reportAttributesSelection() {
		getInstance().setAttributes(
				new HashSet<TagAttribute>(Arrays.asList(selectedAttributes)));
	}

	public boolean isAttributeInApis(TagAttribute attribute) {
		return apisAttributes.contains(attribute);
	}
	
	public boolean isAttributeLinked(TagAttribute attribute) {
		return Arrays.asList(selectedAttributes).contains(attribute);
	}


	public void addNewAttribute() {
		newAttributes.add(tagAttributeHome.getInstance());
	}
	
	public void removeNewAttribute(String name) {
		newAttributes.remove(getIndexNewAttributeByName(name));
	}
	
	private int getIndexNewAttributeByName(String name) {
		for(int i = 0; i < newAttributes.size(); i++) {
			if(newAttributes.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		
		return -1;
	}
	

	public TagApi[] getSelectedApis() {
		return selectedApis;
	}

	public void setSelectedApis(TagApi[] selectedApis) {
		this.selectedApis = selectedApis;
	}

	public LongIdListDataModel<TagApi> getApisModel() {
		return apisModel;
	}

	public void setApisModel(LongIdListDataModel<TagApi> apisModel) {
		this.apisModel = apisModel;
	}

	public TagAttribute[] getSelectedAttributes() {
		return selectedAttributes;
	}

	public void setSelectedAttributes(TagAttribute[] selectedAttributes) {
		this.selectedAttributes = selectedAttributes;
	}

	public LongIdListDataModel<TagAttribute> getAttributesModel() {
		return attributesModel;
	}

	public void setAttributesModel(
			LongIdListDataModel<TagAttribute> attributesModel) {
		this.attributesModel = attributesModel;
	}

	public List<TagAttribute> getNewAttributes() {
		return newAttributes;
	}

	public void setNewAttributes(List<TagAttribute> newAttributes) {
		this.newAttributes = newAttributes;
	}
}
