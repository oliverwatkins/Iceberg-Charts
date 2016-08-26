package com.bluewalrus.main.test.xy;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.main.test.ChartTester;

public class TestDataXY_Simple extends ChartTester {

	@Override
	public Chart getChart() {

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 96));
		values.add(new DataPoint(58, 43));
		values.add(new DataPoint(101, 90));
		values.add(new DataPoint(135, 67));
		values.add(new DataPoint(150, 70));

		XYChart chart = new XYChart(values, "My Easy Example", "X Axis",
				"Y Axis");

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "XY: Simple";
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Simple();
		t.testChart(t.getChart());
	}

}
