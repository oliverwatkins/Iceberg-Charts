package com.bluewalrus.main.test.xy;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointCircle;
import com.bluewalrus.chart.draw.point.UIPointSquare;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;

public class TestDataXY_LineExamples extends ChartTester {

	public XYChart getChart() {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> values1 = new ArrayList<DataPoint>();
		values1.add(new DataPoint(1, 1));
		values1.add(new DataPoint(4, 9));

		ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();
		values2.add(new DataPoint(1, 1));
		values2.add(new DataPoint(5, 4));

		ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
		values3.add(new DataPoint(1, 1));
		values3.add(new DataPoint(7.5, 6));

		ArrayList<DataPoint> values4 = new ArrayList<DataPoint>();
		values4.add(new DataPoint(1, 1));
		values4.add(new DataPoint(9, 9));

		ArrayList<DataPoint> values5 = new ArrayList<DataPoint>();
		values5.add(new DataPoint(1, 1));
		values5.add(new DataPoint(8, 7));

		XYDataSeries<DataPoint> series1 = new XYDataSeries<DataPoint>(
				new UIPointCircle(Color.ORANGE, 15),
				new Line(Color.RED, true, 11), "1");
		XYDataSeries<DataPoint> series2 = new XYDataSeries<DataPoint>(
				new UIPointSquare(Color.PINK), new Line(Color.PINK, true), "2");
		XYDataSeries<DataPoint> series3 = new XYDataSeries<DataPoint>(
				new UIPointCircle(Color.BLACK, 23),
				new Line(Color.BLACK, true, 1), "3");
		XYDataSeries<DataPoint> series4 = new XYDataSeries<DataPoint>(
				new UIPointCircle(Color.GRAY), new Line(Color.GRAY), "4");
		XYDataSeries<DataPoint> series5 = new XYDataSeries<DataPoint>(
				new UIPointSquare(Color.CYAN), new Line(Color.CYAN), "5");

		series1.dataPoints = values1;
		series2.dataPoints = values2;
		series3.dataPoints = values3;
		series4.dataPoints = values4;
		series5.dataPoints = values5;

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(-5.0, 10.0,
				new NumericalInterval(4, 1.0), new NumericalInterval(2, 0.5),
				null), "y");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(0.0, 10.0, 1.0,
				null, null), "x");

		xySeriesList.add(series1);
		xySeriesList.add(series2);
		xySeriesList.add(series3);
		xySeriesList.add(series4);
		xySeriesList.add(series5);

		XYChart chart = new XYChart(xySeriesList, yAxis, xAxis);

		chart.setSize(1000, 500);
		chart.rightOffset = 200;

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("xy");

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "XY: Line Examples";
	}

}
