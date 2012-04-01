package com.jawesa.action.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jawesa.action.common.Action;
import com.jawesa.controller.user.PermissionController;
import com.jawesa.model.user.Permission;
import com.jawesa.model.user.PermissionGroup;
import com.jawesa.util.JsfUtil;

@Named
@RequestScoped
public class PermissionAction extends Action {
	@Inject
	private PermissionController permissionController;
	
	private Map<PermissionGroup, List<Permission>> mapPermissions;
	
	@PostConstruct
	private void init() {
		loadPermissions();
	}
	
	public List<PermissionGroup> getPermissionsGroup() {
		List<PermissionGroup> result = JsfUtil.asList(mapPermissions.keySet());
		Collections.sort(result);
		return result;
	}
	
	public List<Permission> getPermissionsByPermissionGroup(PermissionGroup permissionGroup) {
		return mapPermissions.get(permissionGroup);
	}
	
	private void loadPermissions() {
		mapPermissions = new HashMap<PermissionGroup, List<Permission>>();
		
		for(Permission p : permissionController.getPermissionsWithGroupInOrder()) {
			if(mapPermissions.get(p.getPermissionGroup()) == null) {
				mapPermissions.put(p.getPermissionGroup(), new ArrayList<Permission>());
			}
			
			mapPermissions.get(p.getPermissionGroup()).add(p);
		}
	}
}
