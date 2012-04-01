package com.jawesa.model.common;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractLongIdEntity extends AbstractBaseEntity<Long> implements Comparable<AbstractLongIdEntity> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public int compareTo(AbstractLongIdEntity o) {
		if(o == null) {
			return -1;
		} else if(o.getId() == null) {
			return -1;
		} else if(getId() == null) {
			return 1;
		} else {
			return getId().compareTo(o.getId());
		}
	}
}
