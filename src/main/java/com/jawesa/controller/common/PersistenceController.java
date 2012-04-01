package com.jawesa.controller.common;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jawesa.annotation.controller.PersistenceControllerQualifier;

@Stateless
@SuppressWarnings("serial")
@PersistenceControllerQualifier
public class PersistenceController extends Controller {
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	public void joinTransaction() {
		entityManager.joinTransaction();
	}
	
	public Transaction begin() {
		Session session = (Session) entityManager.getDelegate();
		return session.beginTransaction();
	}
	
	public void close(Transaction t) {
		t.commit();
		Session session = (Session) entityManager.getDelegate();
		session.close();
	}

	public void flush() {
		this.getEntityManager().flush();
	}

	public Query createNamedQuery(String name) {
		return getEntityManager().createNamedQuery(name);
	}

	public Query createQuery(String ejbql) {
		return getEntityManager().createQuery(ejbql);
	}

	public void lock(Object entity, LockModeType lockMode) {
		getEntityManager().lock(entity, lockMode);
	}

	public void persist(Object entity) {
		getEntityManager().persist(entity);
	}
	
	public Object merge(Object entity) {
		return getEntityManager().merge(entity);
	}

	public void remove(Object entity) {
		getEntityManager().remove(entity);
	}

	public Object updateObject(Object object) {
		return entityManager.merge(object);
	}

	public void createObject(Object object) {
		entityManager.persist(object);
	}

	public void refresh(Object object) {
		entityManager.refresh(object);
	}

	public <T> T find(Class<T> clazz, Object id) {
		return entityManager.find(clazz, id);
	}

	public void deleteObject(Object object) {
		entityManager.remove(object);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
