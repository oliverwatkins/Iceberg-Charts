package com.frontangle.ichart.main.test.bar;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

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
		
		XYChart chart = new XYChart("Simple Bar Chart (Gradient Color)", 
				"Commodity", "Price Change % (USD)", barSeries);

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
	
	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}

}
