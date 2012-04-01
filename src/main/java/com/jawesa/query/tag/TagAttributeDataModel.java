package com.jawesa.query.tag;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.jawesa.controller.tag.TagAttributeController;
import com.jawesa.model.tag.TagAttribute;

@Named
@SessionScoped
@SuppressWarnings("serial")
public class TagAttributeDataModel extends LazyDataModel<TagAttribute> {

	@Inject
	private TagAttributeController tagAttributeController;
	
	public TagAttributeDataModel() {
		this.setRowCount(4);
		this.setPageSize(10);
	}
	
	@Override
	public List<TagAttribute> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		this.setWrappedData(tagAttributeController.getByCriteria(first, pageSize, sortField, sortOrder, filters));
		return (List) this.getWrappedData();
	}

}
