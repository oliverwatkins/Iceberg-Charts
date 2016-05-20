package com.bluewalrus.main.test.math;

import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointCircle;
import com.bluewalrus.chart.draw.point.UIPointSquare;
import com.bluewalrus.chart.draw.point.UIPointTriangle;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.main.test.fractions.TestDataXY_Fractions;
import com.bluewalrus.scaling.LinearNumericalAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;

public class TestDataXY_SineCurve extends ChartTester {

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_SineCurve();
		t.testChart(t.getChart());
	}
	

	public Chart getChart() {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();

		
		for (int x = -170; x <= 170; x++) {
			
			values.add(new DataPoint(x, (int) (50 * f((x / 100.0) * 2 * Math.PI))));
		}

		ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
		
		for (int x = -170; x <= 170; x++) {
			
			DataPoint dp = new DataPoint(x, (int) (50 * gCos((x / 100.0) * 2
					* Math.PI)));
			
			System.out.println("dp = " + dp);
			values3.add(dp);
		}
		
//		XYDataSeries series = new XYDataSeries(new UIPointCircle(Color.BLUE, 1),
//				new Line(Color.BLUE), "x^2");
		XYDataSeries series = new XYDataSeries(null,
				new Line(Color.BLUE), "x^2");
		
		series.dataPoints = values;

		XYDataSeries series3 = new XYDataSeries(
				new UIPointCircle(Color.PINK, 1), new Line(Color.PINK), "e^x");
		series3.dataPoints = values3;

		xySeriesList.add(series);
		xySeriesList.add(series3);

		
		
		XAxis xAxis = new XAxis(new LinearNumericalAxisScalingX(), "X");
		xAxis.axisScaling.setMinValue(0);
		xAxis.axisScaling.setMaxValue(400);
		
		XYChart lineChart = new XYChart("Sine/Cos Curve", "x", "y", xySeriesList);//, yAxis, xAxis);

		lineChart.xAxis = xAxis;
		
		return lineChart;
	}

	double f(double x) {
		return Math.sin(x);
	}

	double gCos(double y) {
		return Math.cos(y);
	}

}
