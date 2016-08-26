package com.bluewalrus.main.test.gridlinefill;

import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.IntervalStyling;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.GridFill;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.main.test.ChartTester;

public class TestDataGrids_3_alternateGridFillX extends ChartTester {

	@Override
	public Chart getChart() throws ParseException {
		
		XYDataSeries series = new XYDataSeries(null, 
				new Line(Color.BLUE, false, 3), 
				"Blue Series");
	
	
		series.dataPoints = getSeries1();
		
		XYDataSeries series2 = new XYDataSeries(null, 
				new Line(Color.RED, false, 3), 
				"Red Series");
		series2.dataPoints = getSeries2();

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		xySeriesList.add(series);
		xySeriesList.add(series2);


		IntervalStyling stylingX = new IntervalStyling(2, 
				new Line(Color.GRAY, false, 0),
				new GridFill(
						new Color(179, 209, 255), 
						new Color(172, 109, 215), false));
		
		IntervalStyling stylingY = new IntervalStyling(8,
				new Line(Color.GRAY, false, 1), 
				null);
		
		XYChart chart = new XYChart(xySeriesList, "Alternate Grid Fill", null, 
				stylingX, 
				null, 
				null, 
				stylingY, 
				null, "X Axis", "Y Axis");
		
		chart.setSize(1000, 500);
		chart.rightOffset = 200;

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "Grids: alternate Grid Fill X";
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
		ChartTester t = new TestDataGrids_3_alternateGridFillX();
		t.testChart(t.getChart());
	}

}
