package com.jawesa.action.database.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jawesa.action.database.SecondLevelCacheStatistics;
import com.jawesa.util.Convert;

@SuppressWarnings("serial")
public class SecondLevelCacheStatisticsHibernate implements SecondLevelCacheStatistics {
	public static final String MESSAGES_PREFIX = "cache.secondLevelCacheStatistics.";
	
	public static final List<String> columns = Arrays.asList(
			MESSAGES_PREFIX + "regionName",
			MESSAGES_PREFIX + "elementCountInMemory",
			MESSAGES_PREFIX + "elementCountOnDisk",
			MESSAGES_PREFIX + "hitCount",
			MESSAGES_PREFIX + "missCount",
			MESSAGES_PREFIX + "putCount",
			MESSAGES_PREFIX + "sizeInMemory");
	
	private List<String> statistics;
	
	@SuppressWarnings("rawtypes")
	private Map entries;
	
	@SuppressWarnings("rawtypes")
	public SecondLevelCacheStatisticsHibernate(String regionName, org.hibernate.stat.SecondLevelCacheStatistics secondLevelCacheStatistics) {
		statistics = new ArrayList<String>();
		statistics.add(regionName);
		statistics.add(Convert.longToString(secondLevelCacheStatistics.getElementCountInMemory()));
		statistics.add(Convert.longToString(secondLevelCacheStatistics.getElementCountOnDisk()));
		statistics.add(Convert.longToString(secondLevelCacheStatistics.getHitCount()));
		statistics.add(Convert.longToString(secondLevelCacheStatistics.getMissCount()));
		statistics.add(Convert.longToString(secondLevelCacheStatistics.getPutCount()));
		statistics.add(Convert.longToString(secondLevelCacheStatistics.getSizeInMemory()));
		
		entries = secondLevelCacheStatistics.getEntries();
		
		if(entries == null) {
			entries = new HashMap();
		}
	}

	@Override
	public List<String> getStatistics() {
		return statistics;
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public Map getEntries() {
		return entries;
	}
}
