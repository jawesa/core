package com.jawesa.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class Format {
	public static final String dateToDate(Date date, String pattern) {
		DateFormat formatter =  new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	
	public static final String dateToDate(Date date) {
		return Format.dateToDate(date, Constants.PATTERN_DATE);
	}
	
	public static final String dateToDate(Date date, ResourceBundle messages) {
		return Format.dateToDate(date, messages.getString("format.date"));
	}
	
	public static final String calendarToDate(Calendar calendar) {
		return Format.dateToDate(calendar.getTime());
	}
	
	public static final String calendarToDate(Calendar calendar, ResourceBundle messages) {
		return Format.dateToDate(calendar.getTime(), messages);
	}
	
	public static final String dateToDatetime(Date date, String pattern) {
		DateFormat formatter =  new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	
	public static final String dateToDatetime(Date date) {
		return Format.dateToDatetime(date, Constants.PATTERN_DATETIME);
	}
	
	public static final String dateToDatetime(Date date, ResourceBundle messages) {
		return Format.dateToDatetime(date, messages.getString("format.datetime"));
	}
	
	public static final String calendarToDatetime(Calendar calendar) {
		return Format.dateToDatetime(calendar.getTime());
	}
	
	public static final String calendarToDatetime(Calendar calendar, ResourceBundle messages) {
		return Format.dateToDatetime(calendar.getTime(), messages);
	}
	
	public static final String doubleToPercent(double value) {
		return (Math.round(Constants.CENT * value)+"%");
	}
}
