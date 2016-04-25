package com.bluewalrus.main.test.bar;

import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.bar.XYBarDataSeries;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.main.test.ChartTester;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_1_Simple extends ChartTester {

	@Override
	public Chart getChart() {
		
		XYBarDataSeries barSeries = new XYBarDataSeries();
		barSeries.add(new DataPointBar("Apple", 90, Color.ORANGE));
		barSeries.add(new DataPointBar("Banana", 14, Color.GRAY));
		barSeries.add(new DataPointBar("Barley", 4, Color.DARK_GRAY));
		barSeries.add(new DataPointBar("Rice", 30, Color.BLUE));
		barSeries.add(new DataPointBar("Wheat", 10, Color.RED));
		barSeries.add(new DataPointBar("Oranges", 30, Color.BLACK));
		barSeries.add(new DataPointBar("Corn", 54, Color.CYAN));
		
		XYChart barChart = new XYChart(barSeries, "Simple Bar Chart", "Commodity", "Price (USD)");

		return barChart;
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_1_Simple();
		t.testChart(t.getChart());
	}

}
