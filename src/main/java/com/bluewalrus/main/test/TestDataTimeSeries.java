package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.draw.LinearNumericalAxisDrawY;
import com.bluewalrus.chart.draw.TimeSeriesAxisDrawX;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.point.UIPointSquare;

public class TestDataTimeSeries {

	
	public static Chart getTestData_TimeSeries() throws ParseException {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm");
		
		Date startDate = df.parse("1984-01-20 05-33"); 
		Date endDate = df.parse("1991-01-20 05-33");
		
		
//		String time = "2009-07-20 05-33";                              
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm");
//		Date dt = df.parse(time);                                      
//		
//		time = "2009-07-20 05-33";                              
//		df = new SimpleDateFormat("yyyy-MM-dd hh-mm");
//		Date dt2 = df.parse(time);                                      
//
//		time = "2010-07-20 05-33";                                                          
//		df = new SimpleDateFormat("yyyy-MM-dd hh-mm");
//		Date dt3 = df.parse(time);                                      

		String time = "1990-01-20 05-33";                              
		Date dt4 = df.parse(time);                                      

		time = "1984-07-20 05-33";                                                
		Date dt5 = df.parse(time);                                      

		time = "1988-07-20 05-33";                              
		Date dt6 = df.parse(time);                                      
		
		
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
//		values.add(new DataPoint(dt, -30));
//		values.add(new DataPoint(dt2, -11));
//		values.add(new DataPoint(dt3, -14));
		values.add(new DataPoint(dt4, 5));
		values.add(new DataPoint(dt5, 8));
		values.add(new DataPoint(dt6, 14));

		XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.BLUE), new Line(Color.BLUE), "Something Blue");
		series.dataPoints = values;

		NumericalInterval t1 = new NumericalInterval(6, 50.0, new Line(Color.GRAY, false, 1));
		NumericalInterval t2 = new NumericalInterval(3, 10.0, new Line(Color.LIGHT_GRAY, true, 1));
		NumericalInterval t3 = new NumericalInterval(1, 5.0, null);

		YAxis yAxis = new YAxis(new LinearNumericalAxisDrawY(-90.0, 100.0, t1, t2, t3), "Y Axis");
		
		TimeInterval timeInt1 = new TimeInterval(7, TimeInterval.Type.YEAR, new Line(Color.GRAY, false, 1));
		TimeInterval timeInt2 = new TimeInterval(4, TimeInterval.Type.NONE, new Line(Color.GRAY, false, 1));
		TimeInterval timeInt3 = new TimeInterval(2, TimeInterval.Type.NONE, new Line(Color.GRAY, false, 1));
//		
		
		XAxis xAxis = new XAxis(new TimeSeriesAxisDrawX(startDate, endDate, timeInt1, timeInt2, timeInt3), "Time Series"); //timeInt2, timeInt3), "Time Series");

		xySeriesList.add(series);

		XYChart lineChart = new XYChart(xySeriesList, yAxis, xAxis);

		lineChart.setSize(1000, 500);
		lineChart.rightOffset = 200;

		lineChart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		lineChart.setTitle("Some Kind of XY Chart");

		return lineChart;
	}
}
