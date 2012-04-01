package com.jawesa.controller.user;

import java.util.List;

import javax.ejb.Stateless;

import com.jawesa.controller.common.EntityLongIdController;
import com.jawesa.exception.user.UserNotFoundByEmailException;
import com.jawesa.exception.user.UserNotFoundByLoginException;
import com.jawesa.model.user.User;

@Stateless
@SuppressWarnings("serial")
public class UserController extends EntityLongIdController<User> {

	public User findByLogin(String login) throws UserNotFoundByLoginException {
		try {
			return (User) createNamedQuery(User.FIND_BY_LOGIN).setParameter(
					"login", login).getSingleResult();
		} catch (Exception e) {
			//hrow new UserNotFoundByLoginException();
			return null;
		}
	}

	public User findByEmail(String email) throws UserNotFoundByEmailException {
		try {
			return (User) createNamedQuery(User.FIND_BY_EMAIL).setParameter(
					"email", email).getSingleResult();
		} catch (Exception e) {
			//throw new UserNotFoundByEmailException();
			return null;
		}
	}

	public User authenticate(String login, String password) {
		User user = null;
		try {
			user = this.findByLogin(login);
		} catch (UserNotFoundByLoginException unfble) {
			info("User not found");
			return null;
		}

		// FIXME encodage password test
		// FIXME add exceptions
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			info("Wrong password dude");
		}

		return null;
	}

	public User loadRolesPermissions(User user) {
		if (user == null || user.getId() == null) {
			return user;
		} else {
			return (User) createNamedQuery(User.FIND_ROLES_PERMISSIONS_BY_USER)
					.setParameter("user", user).getSingleResult();
		}
	}

	public void register(User user) throws Exception {
		user.setActive(false);
		persist(user);
		flush();
	}

	public List<User> getUsers() {
		return getEntityManager().createNamedQuery(User.FIND_ALL)
				.getResultList();
	}

	public void activateUser(User user) {
		user.setActive(true);
		getEntityManager().merge(user);
	}
}
