package com.jawesa.home.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.inject.Inject;

import com.jawesa.action.user.Identity;
import com.jawesa.annotation.controller.PersistenceControllerQualifier;
import com.jawesa.controller.common.MutableController;
import com.jawesa.controller.common.PersistenceController;
import com.jawesa.model.common.AbstractBaseEntity;

@SuppressWarnings("serial")
public abstract class Home<E extends AbstractBaseEntity<T>, T> extends
		MutableController implements Serializable {
	@Inject
	@PersistenceControllerQualifier
	private PersistenceController persistenceController;
	private T id;
	protected E instance;
	private Class<E> entityClass;

	public E getInstance() {
		if (instance == null) {
			if (id != null) {
				instance = loadInstance();
			} else {
				instance = createInstance();
			}
		}
		return instance;
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		if (setDirty(this.id, id)) {
			instance = null;
		}
		this.id = id;
	}

	/**
	 * Same as setId but without reseting the instance Used after a persist to
	 * update only the id of the home
	 * 
	 * @param id
	 */
	public void assignId(T id) {
		setDirty(this.id, id);
		this.id = id;
	}

	public void setInstance(E newInstance) {
		// TODO : remove comment when compare is possible without id : setDirty(getInstance(), newInstance);
		instance = newInstance;
	}

	protected E loadInstance() {
		return getPersistenceController().find(getEntityClass(), getId());
	}

	protected E createInstance() {
		try {
			return getEntityClass().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Class<E> getEntityClass() {
		if (entityClass == null) {
			Type type = getClass().getGenericSuperclass();

			if (type instanceof Class) {
				// likely dealing with a Weld Proxy
				Class clazz = (Class) type;
				type = clazz.getGenericSuperclass();
			}

			if (type instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) type;
				entityClass = (Class<E>) paramType.getActualTypeArguments()[0];
			} else {
				throw new IllegalArgumentException(
						"Could not guess entity class by reflection");
			}
		}

		return entityClass;
	}

	public boolean isManaged() {
		return getInstance().getId() != null;
	}

	public String save() {
		if (isManaged()) {
			update();
		} else {
			create();
		}
		return getSaveSuccessReturn();
	}

	public String create() {
		preCreate();
		getPersistenceController().persist(getInstance());
		getPersistenceController().flush();
		assignId(getInstance().getId());
		postCreate();
		return getCreateSuccessReturn();
	}

	public String update() {
		preUpdate();
		getPersistenceController().merge(getInstance());
		getPersistenceController().flush();
		postUpdate();
		return getUpdateSuccessReturn();
	}

	public String delete() {
		preDelete();
		getPersistenceController().remove(
				getPersistenceController().merge(getInstance()));
		getPersistenceController().flush();
		postDelete();
		return getDeleteSuccessReturn();
	}

	public String cancel() {
		preCancel();
		refresh();
		postCancel();
		return getCancelSuccessReturn();
	}

	public void refresh() {
		if (isManaged()) {
			getPersistenceController().refresh(
					getPersistenceController().merge(getInstance()));
		} else {
			setInstance(null);
		}
	}

	public void clear() {
		setInstance(null);
		setId(null);
	}

	protected PersistenceController getPersistenceController() {
		return persistenceController;
	}

	public boolean hasAccessInstance(Identity identity) {
		return true;
	}

	private String getListView() {
		return "list";
	}

	protected String getCreateSuccessReturn() {
		return getListView();
	}

	protected String getUpdateSuccessReturn() {
		return getListView();
	}

	protected String getDeleteSuccessReturn() {
		return getListView();
	}

	protected String getSaveSuccessReturn() {
		return getListView();
	}

	protected String getCancelSuccessReturn() {
		return getListView();
	}

	protected String getCreateSuccessMessage() {
		return "Entity créée avec succès";
	}

	protected String getUpdateSuccessMessage() {
		return "Entity mise à jour avec succès";
	}

	protected String getDeleteSuccessMessage() {
		return "Entity supprimée avec succès";
	}

	protected String getCancelSuccessMessage() {
		return "Opération annulée";
	}

	protected void preSave() {

	}

	protected void postSave() {

	}

	protected void preRead() {

	}

	protected void postRead() {

	}

	protected void preCreate() {

	}

	protected void postCreate() {
		getPersistenceController().info(getCreateSuccessMessage());
	}

	protected void preUpdate() {

	}

	protected void postUpdate() {
		getPersistenceController().info(getUpdateSuccessMessage());
	}

	protected void preDelete() {

	}

	protected void postDelete() {
		getPersistenceController().info(getDeleteSuccessMessage());
	}

	protected void preCancel() {

	}

	protected void postCancel() {
		getPersistenceController().info(getCancelSuccessMessage());
	}

	protected void preRefresh() {

	}

	protected void postRefresh() {

	}
}
