package com.common;

import java.util.Calendar;
import java.util.Date;

public final class Utils {
	public static Date getNow() {
		return new Date();
	}
	
	public static java.sql.Date getSqlDateNow() {
		java.sql.Date sqlDate = new java.sql.Date(getNow().getTime());
		return sqlDate;
	}
	
	public static java.sql.Date getMaxSqlDateNow() {
		Calendar c = Calendar.getInstance();
		c.set(2099, 11, 30, 0, 0);  
		java.sql.Date sqlDate = new java.sql.Date(c.getTimeInMillis());
		return sqlDate;
	} 
}
