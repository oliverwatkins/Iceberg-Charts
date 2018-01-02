package com.frontangle.ichart.main.test.bar;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.bar.XYBarDataSeries;
import com.frontangle.ichart.chart.datapoint.DataPointBar;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.main.test.Showcase;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_3_Simple_fixed_axis extends ChartTester {

	@Showcase
	public Chart getChart() {
		
		XYBarDataSeries barSeries = new XYBarDataSeries();
		barSeries.add(new DataPointBar("Apple", 98, Color.ORANGE));
		barSeries.add(new DataPointBar("Banana", 44, Color.GRAY));
		barSeries.add(new DataPointBar("Barley", 40, Color.DARK_GRAY));
		barSeries.add(new DataPointBar("Rice", 3, Color.BLUE));
		barSeries.add(new DataPointBar("Wheat", 50, Color.RED));
		barSeries.add(new DataPointBar("Oranges", 30, Color.BLACK));
		barSeries.add(new DataPointBar("Corn", 54, Color.CYAN));
		
		XYChart chart = new XYChart("Simple Bar Chart (Axis Set)", 
				"Commodity", "Price (USD)", barSeries);

		chart.yAxis.setMinValue(0);
		chart.yAxis.setMaxValue(100);
		
		return chart;
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_3_Simple_fixed_axis();
		t.testChart(t.getChart());
	}
	

	
	@Override
	public String getNiceTitle()  {
		return "Bar with fixed axis";
	}
	
	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
}
