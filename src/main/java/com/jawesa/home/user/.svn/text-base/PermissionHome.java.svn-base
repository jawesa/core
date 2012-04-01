package com.jawesa.home.user;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.jawesa.home.common.EntityLongIdHome;
import com.jawesa.model.user.Permission;
import com.jawesa.model.user.Role;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class PermissionHome extends EntityLongIdHome<Permission> {
	@PostConstruct
	public void init() {
		System.out.println("Post construct permission home");
	}
	
	@Override
	protected void preDelete() {
		super.preDelete();
		
		List<Role> roles = getPersistenceController().createQuery("from Role").getResultList();
		
		for(Role role : roles) {
			if(role.getPermissions().contains(getInstance())) {
				role.getPermissions().remove(getInstance());
			}
		}
	}
}
