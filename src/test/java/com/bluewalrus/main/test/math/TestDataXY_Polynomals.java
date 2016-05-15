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

public class TestDataXY_Polynomals extends ChartTester {

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Polynomals();
		t.testChart(t.getChart());
	}
	

	public Chart getChart() {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();

		
		for (int x = -300; x <= 300; x++) {
			
			double xd = ((double)(x))/100;
			
			DataPoint dp = new DataPoint(xd, f(xd));
			System.out.println("dp " + dp);
			
			values.add(dp);
		}

		ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
		
		for (int x = -300; x <= 300; x++) {
			
			
			double xd = ((double)(x))/100;
			
			DataPoint dp = new DataPoint(xd, gCos(xd));
			System.out.println("dp " + dp);
			
			values3.add(dp);
			
//			double xd = x/100;
			
//			values3.add(new DataPoint(xd, gCos(xd)));
			
//			DataPoint dp = new DataPoint(x, (int) (50 * gCos((x / 100.0) * 2
//					* Math.PI)));
//			
//			System.out.println("dp = " + dp);
//			values3.add(dp);
		}
		
		XYDataSeries series = new XYDataSeries(new UIPointCircle(Color.BLUE, 1),
				new Line(Color.BLUE), "x^2");
		series.dataPoints = values;

		XYDataSeries series3 = new XYDataSeries(
				new UIPointCircle(Color.RED, 1), new Line(Color.RED), "e^x");
		series3.dataPoints = values3;

		xySeriesList.add(series);
		xySeriesList.add(series3);

		XYChart lineChart = new XYChart("Polynomals", "x", "y", xySeriesList);//, yAxis, xAxis);

		return lineChart;
	}

	double f(double x) {
		return Math.pow(x, 5) - 8*Math.pow(x, 3) + 10*x + 6;
	}

	double gCos(double x) {
		return Math.pow(x, 4) - 8*Math.pow(x, 2);
	}

}
