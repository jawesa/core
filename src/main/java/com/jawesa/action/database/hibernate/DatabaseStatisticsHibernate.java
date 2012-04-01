package com.jawesa.action.database.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.stat.SessionStatistics;
import org.hibernate.stat.Statistics;

import com.jawesa.action.database.DatabaseStatistics;
import com.jawesa.util.Convert;
import com.jawesa.util.Format;

@SuppressWarnings("serial")
public class DatabaseStatisticsHibernate implements DatabaseStatistics {
	public static final String MESSAGES_PREFIX = "cache.statistics.";
	public static final String MESSAGES_PREFIX_SESSION = "cache.sessionStatistics.";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private Map<String, String> sessionStatistics;
	@SuppressWarnings("rawtypes")
	private List sessionEntityKeys;
	@SuppressWarnings("rawtypes")
	private List sessionCollectionKeys;
	private Map<String, String> sessionFactoryStatistics;
	private Map<String, String> globalEntityStatistics;
	private List<EntityStatisticsHibernate> entityStatistics;
	private Map<String, String> globalQueryStatistics;
	private List<QueryStatisticsHibernate> queryStatistics;
	private Map<String, String> globalCollectionStatistics;
	private List<CollectionStatisticsHibernate> collectionStatistics;
	private Map<String, String> globalSecondLevelCacheStatistics;
	private List<SecondLevelCacheStatisticsHibernate> secondLevelCacheStatistics;
	
	@Override
	public String getImplementationName() {
		return "Hibernate";
	}
	
	@PostConstruct
	@SuppressWarnings("unused")
	private void init() {
		Session session = getSession();
		Statistics statistics = session.getSessionFactory().getStatistics();
		SessionStatistics sessionStatistics = session.getStatistics();
		
		initSessionStatistics(sessionStatistics);
		initSessionEntityKeys(sessionStatistics);
		initSessionCollectionKeys(sessionStatistics);
		
		initSessionFactoryStatistics(statistics);
		
		initGlobalEntityStatistics(statistics);
		initEntityStatistics(statistics);
		
		initGlobalQueryStatistics(statistics);
		initQueryStatistics(statistics);
		
		initGlobalCollectionStatistics(statistics);
		initCollectionStatistics(statistics);
		
		initGlobalSecondLevelCacheStatistics(statistics);
		initSecondLevelCacheStatistics(statistics);
	}
	
	private void initSessionStatistics(SessionStatistics sessionStatistics) {
		this.sessionStatistics = new LinkedHashMap<String,String>();
		this.sessionStatistics.put(MESSAGES_PREFIX_SESSION+"entityCount", Convert.intToString(sessionStatistics.getEntityCount()));
		this.sessionStatistics.put(MESSAGES_PREFIX_SESSION+"collectionCount", Convert.intToString(sessionStatistics.getCollectionCount()));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initSessionEntityKeys(SessionStatistics sessionStatistics) {
		sessionEntityKeys = new ArrayList(sessionStatistics.getEntityKeys());
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initSessionCollectionKeys(SessionStatistics sessionStatistics) {
		sessionCollectionKeys = new ArrayList(sessionStatistics.getCollectionKeys());
	}
	
	private void initSessionFactoryStatistics(Statistics statistics) {
		sessionFactoryStatistics = new LinkedHashMap<String,String>();
		Calendar startTime = Calendar.getInstance();
		startTime.setTimeInMillis(statistics.getStartTime());
		sessionFactoryStatistics.put(MESSAGES_PREFIX+"startTime", Format.calendarToDatetime(startTime));
		sessionFactoryStatistics.put(MESSAGES_PREFIX+"sessionOpenCount", Convert.longToString(statistics.getSessionOpenCount()));
		sessionFactoryStatistics.put(MESSAGES_PREFIX+"sessionCloseCount", Convert.longToString(statistics.getSessionCloseCount()));
		sessionFactoryStatistics.put(MESSAGES_PREFIX+"connectCount", Convert.longToString(statistics.getConnectCount()));
		sessionFactoryStatistics.put(MESSAGES_PREFIX+"transactionCount", Convert.longToString(statistics.getTransactionCount()));
		sessionFactoryStatistics.put(MESSAGES_PREFIX+"successfulTransactionCount", Convert.longToString(statistics.getSuccessfulTransactionCount()));
		sessionFactoryStatistics.put(MESSAGES_PREFIX+"flushCount", Convert.longToString(statistics.getFlushCount()));
		sessionFactoryStatistics.put(MESSAGES_PREFIX+"prepareStatementCount", Convert.longToString(statistics.getPrepareStatementCount()));
		sessionFactoryStatistics.put(MESSAGES_PREFIX+"closeStatementCount", Convert.longToString(statistics.getCloseStatementCount()));
	}
	
	private void initGlobalEntityStatistics(Statistics statistics) {
		globalEntityStatistics = new LinkedHashMap<String,String>();
		globalEntityStatistics.put(MESSAGES_PREFIX+"entityFetchCount", Convert.longToString(statistics.getEntityFetchCount()));
		globalEntityStatistics.put(MESSAGES_PREFIX+"entityLoadCount", Convert.longToString(statistics.getEntityLoadCount()));
		globalEntityStatistics.put(MESSAGES_PREFIX+"entityInsertCount", Convert.longToString(statistics.getEntityInsertCount()));
		globalEntityStatistics.put(MESSAGES_PREFIX+"entityUpdateCount", Convert.longToString(statistics.getEntityUpdateCount()));
		globalEntityStatistics.put(MESSAGES_PREFIX+"entityDeleteCount", Convert.longToString(statistics.getEntityDeleteCount()));
		globalEntityStatistics.put(MESSAGES_PREFIX+"optimisticFailureCount", Convert.longToString(statistics.getOptimisticFailureCount()));
	}
	
	private void initEntityStatistics(Statistics statistics) {
		entityStatistics = new ArrayList<EntityStatisticsHibernate>();
		String[] entityNames = statistics.getEntityNames();
		Arrays.sort(entityNames);
		for(String entityName : entityNames) {
			entityStatistics.add(new EntityStatisticsHibernate(entityName, statistics.getEntityStatistics(entityName)));
		}
	}
	
	private void initGlobalQueryStatistics(Statistics statistics) {
		globalQueryStatistics = new LinkedHashMap<String,String>();
		double ratio = 0.0;
		if(statistics.getQueryCacheHitCount() + statistics.getQueryCacheMissCount() > 0) {
			ratio = statistics.getQueryCacheHitCount() / (statistics.getQueryCacheHitCount() + statistics.getQueryCacheMissCount());
		}
		globalQueryStatistics.put(MESSAGES_PREFIX+"queryCacheHitRatio", Format.doubleToPercent(ratio));
		globalQueryStatistics.put(MESSAGES_PREFIX+"queryCacheHitCount", Convert.longToString(statistics.getQueryCacheHitCount()));
		globalQueryStatistics.put(MESSAGES_PREFIX+"queryCacheMissCount", Convert.longToString(statistics.getQueryCacheMissCount()));
		globalQueryStatistics.put(MESSAGES_PREFIX+"queryCachePutCount", Convert.longToString(statistics.getQueryCachePutCount()));
		globalQueryStatistics.put(MESSAGES_PREFIX+"queryExecutionCount", Convert.longToString(statistics.getQueryExecutionCount()));
		globalQueryStatistics.put(MESSAGES_PREFIX+"queryExecutionMaxTime", Convert.longToString(statistics.getQueryExecutionMaxTime()));
		globalQueryStatistics.put(MESSAGES_PREFIX+"queryExecutionMaxTimeQueryString", statistics.getQueryExecutionMaxTimeQueryString());
	}
	
	private void initQueryStatistics(Statistics statistics) {
		queryStatistics = new ArrayList<QueryStatisticsHibernate>();
		for(String queryString : statistics.getQueries()) {
			queryStatistics.add(new QueryStatisticsHibernate(queryString, statistics.getQueryStatistics(queryString)));
		}
	}
	
	private void initGlobalCollectionStatistics(Statistics statistics) {
		globalCollectionStatistics = new LinkedHashMap<String,String>();
		globalCollectionStatistics.put(MESSAGES_PREFIX+"collectionFetchCount", Convert.longToString(statistics.getCollectionFetchCount()));
		globalCollectionStatistics.put(MESSAGES_PREFIX+"collectionLoadCount", Convert.longToString(statistics.getCollectionLoadCount()));
		globalCollectionStatistics.put(MESSAGES_PREFIX+"collectionRecreateCount", Convert.longToString(statistics.getCollectionRecreateCount()));
		globalCollectionStatistics.put(MESSAGES_PREFIX+"collectionUpdateCount", Convert.longToString(statistics.getCollectionUpdateCount()));
		globalCollectionStatistics.put(MESSAGES_PREFIX+"collectionRemoveCount", Convert.longToString(statistics.getCollectionRemoveCount()));
	}
	
	private void initCollectionStatistics(Statistics statistics) {
		collectionStatistics = new ArrayList<CollectionStatisticsHibernate>();
		String[] collectionRoleNames = statistics.getCollectionRoleNames();
		Arrays.sort(collectionRoleNames);
		for(String role : collectionRoleNames) {
			collectionStatistics.add(new CollectionStatisticsHibernate(role, statistics.getCollectionStatistics(role)));
		}
	}
	
	private void initGlobalSecondLevelCacheStatistics(Statistics statistics) {
		globalSecondLevelCacheStatistics = new LinkedHashMap<String,String>();
		double ratio = 0.0;
		if(statistics.getSecondLevelCacheHitCount() + statistics.getSecondLevelCacheMissCount() > 0) {
			ratio = statistics.getSecondLevelCacheHitCount() / (statistics.getSecondLevelCacheHitCount() + statistics.getSecondLevelCacheMissCount());
		}
		globalSecondLevelCacheStatistics.put(MESSAGES_PREFIX+"secondLevelCacheHitRatio",Format.doubleToPercent(ratio));
		globalSecondLevelCacheStatistics.put(MESSAGES_PREFIX+"secondLevelCacheHitCount", Convert.longToString(statistics.getSecondLevelCacheHitCount()));
		globalSecondLevelCacheStatistics.put(MESSAGES_PREFIX+"secondLevelCacheMissCount", Convert.longToString(statistics.getSecondLevelCacheMissCount()));
		globalSecondLevelCacheStatistics.put(MESSAGES_PREFIX+"secondLevelCachePutCount", Convert.longToString(statistics.getSecondLevelCachePutCount()));
	}

	private void initSecondLevelCacheStatistics(Statistics statistics) {
		secondLevelCacheStatistics = new ArrayList<SecondLevelCacheStatisticsHibernate>();
		String[] secondLevelCacheRegionNames = statistics.getSecondLevelCacheRegionNames();
		Arrays.sort(secondLevelCacheRegionNames);
		for(String regionName : secondLevelCacheRegionNames) {
			secondLevelCacheStatistics.add(new SecondLevelCacheStatisticsHibernate(regionName, statistics.getSecondLevelCacheStatistics(regionName)));
		}
	}
	
	private Session getSession() {
		if(entityManager.getDelegate() instanceof Session) {
			return ((Session) entityManager.getDelegate());
		} else {
			throw new IllegalStateException();
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Map<String, String> getSessionStatistics() {
		return sessionStatistics;
	}

	@SuppressWarnings("rawtypes")
	public List getSessionEntityKeys() {
		return sessionEntityKeys;
	}

	@SuppressWarnings("rawtypes")
	public List getSessionCollectionKeys() {
		return sessionCollectionKeys;
	}

	public Map<String, String> getSessionFactoryStatistics() {
		return sessionFactoryStatistics;
	}

	public Map<String, String> getGlobalEntityStatistics() {
		return globalEntityStatistics;
	}
	
	public List<String> getEntityStatisticsColumns() {
		return EntityStatisticsHibernate.columns;
	}

	public List<EntityStatisticsHibernate> getEntityStatistics() {
		return entityStatistics;
	}

	public Map<String, String> getGlobalQueryStatistics() {
		return globalQueryStatistics;
	}
	
	public List<String> getQueryStatisticsColumns() {
		return QueryStatisticsHibernate.columns;
	}

	public List<QueryStatisticsHibernate> getQueryStatistics() {
		return queryStatistics;
	}

	public Map<String, String> getGlobalCollectionStatistics() {
		return globalCollectionStatistics;
	}
	
	public List<String> getCollectionStatisticsColumns() {
		return CollectionStatisticsHibernate.columns;
	}

	public List<CollectionStatisticsHibernate> getCollectionStatistics() {
		return collectionStatistics;
	}

	public Map<String, String> getGlobalSecondLevelCacheStatistics() {
		return globalSecondLevelCacheStatistics;
	}
	
	public List<String> getSecondLevelCacheStatisticsColumns() {
		return SecondLevelCacheStatisticsHibernate.columns;
	}

	public List<SecondLevelCacheStatisticsHibernate> getSecondLevelCacheStatistics() {
		return secondLevelCacheStatistics;
	}
}