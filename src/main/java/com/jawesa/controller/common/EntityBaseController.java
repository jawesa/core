package com.jawesa.controller.common;

import java.io.Serializable;

import com.jawesa.model.common.AbstractBaseEntity;

@SuppressWarnings("serial")
public abstract class EntityBaseController<E extends AbstractBaseEntity<T>, T>
		extends PersistenceController implements Serializable {
	public E findById(Class<E> clazz, T id) {
		return find(clazz, id);
	}

	protected E getReference(Class<E> clazz, Object id) {
		return getReference(clazz, id);
	}

	protected E merge(E entity) {
		return merge(entity);
	}
}
