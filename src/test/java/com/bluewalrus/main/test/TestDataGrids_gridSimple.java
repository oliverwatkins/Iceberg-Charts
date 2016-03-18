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
import com.bluewalrus.chart.axis.IntervalStyling;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.draw.LinearNumericalAxisDrawX;
import com.bluewalrus.chart.draw.LinearNumericalAxisDrawY;
import com.bluewalrus.chart.draw.TimeSeriesAxisDrawX;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.point.UIPointSquare;

/**
 * introduction to grids
 * 
 * @author Oliver Watkins
 */
public class TestDataGrids_gridSimple extends ChartTester {

	
	public Chart getChart() {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 30));
		values.add(new DataPoint(10, 11));
		values.add(new DataPoint(15, 14));
		values.add(new DataPoint(20, 5));
		values.add(new DataPoint(25, 8));

		ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();
		values2.add(new DataPoint(5, 2));
		values2.add(new DataPoint(10, 33));
		values2.add(new DataPoint(15, 6));
		values2.add(new DataPoint(20, 14));
		values2.add(new DataPoint(25, 17));

		ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
		values3.add(new DataPoint(5, 130));
		values3.add(new DataPoint(10, 74));
		values3.add(new DataPoint(15, 67));
		values3.add(new DataPoint(20, 22));
		values3.add(new DataPoint(25, 68));
		
		ArrayList<DataPoint> values4 = new ArrayList<DataPoint>();
		values4.add(new DataPoint(5, 90));
		values4.add(new DataPoint(10, 65));
		values4.add(new DataPoint(15, 80));
		values4.add(new DataPoint(20, 83));
		values4.add(new DataPoint(23, 90));

		XYDataSeries series = new XYDataSeries(values, "First");
		XYDataSeries series2 = new XYDataSeries(values2, "Second");
		XYDataSeries series3 = new XYDataSeries(values3, "Third");
		XYDataSeries series4 = new XYDataSeries(values4, "Fourth");

		
		XYChart lineChart = new XYChart("Many Series Example", 
				"My X Axis", 
				"My Y Axis", 
				new IntervalStyling(4, new GridLine(Color.BLUE, false, 3), null),
				new IntervalStyling(1, new GridLine(Color.RED, true, 2), null),
				new IntervalStyling(1, new GridLine(Color.RED, true, 1), null),
				series, 
				series2, 
				series3, 
				series4); 
		
		return lineChart;
	}



	private ArrayList<DataPoint> getSeries2() {
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 91));
		values.add(new DataPoint(7, 92));
		values.add(new DataPoint(8, 93));
		values.add(new DataPoint(10, 92));
		values.add(new DataPoint(15, 90));
		values.add(new DataPoint(21, 91));
		values.add(new DataPoint(24, 92));
		values.add(new DataPoint(26, 83));
		values.add(new DataPoint(29, 84));
		values.add(new DataPoint(30, 63));
		values.add(new DataPoint(32, 67));
		values.add(new DataPoint(33, 62));
		values.add(new DataPoint(34, 51));
		values.add(new DataPoint(37, 47));
		values.add(new DataPoint(43, 41));
		values.add(new DataPoint(46, 48));
		values.add(new DataPoint(51, 32));
		values.add(new DataPoint(53, 36));
		values.add(new DataPoint(54, 31));
		values.add(new DataPoint(57, 32));
		values.add(new DataPoint(58, 25));
		values.add(new DataPoint(62, 12));
		values.add(new DataPoint(66, 2));
		values.add(new DataPoint(69, -1));
		values.add(new DataPoint(72, -5));
		values.add(new DataPoint(74, -38));
		values.add(new DataPoint(76, -24));
		values.add(new DataPoint(77, -21));
		values.add(new DataPoint(81, -12));
		values.add(new DataPoint(82, -13));
		values.add(new DataPoint(88, -11));
		values.add(new DataPoint(89, -23));
		values.add(new DataPoint(91, -21));
		values.add(new DataPoint(92, -24));
		values.add(new DataPoint(95, -26));
		return values;
	}






	private ArrayList<DataPoint> getSeries1() {
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 94));
		values.add(new DataPoint(7, 92));
		values.add(new DataPoint(8, 94));
		values.add(new DataPoint(10, 91));
		values.add(new DataPoint(15, 90));
		values.add(new DataPoint(21, 91));
		values.add(new DataPoint(24, 92));
		values.add(new DataPoint(26, 88));
		values.add(new DataPoint(29, 83));
		values.add(new DataPoint(30, 81));
		values.add(new DataPoint(32, 77));
		values.add(new DataPoint(33, 68));
		values.add(new DataPoint(34, 61));
		values.add(new DataPoint(37, 57));
		values.add(new DataPoint(43, 61));
		values.add(new DataPoint(46, 68));
		values.add(new DataPoint(51, 52));
		values.add(new DataPoint(53, 46));
		values.add(new DataPoint(54, 41));
		values.add(new DataPoint(57, 42));
		values.add(new DataPoint(58, 45));
		values.add(new DataPoint(62, 52));
		values.add(new DataPoint(66, 32));
		values.add(new DataPoint(69, 34));
		values.add(new DataPoint(72, 25));
		values.add(new DataPoint(74, 38));
		values.add(new DataPoint(76, 24));
		values.add(new DataPoint(77, 21));
		values.add(new DataPoint(81, 12));
		values.add(new DataPoint(82, 13));
		values.add(new DataPoint(88, 11));
		values.add(new DataPoint(89, 23));
		values.add(new DataPoint(91, 21));
		values.add(new DataPoint(92, 3));
		values.add(new DataPoint(95, 4));
		return values;
	}
	
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataGrids_gridSimple();
		t.testChart(t.getChart());
	}
	
}

