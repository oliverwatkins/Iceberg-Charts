package com.frontangle.ichart.main.test.timeseries;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.axis.TimeInterval;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.GridFill;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointSquare;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;
import com.frontangle.ichart.scaling.TimeSeriesAxisScaling;

public class TestDataTimeSeries_YearMonth extends ChartTester {

	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	
	@Override
	public Chart getChart() throws ParseException {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		Date startDate = df.parse("1991-05-15 01-00-00"); 
		Date endDate = df.parse("1997-07-12 05-33-00");
		
		
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

		NumericalInterval t1 = new NumericalInterval(8, 10.0, new Line(Color.GRAY, false, 1));

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(-90.0, 100.0, t1, null, null), "Y Axis");
		
		SimpleDateFormat monthFormat = new SimpleDateFormat("M");
		SimpleDateFormat yearFormat = new SimpleDateFormat("YY");
		
		TimeInterval timeInt2 = new TimeInterval(6, TimeInterval.Type.YEAR, new Line(Color.GRAY, false, 2), yearFormat);
		
		TimeInterval timeInt3 = new TimeInterval(2, TimeInterval.Type.MONTH, new Line(Color.GRAY, false, 1), monthFormat);

		
		timeInt2.styling.intervalFont =  new Font("Blackadder ITC", Font.BOLD, 16);
		timeInt3.styling.intervalFont =  new Font("Blackadder ITC", Font.PLAIN, 8);
		
		timeInt2.styling.graphFill =  new GridFill(Color.WHITE, new Color(224,235,235), false);
		
		XAxis xAxis = new XAxis(
				new TimeSeriesAxisScaling(
						startDate, 
						endDate, 
						timeInt2, 
						timeInt3, 
						null), "Time Series"); 

		xySeriesList.add(series);

		XYChart chart = new XYChart("","","",xySeriesList, yAxis, xAxis,false);
		chart.setTitle("Year Month (May 1991, July 2001)");

		return chart;
	}
	
	@Override
	public String getNiceTitle() {
		return "Time Series: Year Month";
	}
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataTimeSeries_YearMonth();
		t.testChart(t.getChart());
	}	
}
