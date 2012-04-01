package com.jawesa.model.common;

import javax.persistence.MappedSuperclass;

import org.hibernate.proxy.HibernateProxyHelper;

@MappedSuperclass
public abstract class AbstractBaseEntity<PK> extends AbstractVersionedEntity {
	public abstract PK getId();

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(final Object other) {
		if (other == null)
			return false;
		if (this == other)
			return true;

		// ///// 1. We are the classes compatible?

		// To check the class compatibility types, we need to get rid
		// of (Hibernate) proxies (used for lazy loading) that may be in our
		// way.
		Class<?> otherClassNoProxy = HibernateProxyHelper
				.getClassWithoutInitializingProxy(other);
		Class<?> thisClassNoProxy = HibernateProxyHelper
				.getClassWithoutInitializingProxy(this);

		// if this and other are not equals, and no one is ancestor of the
		// other, then it's not comparable.
		// Example: thisClassNoProxy == Worker.class; otherClassNoProxy ==
		// BankAccount.class -> not compatible.
		// Other example: thisClassNoProxy == SavingBankAccount (that extends
		// BankAccount)
		// otherClassNoProxy == BankAccount (because other is a not loaded yet
		// proxy
		// and Hibernate does not know yet which concrete implementation of
		// BankAccount it will be)
		// -> probably comparable (the further id compare will fail if
		// otherClassNoProxy was not a saving account)
		if (!otherClassNoProxy.isAssignableFrom(thisClassNoProxy)
				&& !thisClassNoProxy.isAssignableFrom(otherClassNoProxy)) {
			return false;
		}

		// ///// 2. Are the ids equal?

		if (!(other instanceof AbstractBaseEntity)) {
			// Should never happen because the previous check on the classes:
			// other is always a BaseEntity => Identifiable
			// Defensive programming ;-)
			throw new RuntimeException(
					"Probably Bug: how can other be assignableFrom us (or vis versa) "
							+ "and not being a BaseEntity? other=[" + other
							+ "] - this=[" + this + "]");
		}
		AbstractBaseEntity<PK> otherEntity = (AbstractBaseEntity<PK>) other;

		if (this.getId() != null && otherEntity.getId() != null) {
			return this.getId().equals(otherEntity.getId());
		} else {
			return extEquals(other);
		}
	}

	@Override
	public int hashCode() {
		if (this.getId() == null) {
			return extHashCode();
		}

		return this.getId().hashCode();
	}
	
	public int extHashCode() {
		return 0;
//		throw new RuntimeException(
//				"Bug: We should not call hashCode on an entity that have not been persisted yet "
//						+ "because they have a null id. Because the equals is based on id, hashCode must be based on id too. "
//						+ "If hashCode is called before the id is assigned, then the hashCode will change if called later when "
//						+ "the id will have been assigned. But hashCode never can change (must always return the same value). "
//						+ "This exceptin may happen because you have put an entity in a collection "
//						+ "(an HashSet, maybe) that calls hashCode, before you have persisted it. "
//						+ "It's typically the case when you put an entity in a "
//						+ "-toMany relationship before you call EntityManager.persist on that entity. "
//						+ "Using this BaseEntity class constraints you not doing that (else subtle bugs may arrive by the back door).");
	}

	public boolean extEquals(final Object other) {
		return true;
		 // An id is null => we cannot (should not) compare!
//		throw new RuntimeException(
//				"Bug: We should not compare entities that have not been persisted yet "
//						+ "because they have a null id. This may happen because you have put an entity in a collection "
//						+ "(a Set, maybe) before you have persisted it. It's typically the case when you put an entity in a "
//						+ "-toMany relationship before you call EntityManager.persist on that entity. "
//						+ "Using this BaseEntity class constraint you not doing that (else subtle bugs may arrive by the back door).");
	}
}
