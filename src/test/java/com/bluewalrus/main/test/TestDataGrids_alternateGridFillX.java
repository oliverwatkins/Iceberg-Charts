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

public class TestDataGrids_alternateGridFillX extends ChartTester {

	
	@Override
	public Chart getChart() throws ParseException {

		
		ArrayList<DataPoint> values = getSeries1();
		ArrayList<DataPoint> values2 = getSeries2();

		XYDataSeries series = new XYDataSeries(null, new Line(Color.BLUE, false, 3), "Blue");
		series.dataPoints = values;
		
		XYDataSeries series2 = new XYDataSeries(null, new Line(Color.RED, false, 3), "Red");
		series2.dataPoints = values2;

		NumericalInterval yInterval = new NumericalInterval(8, 10.0, new GridLine(Color.GRAY, false, 1));
		
		YAxis yAxis = new YAxis(new LinearNumericalAxisDrawY(-90.0, 100.0, yInterval, null, null), "Y Axis");
		
		NumericalInterval x1 = new NumericalInterval(2, 5.0, new GridLine(Color.GRAY, false, 0));
		
		NumericalInterval x2 = new NumericalInterval(6, 20.0, new GridLine(Color.GRAY, false, 0));

		XAxis xAxis = new XAxis(new LinearNumericalAxisDrawX(3, 97, x1, x2, null), "X Axis"); 
		
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		xySeriesList.add(series);
		xySeriesList.add(series2);

		IntervalStyling stylingX = new IntervalStyling(2, 
				new GridLine(Color.GRAY, false, 0),
				new GridFill(
						new Color(179, 209, 255), 
						new Color(172, 109, 215), false));
		
		IntervalStyling stylingY = new IntervalStyling(8,
				new GridLine(Color.GRAY, false, 1), 
				null);
		
//		XYChart lineChart = new XYChart(xySeriesList, "Alternate Grid Fill", stylingX, stylingY);
		XYChart lineChart = new XYChart(xySeriesList, "Alternate Grid Fill", null, 
				stylingX, 
				null, 
				null, 
				stylingY, 
				null);
		
		lineChart.setSize(1000, 500);
		lineChart.rightOffset = 200;

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
		ChartTester t = new TestDataGrids_alternateGridFillX();
		t.testChart(t.getChart());
	}
	
}

