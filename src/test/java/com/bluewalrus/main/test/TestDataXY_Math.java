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
import com.bluewalrus.chart.bar.GridLine;
import com.bluewalrus.chart.bar.Line;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointBoxPlot;
import com.bluewalrus.chart.draw.point.UIPointBoxPlot;
import com.bluewalrus.chart.draw.point.UIPointCircle;
import com.bluewalrus.chart.draw.point.UIPointSquare;
import com.bluewalrus.chart.draw.point.UIPointTriangle;
import com.bluewalrus.scaling.LinearNumericalAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;

public class TestDataXY_Math extends ChartTester {

	public static void main(String[] s) {
//		getChart();
	}


	public Chart getChart() {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();

		for (int i = -10; i < 10; i++) {
			values.add(new DataPoint(i, i * i));
		}


		ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
		for (int i = -5; i < 5; i++) {

			double x = Math.exp(i);
			System.out.println(" x = " + x);

			values3.add(new DataPoint(i, x));
		}

		XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.BLUE),
				new Line(Color.BLUE), "x^2");
		series.dataPoints = values;

		XYDataSeries series3 = new XYDataSeries(new UIPointTriangle(Color.PINK),
				new Line(Color.PINK), "e^x");
		series3.dataPoints = values3;

		NumericalInterval t1x = new NumericalInterval(6, 10.0, new GridLine(Color.GRAY, false, 1));
		NumericalInterval t2x = new NumericalInterval(3, 5.0, new GridLine(Color.LIGHT_GRAY, true, 1));
		NumericalInterval t3x = new NumericalInterval(1, 1.0, new GridLine(Color.LIGHT_GRAY, true, 1));

		YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(-10.0, 10.0, t1x, t2x, t3x), "Y Axis");

		XAxis xAxis = new XAxis(new LinearNumericalAxisScalingX(-10.0, 10.0, t1x, t2x, t3x), "X Value");

		xySeriesList.add(series);
		xySeriesList.add(series3);

		XYChart lineChart = new XYChart(xySeriesList, yAxis, xAxis);

		lineChart.setSize(1000, 500);
		lineChart.rightOffset = 200;

		lineChart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		lineChart.setTitle("Maths");

		return lineChart;
	}

}
