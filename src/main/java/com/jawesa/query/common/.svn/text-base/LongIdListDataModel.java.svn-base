package com.jawesa.query.common;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.jawesa.model.common.AbstractLongIdEntity;

public class LongIdListDataModel<E extends AbstractLongIdEntity> extends ListDataModel<E> implements SelectableDataModel<E> {
	public LongIdListDataModel() {
		
	}

	public LongIdListDataModel(List<E> entities) {
		super(entities);
	}

	@Override
	public Object getRowKey(E object) {
		return object.getId();
	}

	@Override
	public E getRowData(String rowKey) {
		Long id = Long.parseLong(rowKey);
		List<E> entities = (List<E>) getWrappedData();
		for (E entity : entities) {
			if (entity.getId().equals(id)) {
				return entity;
			}
		}
		return null;
	}
}
