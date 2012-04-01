package com.jawesa.action.database.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jawesa.action.database.EntityStatistics;
import com.jawesa.util.Convert;

@SuppressWarnings("serial")
public class EntityStatisticsHibernate implements EntityStatistics {
	public static final String MESSAGES_PREFIX = "cache.entityStatistics.";
	
	public static final List<String> columns = Arrays.asList(
			MESSAGES_PREFIX + "entityName",
			MESSAGES_PREFIX + "fetchCount",
			MESSAGES_PREFIX + "loadCount",
			MESSAGES_PREFIX + "insertCount",
			MESSAGES_PREFIX + "updateCount",
			MESSAGES_PREFIX + "deleteCount",
			MESSAGES_PREFIX + "optimisticFailureCount");
	
	public EntityStatisticsHibernate(String entityName, org.hibernate.stat.EntityStatistics entityStatistics) {
		statistics = new ArrayList<String>();
		statistics.add(entityName);
		statistics.add(Convert.longToString(entityStatistics.getFetchCount()));
		statistics.add(Convert.longToString(entityStatistics.getLoadCount()));
		statistics.add(Convert.longToString(entityStatistics.getInsertCount()));
		statistics.add(Convert.longToString(entityStatistics.getUpdateCount()));
		statistics.add(Convert.longToString(entityStatistics.getDeleteCount()));
		statistics.add(Convert.longToString(entityStatistics.getOptimisticFailureCount()));
	}
	
	private List<String> statistics;

	public List<String> getStatistics() {
		return statistics;
	}
}
