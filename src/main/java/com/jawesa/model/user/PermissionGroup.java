package com.jawesa.model.user;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
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
	@NamedQuery(name = PermissionGroup.FIND_ALL_IN_ORDER, query = "from PermissionGroup order by orderBy asc")})
public class PermissionGroup extends AbstractLongIdEntity {
	public static final String FIND_ALL_IN_ORDER = "PermissionGroup.findAllInOrder";
	
	private int orderBy;
	@Type(type = I18nUserType.NAME)
	private I18nLabel nameLabel = new I18nLabel();

	public String getName() {
		return this.nameLabel.getValue();
	}

	public void setName(String name) {
		this.nameLabel.setValue(name);
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
}
