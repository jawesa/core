package com.jawesa.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Named;

@Named
public class JsfUtil {
	
	private List mapToList;
	
	public static <E> List<E> asList(Set<E> mySet) {
		return new ArrayList<E>(mySet);
	}
	
	public static <E> List<E> getAsList(Set<E> mySet) {
		return new ArrayList<E>(mySet);
	}
	
	public <K,V> List<Map.Entry<K, V>> mapToList(Map<K, V> map) {
		return (new ArrayList<Map.Entry<K, V>>(map.entrySet()));
	}
	
	public List getMapToList() {
		return mapToList;
	}

	public void setMapToList(List mapToList) {
		this.mapToList = mapToList;
	}
}
