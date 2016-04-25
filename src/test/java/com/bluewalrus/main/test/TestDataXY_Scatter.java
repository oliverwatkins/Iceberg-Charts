package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointBoxPlot;
import com.bluewalrus.chart.draw.GridLine;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointBoxPlot;
import com.bluewalrus.chart.draw.point.UIPointCircle;
import com.bluewalrus.chart.draw.point.UIPointSquare;
import com.bluewalrus.chart.draw.point.UIPointTriangle;
import com.bluewalrus.scaling.LinearNumericalAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;

public class TestDataXY_Scatter extends ChartTester {


	public Chart getChart() {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();

		values.add(new DataPoint(10, 73));
		values.add(new DataPoint(15, 12));
		values.add(new DataPoint(18, 61));
		values.add(new DataPoint(45, 16));
		values.add(new DataPoint(16, 13));
		values.add(new DataPoint(35, 33));
		values.add(new DataPoint(48, 67));
		values.add(new DataPoint(55, 10));
		values.add(new DataPoint(20, 12));
		values.add(new DataPoint(35, 35));
		values.add(new DataPoint(48, 63));
		values.add(new DataPoint(46, 12));

		values.add(new DataPoint(47, 23));
		values.add(new DataPoint(46, 19));
		values.add(new DataPoint(48, 15));
		values.add(new DataPoint(32, 18));
		values.add(new DataPoint(38, 17));
		values.add(new DataPoint(39, 14));
		values.add(new DataPoint(49, 19));
		values.add(new DataPoint(41, 12));

		values2.add(new DataPoint(45, 21));
		values2.add(new DataPoint(44, 14));
		values2.add(new DataPoint(42, 17));
		values2.add(new DataPoint(36, 11));
		values2.add(new DataPoint(33, 21));
		values2.add(new DataPoint(32, 17));
		values2.add(new DataPoint(46, 14));
		values2.add(new DataPoint(54, 21));

		XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.RED, 8),
				null, "1994");

		series.dataPoints = values;

		XYDataSeries series2 = new XYDataSeries(
				new UIPointCircle(Color.ORANGE, 8), null, "1995xxxx");

		series2.dataPoints = values2;

//		NumericalAxis yAxis = new NumericalAxis(3.0, 100.0, 50.0, 0.0, 0.0, "Length (inches) ");
//		NumericalAxis xAxis = new NumericalAxis(3.0, 100.0, 50.0, 10.0, 0.0, "Weight (kg)");
		
		YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(3.0, 100.0, 50.0, 0.0, 0.0), "Length (inches) ");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScalingX(3.0, 100.0, 50.0, 10.0, 0.0), "Weight (kg)");


//		new LinearNumericalAxisDrawY(minValue, maxValue, primaryIncrements, secondaryIncrements, tertiaryIncrements)
		
		xySeriesList.add(series);
		xySeriesList.add(series2);
		XYChart chart = new XYChart(xySeriesList, yAxis, xAxis);
//		ScatterChart chart = new ScatterChart(xySeriesList, yAxis, xAxis);

		chart.setSize(1000, 500);
		chart.rightOffset = 200;

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("Sea Lion Height versus Weight");

		return chart;
	}

}
