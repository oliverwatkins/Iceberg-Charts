package com.frontangle.ichart.main.test.bar;

import java.awt.Color;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.bar.BarDisplayOptions;
import com.frontangle.ichart.chart.bar.GradiantRule;
import com.frontangle.ichart.chart.bar.XYBarDataSeries;
import com.frontangle.ichart.chart.datapoint.DataPointBar;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.main.test.Showcase;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_5_PosNegColor extends ChartTester {

	@Showcase
	public Chart getChart() {

		XYBarDataSeries barSeries = new XYBarDataSeries();
		barSeries.add(new DataPointBar("a", 11));
		barSeries.add(new DataPointBar("b", 14));
		barSeries.add(new DataPointBar("c", 7));
		barSeries.add(new DataPointBar("m", 14));
		barSeries.add(new DataPointBar("n", 37));
		barSeries.add(new DataPointBar("o", 4));
		barSeries.add(new DataPointBar("p", -14));
		barSeries.add(new DataPointBar("q", -54));
		barSeries.add(new DataPointBar("t", -41));
		barSeries.add(new DataPointBar("u", -13));
		barSeries.add(new DataPointBar("v", 2));
		barSeries.add(new DataPointBar("w", 3));
		barSeries.add(new DataPointBar("x", 14));
		barSeries.add(new DataPointBar("z", 41));

		BarDisplayOptions barDisplayOptions = new BarDisplayOptions();

		barDisplayOptions.positiveColor = Color.DARK_GRAY;
		barDisplayOptions.negativeColor = Color.LIGHT_GRAY;

		barSeries.setUpBarDisplayOptions(barDisplayOptions);

		XYChart chart = new XYChart(barSeries, "Simple Bar Chart (+/- Color)",
				"Commodity", "Price Change % (USD)");

		return chart;
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_5_PosNegColor();
		t.testChart(t.getChart());
	}

	@Override
	public String getNiceTitle() {
		return "Bar: pos/neg color";
	}

}
