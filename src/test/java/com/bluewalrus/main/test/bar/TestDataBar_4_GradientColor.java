package com.bluewalrus.main.test.bar;

import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.bar.BarDisplayOptions;
import com.bluewalrus.chart.bar.GradiantRule;
import com.bluewalrus.chart.bar.XYBarDataSeries;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.main.test.Showcase;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_4_GradientColor extends ChartTester {

	@Showcase
	public Chart getChart() {
		
		XYBarDataSeries barSeries = new XYBarDataSeries();
		barSeries.add(new DataPointBar("a", 11));
		barSeries.add(new DataPointBar("b", 14));
		barSeries.add(new DataPointBar("c", 7));
		barSeries.add(new DataPointBar("d", 30));
		barSeries.add(new DataPointBar("e", 18));
		barSeries.add(new DataPointBar("f", 21));
		barSeries.add(new DataPointBar("h", 22));
		barSeries.add(new DataPointBar("i", 14));
		barSeries.add(new DataPointBar("j", 67));
		barSeries.add(new DataPointBar("k", 87));
		barSeries.add(new DataPointBar("l", 94));
		barSeries.add(new DataPointBar("m", 14));
		barSeries.add(new DataPointBar("n", 37));
		barSeries.add(new DataPointBar("o", 4));
		barSeries.add(new DataPointBar("p", -14));
		barSeries.add(new DataPointBar("q", -54));
		barSeries.add(new DataPointBar("r", -51));
		barSeries.add(new DataPointBar("s", -45));
		barSeries.add(new DataPointBar("t", -41));
		barSeries.add(new DataPointBar("u", -13));
		barSeries.add(new DataPointBar("v", 2));
		barSeries.add(new DataPointBar("w", 3));
		barSeries.add(new DataPointBar("x", 14));
		barSeries.add(new DataPointBar("y", 11));
		barSeries.add(new DataPointBar("z", 41));

		BarDisplayOptions barDisplayOptions = new BarDisplayOptions();
		
		barDisplayOptions.setGradiantRule(
				new GradiantRule(0, 100, Color.BLUE, Color.RED));
		
		barSeries.setUpBarDisplayOptions(barDisplayOptions);
		
		XYChart chart = new XYChart(barSeries, "Simple Bar Chart (Gradient Color)", 
				"Commodity", "Price Change % (USD)");

		return chart;
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_4_GradientColor();
		t.testChart(t.getChart());
	}
	
	@Override
	public String getNiceTitle()  {
		return "Bar: color gradients";
	}

}
