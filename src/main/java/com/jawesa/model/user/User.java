package com.jawesa.model.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.jawesa.model.common.AbstractLongIdEntity;

@Entity
@Table(name = "users")
@NamedQueries({
		@NamedQuery(name = User.FIND_ALL, query = "from User"),
		@NamedQuery(name = User.FIND_BY_LOGIN, query = "from User where login = :login"),
		@NamedQuery(name = User.FIND_BY_EMAIL, query = "from User where email = :email"),
		@NamedQuery(name = User.FIND_ROLES_PERMISSIONS_BY_USER, query = "select user from User as user left join fetch user.roles as role left join fetch role.permissions as permission where user = :user") })
public class User extends AbstractLongIdEntity {

	public static final String FIND_ALL = "User.findAll";
	public static final String FIND_BY_LOGIN = "User.findByLogin";
	public static final String FIND_BY_EMAIL = "User.findByEmail";
	public static final String FIND_ROLES_PERMISSIONS_BY_USER = "User.findRolesPermissionsByUser";

	private String login;
	private String password;
	@Pattern(regexp = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9Hm.-]+\\.[a-zA-Z]{2,4}", message = "Email not valid")
	private String email;
	private String website;
	private boolean active;

	@ManyToMany
	private Set<Role> roles;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
