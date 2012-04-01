package com.jawesa.action.database;

import java.io.Serializable;
import java.util.List;

public interface EntityStatistics extends Serializable {
	List<String> getStatistics();
}
