package com.jawesa.util;

import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence
 * context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources {
	// use @SuppressWarnings to tell IDE to ignore warnings about field not
	// being referenced directly
	// @SuppressWarnings("unused")
	// @Produces
	// @PersistenceContext
	// private EntityManager em;

//	@Produces
//	@PersistenceUnit
//	@ConversationScoped
//	EntityManagerFactory emf;
//
//	@Produces
//	public Logger produceLog(InjectionPoint injectionPoint) {
//		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass()
//				.getName());
//	}

//	public void setupEntityManager(
//			@Observes SeamManagedPersistenceContextCreated event) {
//		Session session = (Session) event.getEntityManager().getDelegate();
//		session.setFlushMode(FlushMode.MANUAL);
//	}
}
