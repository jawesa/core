package com.jawesa.model.user;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.jawesa.model.common.AbstractLongIdEntity;
import com.jawesa.model.common.I18nLabel;
import com.jawesa.model.common.I18nUserType;

@Entity
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQueries({
	@NamedQuery(name = Permission.FIND_ALL_WITH_GROUP, query = "select permission from Permission as permission join fetch permission.permissionGroup"),
	@NamedQuery(name = Permission.FIND_ALL_WITH_GROUP_IN_ORDER, query = "select permission from Permission as permission join fetch permission.permissionGroup as permissionGroup order by permissionGroup.orderBy asc, permission.target asc, permission.action asc")})
public class Permission extends AbstractLongIdEntity {
	public static final String FIND_ALL_WITH_GROUP = "Permission.findAllWithGroup";
	public static final String FIND_ALL_WITH_GROUP_IN_ORDER = "Permission.findAllWithGroupInOrder";
	
	private String action;
	private String target;
	private boolean readOnly;
	private boolean hidden;
	@Type(type = I18nUserType.NAME)
	private I18nLabel descriptionLabel = new I18nLabel();
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private PermissionGroup permissionGroup;
	
	@Override
	public int compareTo(AbstractLongIdEntity o) {
		if(o == null) {
			return -1;
		} else if(o instanceof Permission) {
			Permission p = (Permission) o;
			int c = getTarget().compareTo(p.getTarget());
			if(c == 0) {
				return getAction().compareTo(p.getAction());
			} else {
				return c;
			}
		} else {
			return -1;
		}
	}

	public String getDescription() {
		return this.descriptionLabel.getValue();
	}

	public void setDescription(String description) {
		this.descriptionLabel.setValue(description);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public PermissionGroup getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(PermissionGroup permissionGroup) {
		this.permissionGroup = permissionGroup;
	}
}
