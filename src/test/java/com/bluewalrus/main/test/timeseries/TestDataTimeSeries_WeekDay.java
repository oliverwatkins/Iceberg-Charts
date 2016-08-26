package com.bluewalrus.main.test.timeseries;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.GridFill;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointSquare;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;
import com.bluewalrus.scaling.TimeSeriesAxisScaling;

public class TestDataTimeSeries_WeekDay  extends ChartTester {
	
	@Override
	public Chart getChart() throws ParseException {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		//about 3.5 weeks
		Date startDate = df.parse("2001-07-1 01-00-00"); 
		Date endDate = df.parse("2001-07-27 05-33-00");
		
		
		String time = "2001-07-04 00-33-00";                              
		Date dt4 = df.parse(time);                                      

		time = "2001-07-05 00-33-00";                                                
		Date dt5 = df.parse(time);                                      

		time = "2001-07-07 05-33-00";                              
		Date dt6 = df.parse(time);                                      
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(dt4, 5));
		values.add(new DataPoint(dt5, 8));
		values.add(new DataPoint(dt6, 14));

		XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.BLUE), new Line(Color.BLUE), "Something Blue");
		series.dataPoints = values;

		NumericalInterval t1 = new NumericalInterval(8, 10.0, new Line(Color.LIGHT_GRAY));

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(-90.0, 100.0, t1, null, null), "Y Axis");
		
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("d");
		
		TimeInterval timeInt2 = new TimeInterval(6, TimeInterval.Type.WEEK, new Line(Color.GRAY, false, 2), new SimpleDateFormat("yyyy-MM-dd"));
		
		TimeInterval timeInt3 = new TimeInterval(2, TimeInterval.Type.DAY, new Line(Color.LIGHT_GRAY, false, 1), dayFormat);

		XAxis xAxis = new XAxis(
				new TimeSeriesAxisScaling(
						startDate, 
						endDate, 
						timeInt2, 
						timeInt3, 
						null), "Time Series"); 

		xySeriesList.add(series);
		
		timeInt2.styling.graphFill =  new GridFill(Color.WHITE, new Color(224,235,235), false);

		XYChart chart = new XYChart(xySeriesList, yAxis, xAxis);
		chart.setTitle("Week Day Time Series");

		return chart;
	}
	
	
	@Override
	public String getNiceTitle() {
		return "Time Series: Week Day";
	}
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataTimeSeries_WeekDay();
		t.testChart(t.getChart());
	}
}
