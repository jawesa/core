package com.jawesa.model.common;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.TextType;
import org.hibernate.usertype.UserType;

public class I18nUserType implements UserType {
	public static final String NAME = "i18nType";

	@Override
	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		return (Serializable) this.deepCopy(arg0);
	}

	@Override
	public Object deepCopy(Object arg0) throws HibernateException {
		return arg0;
	}

	@Override
	public Serializable disassemble(Object arg0) throws HibernateException {
		return (Serializable) this.deepCopy(arg0);
	}

	@Override
	public boolean equals(Object arg0, Object arg1) throws HibernateException {
		if(arg0 == null)
		{
			return arg1 == null;
		}
		else if(arg1 == null)
		{
			return arg0 == null;
		}
		else
		{
			return arg0.toString().equals(arg1.toString());
		}
	}

	@Override
	public int hashCode(Object value) throws HibernateException {
		assert value != null;
		return value.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner) throws HibernateException,
			SQLException {
		assert names.length == 1;
		
		String value = (String) TextType.INSTANCE.get(rs, names[0], session);
		
		I18nLabel label = new I18nLabel();
		
		if(value != null)
		{
			label.explode(value);
		}
		
		return label;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws HibernateException, SQLException {
		if(value == null || !(value instanceof I18nLabel))
		{
			TextType.INSTANCE.set(st, null, index, session);
		}
		else
		{
			I18nLabel label = (I18nLabel)value;
			TextType.INSTANCE.set(st, label.serialize(), index, session);
		}

	}

	@Override
	public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		return this.deepCopy(arg0);
	}

	@Override
	public Class returnedClass() {
		return I18nLabel.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] {TextType.INSTANCE.sqlType()};
	}

}
