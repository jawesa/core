package com.jawesa.action.database.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jawesa.action.database.CollectionStatistics;
import com.jawesa.util.Convert;

@SuppressWarnings("serial")
public class CollectionStatisticsHibernate implements CollectionStatistics {
	public static final String MESSAGES_PREFIX = "cache.collectionStatistics.";
	
	public static final List<String> columns = Arrays.asList(
			MESSAGES_PREFIX + "role",
			MESSAGES_PREFIX + "fetchCount",
			MESSAGES_PREFIX + "loadCount",
			MESSAGES_PREFIX + "recreateCount",
			MESSAGES_PREFIX + "updateCount",
			MESSAGES_PREFIX + "removeCount");
	
	public CollectionStatisticsHibernate(String role, org.hibernate.stat.CollectionStatistics collectionStatistics) {
		statistics = new ArrayList<String>();
		statistics.add(role);
		statistics.add(Convert.longToString(collectionStatistics.getFetchCount()));
		statistics.add(Convert.longToString(collectionStatistics.getLoadCount()));
		statistics.add(Convert.longToString(collectionStatistics.getRecreateCount()));
		statistics.add(Convert.longToString(collectionStatistics.getUpdateCount()));
		statistics.add(Convert.longToString(collectionStatistics.getRemoveCount()));
	}
	
	private List<String> statistics;

	public List<String> getStatistics() {
		return statistics;
	}
}
