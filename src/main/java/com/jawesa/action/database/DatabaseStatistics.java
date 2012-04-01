package com.jawesa.action.database;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface DatabaseStatistics extends Serializable {
	String getImplementationName();
	
	Map<String, String> getSessionStatistics();
	
	@SuppressWarnings("rawtypes")
	List getSessionEntityKeys();
	
	@SuppressWarnings("rawtypes")
	List getSessionCollectionKeys();

	Map<String, String> getSessionFactoryStatistics();
	
	Map<String, String> getGlobalEntityStatistics();
	List<String> getEntityStatisticsColumns();
	List<? extends EntityStatistics> getEntityStatistics();

	Map<String, String> getGlobalQueryStatistics();
	List<String> getQueryStatisticsColumns();
	List<? extends QueryStatistics> getQueryStatistics();
	
	Map<String, String> getGlobalCollectionStatistics();
	List<String> getCollectionStatisticsColumns();
	List<? extends CollectionStatistics> getCollectionStatistics();

	Map<String, String> getGlobalSecondLevelCacheStatistics();
	List<String> getSecondLevelCacheStatisticsColumns();
	List<? extends SecondLevelCacheStatistics> getSecondLevelCacheStatistics();
}