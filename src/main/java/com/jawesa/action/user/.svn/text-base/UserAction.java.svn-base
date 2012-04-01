package com.jawesa.action.user;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jawesa.action.common.Action;
import com.jawesa.controller.user.UserController;
import com.jawesa.model.user.User;

@Named
@SuppressWarnings("serial")
@ViewScoped
public class UserAction extends Action {
	@Inject
	private UserController userController;

	private User newUser;

	@PostConstruct
	public void initNewUser() {
		newUser = new User();
	}

	
	public String register() throws Exception {
		User user = null;
		
		user = userController.findByEmail(newUser.getEmail());;
		if(user != null) {
			userController.warn("Email already exist");
		} else {
			user = userController.findByLogin(newUser.getLogin());
			if(user != null) {
				userController.warn("Login already exist");
			} else {
				userController.register(newUser);
				initNewUser();
			}
		}
		
		if (user == null) {
			return "/index";
		} else {
			return null;
		}
	}

	@Produces
	@Named
	@ConversationScoped
	public List<User> getUsers() {
		return userController.getUsers();
	}


	public User getNewUser() {
		return newUser;
	}


	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}
}
