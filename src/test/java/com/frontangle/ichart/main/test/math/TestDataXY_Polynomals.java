package com.frontangle.ichart.main.test.math;

import java.awt.Color;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointCircle;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.main.test.Showcase;

public class TestDataXY_Polynomals extends ChartTester {

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Polynomals();
		t.testChart(t.getChart());
	}
	

	@Showcase
	public Chart getChart() {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		
		for (int x = -300; x <= 300; x++) {
			double xd = ((double)(x))/100;
			
			DataPoint dp = new DataPoint(xd, f(xd));
			
			values.add(dp);
		}

		ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
		
		for (int x = -300; x <= 300; x++) {
			double xd = ((double)(x))/100;
			
			DataPoint dp = new DataPoint(xd, gCos(xd));
			
			values3.add(dp);
		}
		
		XYDataSeries series = new XYDataSeries(new UIPointCircle(Color.BLUE, 1), new Line(Color.BLUE), "x^2");
		series.dataPoints = values;

		XYDataSeries series3 = new XYDataSeries(new UIPointCircle(Color.RED, 1), new Line(Color.RED), "e^x");
		series3.dataPoints = values3;

		xySeriesList.add(series);
		xySeriesList.add(series3);

		XYChart chart = new XYChart("Polynomals", "x", "y", xySeriesList);

		return chart;
	}

	double f(double x) {
		return Math.pow(x, 5) - 8*Math.pow(x, 3) + 10*x + 6;
	}

	double gCos(double x) {
		return Math.pow(x, 4) - 8*Math.pow(x, 2);
	}
	
	@Override
	public String getNiceTitle() {
		return "Maths 2";
	}
}
