package com.jawesa.model.common;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDomainIdEntity<PK> extends AbstractBaseEntity<PK> {
	@Id
	private PK id;

	@Override
	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}
}
