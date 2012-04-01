package com.jawesa.action.database;

import java.io.Serializable;
import java.util.List;

public interface QueryStatistics extends Serializable {
	List<String> getStatistics();
}
