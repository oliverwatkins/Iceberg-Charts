package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bluewalrus.bar.GridFill;
import com.bluewalrus.bar.GridLine;
import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.point.UIPointSquare;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;
import com.bluewalrus.scaling.TimeSeriesAxisScalingX;

public class TestDataTimeSeries2 extends ChartTester {


	
	@Override
	public Chart getChart() throws ParseException {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		Date startDate = df.parse("2001-05-3 01-00-00"); 
		Date endDate = df.parse("2001-07-12 05-33-00");
		
		
		String time = "2001-03-01 00-33-00";                              
		Date dt4 = df.parse(time);                                      

		time = "2001-04-01 00-33-00";                                                
		Date dt5 = df.parse(time);                                      

		time = "2001-07-20 05-33-00";                              
		Date dt6 = df.parse(time);                                      
		
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(dt4, 5));
		values.add(new DataPoint(dt5, 8));
		values.add(new DataPoint(dt6, 14));

		XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.BLUE), new Line(Color.BLUE), "Something Blue");
		series.dataPoints = values;

		NumericalInterval t1 = new NumericalInterval(8, 10.0, new GridLine(Color.GRAY, false, 1));

		YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(-90.0, 100.0, t1, null, null), "Y Axis");
		
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("d");

//		TimeInterval timeInt2 = new TimeInterval(6, TimeInterval.Type.MONTH, new GridLine(Color.GRAY, false, 2), monthFormat);
//		TimeInterval timeInt3 = new TimeInterval(2, TimeInterval.Type.DAY, new GridLine(Color.GRAY, false, 1, new GridFill(Color.GRAY, Color.WHITE, true)), dayFormat);


		
		TimeInterval timeInt2 = new TimeInterval(6, TimeInterval.Type.MONTH, new GridLine(Color.GRAY, false, 2));
		
//				new GridFill(new Color(179, 209, 255), Color.WHITE, true)), monthFormat);
		TimeInterval timeInt3 = new TimeInterval(2, TimeInterval.Type.DAY, new GridLine(Color.GRAY, false, 1), dayFormat);

		
		XAxis xAxis = new XAxis(
				new TimeSeriesAxisScalingX(
						startDate, 
						endDate, 
						timeInt2, 
						timeInt3, 
						null), "Time Series"); 

		xySeriesList.add(series);

		XYChart lineChart = new XYChart(xySeriesList, yAxis, xAxis);

		lineChart.setSize(1000, 500);
		lineChart.rightOffset = 200;

		lineChart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		lineChart.setTitle("Some Kind of XY Chart");

		return lineChart;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
