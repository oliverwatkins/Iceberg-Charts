package com.bluewalrus.main.test.timeseries;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.IntervalStyling;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.GridFill;
import com.bluewalrus.chart.draw.GridLine;
import com.bluewalrus.chart.draw.point.UIPointSquare;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.TimeSeriesAxisScaling;

public class TestDataTimeSeries_MonthDay extends ChartTester {

	

	@Override
	public Chart getChart() throws ParseException {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		Date startDate = df.parse("2001-01-03 10-00-00"); //3rd jan
		Date endDate = df.parse("2001-03-12 05-33-00"); //12th march
//		Date endDate = df.parse("2001-01-7 05-33-00"); //12th march
		
		
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

		XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.BLUE), new GridLine(Color.BLUE), "Something Blue");
		series.dataPoints = values;

		SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("d");

		
		TimeInterval timeInt1 = new TimeInterval(2, TimeInterval.Type.MONTH, new GridLine(Color.GRAY, false, 2), monthFormat);
		timeInt1.styling = new IntervalStyling(10, new GridLine(Color.BLACK, false, 2), new GridFill(Color.LIGHT_GRAY, Color.WHITE, false));
		
		TimeInterval timeInt2 = new TimeInterval(2, TimeInterval.Type.DAY, new GridLine(Color.GRAY, false, 1), dayFormat);
		timeInt2.styling = new IntervalStyling(2, new GridLine(Color.GRAY, false, 1), null);

//        barChart.yAxis.font = new Font("Blackadder ITC", Font.PLAIN, 20);
		timeInt2.styling.intervalFont = new Font("Blackadder ITC", Font.PLAIN, 20);

		
		XAxis xAxis = new XAxis(
				new TimeSeriesAxisScaling(
						startDate, 
						endDate, 
						timeInt1, 
						timeInt2, 
						null), "Time Series 1"); 

		XYChart lineChart = new XYChart(values, "Time Series 1", "X", "Y"); //yAxis, xAxis);
		lineChart.xAxis = xAxis;
		lineChart.setTitle("Month Day Time Series (3rd Jan to 12th March)");
		
		
		timeInt1.styling.intervalFont =  new Font("Blackadder ITC", Font.BOLD, 16);
		timeInt2.styling.intervalFont =  new Font("Blackadder ITC", Font.PLAIN, 12);
		

		return lineChart;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataTimeSeries_MonthDay();
		t.testChart(t.getChart());
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
