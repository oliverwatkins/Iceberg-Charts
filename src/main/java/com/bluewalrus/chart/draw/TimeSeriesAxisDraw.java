package com.bluewalrus.chart.draw;

import java.awt.Graphics;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.TimeInterval.Type;

public abstract class TimeSeriesAxisDraw extends AxisDraw{

	
	public Date dateStart;
	public Date dateEnd;
	public TimeInterval timeInt1;
	public TimeInterval timeInt2; 
	public TimeInterval timeInt3;
	
	
	public TimeSeriesAxisDraw(Date dateStart, Date dateEnd, TimeInterval timeInt1,
			TimeInterval timeInt2, TimeInterval timeInt3) {
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		
		this.timeInt1 = timeInt1;
		this.timeInt2 = timeInt2;
		this.timeInt3 = timeInt3;
	}
	
	
	
	/**
	 * Draw the intervals ticks and labels for one particular interval:
	 * 
	 * 10--| 
	 *     | 
	 *     | 
	 * 20--| 
	 *     |
	 * 
	 * 
	 * @param interval
	 * @param g
	 * @param chart
	 * @param showLabel
	 */
	protected void drawIntervalTickAndLabels(TimeInterval interval, Graphics g,
			XYChart chart, boolean showLabel) {


		TimeInterval.Type t = interval.getInterval();
		
		long increment = getMsForType(t);

		int incrementNo = (int) ((dateEnd.getTime() - dateStart.getTime()) / increment); //shit

		double factor = this.getMultiplicationFactor(chart);

		System.out.println("increment = " + increment);
		System.out.println("factor = " + factor);
		
		
		double incrementInPixel = (double) (increment * factor);

		for (int i = 0; i < (incrementNo + 1); i++) {
			
			drawIntervalTick(interval, g, chart, i, incrementInPixel);

			if (showLabel)
				drawIntervalLabel(interval, g, chart, i, incrementInPixel);
		}
	}
	
	/**
	 * Draw the label next to the tick
	 * 
	 * @param interval
	 * @param g
	 * @param chart
	 * @param i
	 * @param incrementInPixel
	 */
	protected abstract void drawIntervalLabel(TimeInterval interval, Graphics g,
			XYChart chart, int i, double incrementInPixel);
	
	


	protected long getMsForType(Type i) {

		
		TimeUnit.DAYS.toMillis(1);     // 1 day to milliseconds.
		TimeUnit.MINUTES.toMillis(23); // 23 minutes to milliseconds.
		TimeUnit.HOURS.toMillis(4);    // 4 hours to milliseconds.
		TimeUnit.SECONDS.toMillis(96); // 96 seconds to milliseconds.
		
		
		switch (i) {
		case MONTH:
			return TimeUnit.DAYS.toMillis(30); //TODO 30, 31, 28, 29
		case YEAR:
			return TimeUnit.DAYS.toMillis(365); //TODO
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
	
	protected long getMsToNearestDataType(Date dateStart, Type type) {
		
		
		Calendar cal = Calendar.getInstance();
//		cal.setTime(dateStart);
//		cal.get(Calendar.MILLISECOND);
//		int y = cal.get(Calendar.YEAR);
//		int m = cal.get(Calendar.MONTH);
//		int dm = cal.get(Calendar.DAY_OF_MONTH);
//		int dy = cal.get(Calendar.DAY_OF_YEAR);
		


		
		if (type == Type.YEAR) {
			
			cal.setTime(dateStart);
			long dy = cal.get(Calendar.DAY_OF_YEAR);
			long hd = cal.get(Calendar.HOUR_OF_DAY);
			long m = cal.get(Calendar.MINUTE);
			long s = cal.get(Calendar.SECOND);
			long ms = cal.get(Calendar.MILLISECOND);
			
			s = s*1000;
			m = m * 60* 1000;
			hd = hd * 60 * 60 * 1000;
			dy = dy * 24 * 60 * 60 * 1000;
			
			
			
			long msToStart = ms + s +m +hd +dy; 
			
			//(s*1000) + (m*1000*60) + (hd*1000*60*60) + (dy*1000*60*60) 
			
//			getMsForType(i);
			
			
			System.out.println("m = " + dy);
			System.out.println("y = " + hd);
			System.out.println("dm = " + m);
			System.out.println("dy = " + s);
			System.out.println("dy = " + ms);
			
			return getMsForType(Type.YEAR) - msToStart;
		}
		
		
		
		
//		cal.clear(Calendar.MILLISECOND);
		
		
//		Date
		
		// TODO Auto-generated method stub
		return -999;
	}
	
	
	
	
	protected abstract double getMultiplicationFactor(XYChart chart);

	/**
	 * Draw the tick of the interval. Usually just a small line coming out
	 * perpendicular to the axis.
	 * 
	 * @param interval
	 * @param g
	 * @param chart
	 * @param i
	 * @param incrementInPixel
	 */

	protected abstract void drawIntervalTick(TimeInterval interval, Graphics g,
			XYChart chart, int i, double incrementInPixel);
	
	
	

}
