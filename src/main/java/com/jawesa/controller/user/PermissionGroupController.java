package com.jawesa.controller.user;

import java.util.List;

import javax.ejb.Stateless;

import com.jawesa.controller.common.EntityLongIdController;
import com.jawesa.model.user.PermissionGroup;

@Stateless
public class PermissionGroupController extends EntityLongIdController<PermissionGroup> {
	public List<PermissionGroup> getPermissionGroupsInOrder() {
		return createNamedQuery(PermissionGroup.FIND_ALL_IN_ORDER).getResultList();
	}
}
