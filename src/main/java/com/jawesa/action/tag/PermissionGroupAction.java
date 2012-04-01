package com.jawesa.action.tag;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jawesa.action.common.Action;
import com.jawesa.controller.user.PermissionGroupController;
import com.jawesa.model.user.PermissionGroup;

public class PermissionGroupAction extends Action {
	@Inject
	private PermissionGroupController permissionGroupController;
	
	@Produces
	@Named
	@ApplicationScoped
	public List<PermissionGroup> getPermissionGroups() {
		return permissionGroupController.getPermissionGroupsInOrder();
	}
}
