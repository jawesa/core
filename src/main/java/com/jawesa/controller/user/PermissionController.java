package com.jawesa.controller.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.jawesa.controller.common.EntityLongIdController;
import com.jawesa.model.user.Permission;

@Stateless
@SuppressWarnings("serial")
public class PermissionController extends EntityLongIdController<Permission> {
	// FIXE user a query
	@SuppressWarnings("unchecked")
	@Produces
	@Named
	public List<Permission> getPermissions() {
		return getEntityManager().createQuery("from Permission order by target asc, action asc").getResultList();
	}
	
	public List<Permission> getPermissionsWithGroupInOrder() {
		return createNamedQuery(Permission.FIND_ALL_WITH_GROUP_IN_ORDER).getResultList();
	}
}
