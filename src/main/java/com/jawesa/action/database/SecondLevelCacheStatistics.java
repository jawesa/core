package com.jawesa.action.database;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface SecondLevelCacheStatistics extends Serializable {
	List<String> getStatistics();
	
	@SuppressWarnings("rawtypes")
	Map getEntries();
}
