package com.bluewalrus.chart.draw;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.bluewalrus.chart.axis.TimeInterval.Type;

public class DateUtils {

	
	public static long getMsToNearestDataType(Date dateStart, Type type) {
		
		Calendar cal = Calendar.getInstance();

		if (type == Type.YEAR) {
			
			/**
			 * Given a date "dateStart", find the milliseconds to the next year (ie. YYYY-01-01-00:00)
			 */
			Calendar cal2 = Calendar.getInstance();
			
			cal.setTime(dateStart);
			
			cal2.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
			cal2.set(Calendar.DAY_OF_YEAR, 1);
			cal2.set(Calendar.HOUR_OF_DAY, 0);
			cal2.set(Calendar.MINUTE, 0);
			cal2.set(Calendar.SECOND, 0);
			cal2.set(Calendar.MILLISECOND, 0);
			
			return cal2.getTimeInMillis() - cal.getTimeInMillis(); //getMsForType(Type.YEAR) - msToStart;
		}
		
		if (type == Type.MONTH) {
			
			/**
			 * Given a date "dateStart", find the milliseconds to the next month (ie. YYYY-01-01-00:00)
			 */
			Calendar cal2 = Calendar.getInstance();
			
			cal.setTime(dateStart);
			cal2.setTime(dateStart);
			cal2.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
			cal2.set(Calendar.DAY_OF_MONTH, 0);
			cal2.set(Calendar.HOUR, 0);
			cal2.set(Calendar.MINUTE, 0);
			cal2.set(Calendar.SECOND, 0);
			cal2.set(Calendar.MILLISECOND, 0);
			
			System.out.println("cal2.getTimeInMillis() " + new Date(cal2.getTimeInMillis()));
			System.out.println("cal1.getTimeInMillis() " + new Date(cal.getTimeInMillis()));
			
			return cal2.getTimeInMillis() - cal.getTimeInMillis(); //getMsForType(Type.YEAR) - msToStart;
		}
		
		
		return -999;
	}
	
	
	public static long getMsForType(Type i) {
		
		switch (i) {
		case MONTH:
			return TimeUnit.DAYS.toMillis(30); //TODO 30, 31, 28, 29
		case YEAR:
			return TimeUnit.DAYS.toMillis(365); //TODO wrong!!!
		case DAY:
			return TimeUnit.DAYS.toMillis(1);     // 1 day to milliseconds.
		case HOUR:
			return TimeUnit.HOURS.toMillis(1); // 23 minutes to milliseconds.
		case WEEK:
			return TimeUnit.DAYS.toMillis(7); // 23 minutes to milliseconds.
		case MINUTE:
			return TimeUnit.MINUTES.toMillis(1); // 23 minutes to milliseconds.
		case NONE:
			break;
		default:
			break;
		}
		return -1l;
	}
	
	
	/**
	 * Given a date ( which should be in the for 01.01.00:00 19XX ) add years such that
	 * the date returned is 01.01.00:00 (19XX+years) 
	 * 
	 * 
	 * @param date
	 * @param incrementNumber
	 * @return
	 */
	public static long addYear(long date, int years) {
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(new Date(date));
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + years);
		
		return cal.getTimeInMillis();
	}
	
	
	public static long addMonth(long date, int incrementNumber) {
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(new Date(date));
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1); //will this work 13-->1 ?
		
		return cal.getTimeInMillis();
	}
	
}
