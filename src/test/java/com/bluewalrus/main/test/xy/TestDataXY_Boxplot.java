package com.bluewalrus.main.test.xy;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointBoxPlot;
import com.bluewalrus.chart.draw.point.UIPointBoxPlot;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;

public class TestDataXY_Boxplot extends ChartTester {

	public static void main(String[] s) {
		// getChart();
	}

	public XYChart getChart() {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPointBoxPlot(5, 53, 15, 26, 37, 49, 70));

		values.add(new DataPointBoxPlot(10, 73, 15, 26, 37, 49, 70));
		values.add(new DataPointBoxPlot(20, 50, 1, 25, 50, 75, 99));
		values.add(new DataPointBoxPlot(30, 73, 20, 25, 32, 38, 76));
		values.add(new DataPointBoxPlot(40, 73, 20, 40, 46, 73, 84));
		values.add(new DataPointBoxPlot(50, 73, 23, 34, 39, 72, 89));
		values.add(new DataPointBoxPlot(60, 73, 33, 36, 45, 58, 80));
		values.add(new DataPointBoxPlot(70, 73, 38, 41, 45, 49, 57));
		values.add(new DataPointBoxPlot(80, 73, 42, 45, 55, 63, 69));

		XYDataSeries series = new XYDataSeries(values, new UIPointBoxPlot(
				new Color(181, 197, 207, 100)), null, "1994");

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(0.0, 100.0,
				50.0, 10.0, null), "y Axis");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(0.0, 100.0,
				50.0, 10.0, null), "x Axis");

		xySeriesList.add(series);

		XYChart chart = new XYChart(xySeriesList, yAxis, xAxis);

		chart.setSize(1000, 500);

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("Box Plot");

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "XY: Boxplot";
	}

}
