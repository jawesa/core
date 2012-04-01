package com.jawesa.action.database.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jawesa.action.database.QueryStatistics;
import com.jawesa.util.Convert;

@SuppressWarnings("serial")
public class QueryStatisticsHibernate implements QueryStatistics {
	public static final String MESSAGES_PREFIX = "cache.queryStatistics.";
	
	public static final List<String> columns = Arrays.asList(
			MESSAGES_PREFIX + "queryString",
			MESSAGES_PREFIX + "cacheHitCount",
			MESSAGES_PREFIX + "cacheMissCount",
			MESSAGES_PREFIX + "cachePutCount",
			MESSAGES_PREFIX + "executionCount",
			MESSAGES_PREFIX + "executionAvgTime",
			MESSAGES_PREFIX + "executionMaxTime",
			MESSAGES_PREFIX + "executionMinTime",
			MESSAGES_PREFIX + "executionRowCount");
	
	private List<String> statistics;
	
	public QueryStatisticsHibernate(String queryString, org.hibernate.stat.QueryStatistics queryStatistics) {
		statistics = new ArrayList<String>();
		statistics.add(queryString);
		statistics.add(Convert.longToString(queryStatistics.getCacheHitCount()));
		statistics.add(Convert.longToString(queryStatistics.getCacheMissCount()));
		statistics.add(Convert.longToString(queryStatistics.getCachePutCount()));
		statistics.add(Convert.longToString(queryStatistics.getExecutionCount()));
		statistics.add(Convert.longToString(queryStatistics.getExecutionAvgTime()));
		statistics.add(Convert.longToString(queryStatistics.getExecutionMaxTime()));
		statistics.add(Convert.longToString(queryStatistics.getExecutionMinTime()));
		statistics.add(Convert.longToString(queryStatistics.getExecutionRowCount()));
	}

	public List<String> getStatistics() {
		return statistics;
	}
}
