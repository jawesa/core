package com.jawesa.action.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@SuppressWarnings("serial")
public class DatabaseStatisticsManager implements Serializable {
	@Inject
	private Instance<DatabaseStatistics> databaseStatisticsImplementations;
	
	public List<DatabaseStatistics> getDatabaseStatisticsImplementations() {
		List<DatabaseStatistics> result = new ArrayList<DatabaseStatistics>();
		Iterator<DatabaseStatistics> iterator = databaseStatisticsImplementations.iterator();
		
		while(iterator.hasNext()) {
			result.add(iterator.next());
		}
		
		return result;
	}
}
