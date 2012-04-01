package com.jawesa.action.user;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jawesa.action.common.Action;
import com.jawesa.controller.user.UserController;
import com.jawesa.model.user.User;

@Named
@ConversationScoped
@SuppressWarnings("serial")
public class LoginAction extends Action {

	@Inject
	private Credentials credentials;

	@Inject
	private UserController userController;

	@Inject
	private Identity identity;

	public String login() {
		User user = userController.authenticate(credentials.getUsername(),
				credentials.getPassword());
		if (user != null) {
			user = userController.loadRolesPermissions(user);
			identity.login(user);
			return "/index";
		}
		return null;
	}

	public String logout() {
		identity.logout();
		return "/index";
	}
}