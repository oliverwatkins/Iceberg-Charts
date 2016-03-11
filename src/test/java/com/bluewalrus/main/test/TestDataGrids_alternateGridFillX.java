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
import com.bluewalrus.chart.draw.LinearNumericalAxisDrawX;
import com.bluewalrus.chart.draw.LinearNumericalAxisDrawY;
import com.bluewalrus.chart.draw.TimeSeriesAxisDrawX;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.point.UIPointSquare;

public class TestDataGrids_alternateGridFillX extends ChartTester {

	
	@Override
	public Chart getChart() throws ParseException {

		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(1, 5));
		values.add(new DataPoint(2, 8));
		values.add(new DataPoint(3, 14));

		XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.BLUE), new Line(Color.BLUE), "Something Blue");
		series.dataPoints = values;

		NumericalInterval yInterval = new NumericalInterval(8, 10.0, new GridLine(Color.GRAY, false, 1));

		
		YAxis yAxis = new YAxis(new LinearNumericalAxisDrawY(-90.0, 100.0, yInterval, null, null), "Y Axis");

		
		NumericalInterval x1 = new NumericalInterval(2, 5.0, 
				new GridLine(Color.GRAY, false, 0,
				new GridFill(
						new Color(179, 209, 255), 
						new Color(172, 109, 215), false)
				)
		);
		
		NumericalInterval x2 = new NumericalInterval(6, 20.0, new GridLine(Color.GRAY, false, 0));

		XAxis xAxis = new XAxis(new LinearNumericalAxisDrawX(3, 97, x1, x2, null), "X Axis"); 
		
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		xySeriesList.add(series);

		XYChart lineChart = new XYChart(series, "Alternate Grid Fill", yAxis, xAxis);

		lineChart.setSize(1000, 500);
		lineChart.rightOffset = 200;

		return lineChart;
	}
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataGrids_alternateGridFillX();
		t.testChart(t.getChart());
	}
	
	
	
	
	
	
	
	
}

