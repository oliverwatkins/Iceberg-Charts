package com.bluewalrus.chart;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.bluewalrus.chart.axis.TimeInterval.Type;

public class DateUtils {

	public static long getMsToNearestDataType(Date dateStart, Type type) {
		
		Calendar calDateStart = Calendar.getInstance();

		if (type == Type.YEAR) {
			
			/**
			 * Given a date "dateStart", find the milliseconds to the next year (ie. YYYY-01-01-00:00)
			 */
			Calendar cal2 = Calendar.getInstance();
			
			calDateStart.setTime(dateStart);
			
			cal2.set(Calendar.YEAR, calDateStart.get(Calendar.YEAR) + 1);
			cal2.set(Calendar.DAY_OF_YEAR, 1);
			cal2.set(Calendar.HOUR_OF_DAY, 0);
			cal2.set(Calendar.MINUTE, 0);
			cal2.set(Calendar.SECOND, 0);
			cal2.set(Calendar.MILLISECOND, 0);
			
			return cal2.getTimeInMillis() - calDateStart.getTimeInMillis(); 
		}else if (type == Type.MONTH) {
			
			/**
			 * Given a date "dateStart", find the milliseconds to the next month (ie. YYYY-01-01-00:00)
			 */
			Calendar cal2 = Calendar.getInstance();
			
			calDateStart.setTime(dateStart);
			cal2.setTime(dateStart);
			cal2.set(Calendar.MONTH, calDateStart.get(Calendar.MONTH) + 1);
			cal2.set(Calendar.DAY_OF_MONTH, 0);
			cal2.set(Calendar.HOUR, 0);
			cal2.set(Calendar.MINUTE, 0);
			cal2.set(Calendar.SECOND, 0);
			cal2.set(Calendar.MILLISECOND, 0);
			
			
			return cal2.getTimeInMillis() - calDateStart.getTimeInMillis(); //getMsForType(Type.YEAR) - msToStart;
		}else if (type == Type.DAY){
			
			/**
			 * Given a date "dateStart", find the milliseconds to the next day (ie. YYYY-01-01-00:00)
			 */
			Calendar cal2 = Calendar.getInstance();
			
			calDateStart.setTime(dateStart);
			
			
			cal2.setTime(dateStart);
			
			cal2.set(Calendar.DAY_OF_YEAR, calDateStart.get(Calendar.DAY_OF_YEAR) + 1);
			cal2.set(Calendar.HOUR, 0);
			cal2.set(Calendar.MINUTE, 0);
			cal2.set(Calendar.SECOND, 0);
			cal2.set(Calendar.MILLISECOND, 0);
			
			return cal2.getTimeInMillis() - calDateStart.getTimeInMillis(); 
			
			
			
		}else if (type == Type.WEEK){
			
			Calendar cal2 = Calendar.getInstance();
			
			calDateStart.setTime(dateStart);
			
			cal2.setTime(dateStart);
			
			cal2.set(Calendar.WEEK_OF_YEAR, calDateStart.get(Calendar.WEEK_OF_YEAR) + 1);
			cal2.set(Calendar.HOUR, 0);
			cal2.set(Calendar.MINUTE, 0);
			cal2.set(Calendar.SECOND, 0);
			cal2.set(Calendar.MILLISECOND, 0);
			
			return cal2.getTimeInMillis() - calDateStart.getTimeInMillis(); 
			
		}else {
			throw new RuntimeException("No type yet " + type);
		}
	}
	
	public static Type getIntervalTime(DateRange drX) {

		long n = drX.howMany(DateUtils.getMsForType(Type.YEAR));
		
		if (n>2) {
			return Type.YEAR;
		} 
		
		n = drX.howMany(DateUtils.getMsForType(Type.WEEK));
		
		if (n>2) {
			return Type.WEEK;
		}
		
		n = drX.howMany(DateUtils.getMsForType(Type.DAY));
		
		if (n>2) {
			return Type.DAY;
		}
		
		n = drX.howMany(DateUtils.getMsForType(Type.HOUR));
		
		if (n>2) {
			return Type.HOUR;
		}

		n = drX.howMany(DateUtils.getMsForType(Type.MINUTE));
		
		if (n>2) {
			return Type.MINUTE;
		}
		
		throw new RuntimeException("Too small "+ drX + " n == " + n);
	}
	
	
	
	public static Type getNextInterval(Type interval1) {
		
		switch (interval1) {
		
		case YEAR:
			return Type.MONTH;
		case MONTH:
			return Type.DAY;
		case WEEK:
			return Type.DAY;
		case DAY:
			return Type.HOUR;
		case HOUR:
			return Type.MINUTE;
		case MINUTE:
			return Type.SECOND;
		default:
			break;
		}
		return null;
	}
	
	
	/**
	 * TODO this code isn't very correct. The method getMsForType is incorrect for year and month because they are both variable
	 * and this could lead to the incrementNo below being incorrectly calculated. incrementNo could be out by one.
	 */
	
	public static long getMsForType(Type i) {
		
		switch (i) {
		case MONTH:
			return TimeUnit.DAYS.toMillis(30); //TODO wrong!!! 30, 31, 28, 29
		case YEAR:
			return TimeUnit.DAYS.toMillis(365); //TODO wrong!!! leap years??
		case DAY:
			return TimeUnit.DAYS.toMillis(1); // 1 day to milliseconds.
		case HOUR:
			return TimeUnit.HOURS.toMillis(1); 
		case WEEK:
			return TimeUnit.DAYS.toMillis(7); 
		case MINUTE:
			return TimeUnit.MINUTES.toMillis(1);
		case NONE:
			break;
		default:
			break;
		}
		return -1l;
	}
	
	/**
	 * For a given date, get the number of days in the month of that date. (ie 28,29,20 or 31)
	 * 
	 * 
	 * @param date
	 * @return
	 */
	
	public static int getDaysInMonth(Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		boolean isLeapYear = false;
		if (cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
			isLeapYear = true;
		}

		int days = -1;
		int m2 = cal.get(Calendar.MONTH);
		switch (m2) {
		case 0:
			days = 31;
			break;
		case 1:
			if (isLeapYear)
				days = 29;
			else
				days = 28;
			break;
		case 2:
			days = 31;
			break;
		case 3:
			days = 30;
			break;
		case 4:
			days = 31;
			break;
		case 5:
			days = 30;
			break;
		case 6:
			days = 31;
			break;
		case 7:
			days = 30;
			break;
		case 8:
			days = 31;
			break;
		case 9:
			days = 31;
			break;
		case 10:
			days = 30;
			break;
		case 11:
			days = 31;
			break;

		default:
			break;
		}
		return days;
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
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(date));
		calendar.add(Calendar.YEAR, years);
		
		return calendar.getTimeInMillis();
	}
	
	
	public static long addMonth(long date, int months) {
		
		Calendar calendar = Calendar.getInstance();   
		calendar.setTime(new Date(date));
		calendar.add(Calendar.MONTH, months);

//		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + months); //will this work 13-->1 ?
		
		return calendar.getTimeInMillis();
	}
	
	public static long addWeek(long date, int weeks) {
		
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(new Date(date));
		cal.set(Calendar.WEEK_OF_YEAR, cal.get(Calendar.WEEK_OF_YEAR) + weeks); //will this work 13-->1 ?
		
		return cal.getTimeInMillis();
	}


	public static long addDay(long date, int days) {
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(new Date(date));
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + days); //will this work 31-->1 ?
		
		return cal.getTimeInMillis();
	}


	
}
