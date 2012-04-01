package com.jawesa.action.database;

import java.io.Serializable;
import java.util.List;

public interface CollectionStatistics extends Serializable {
	List<String> getStatistics();
}
