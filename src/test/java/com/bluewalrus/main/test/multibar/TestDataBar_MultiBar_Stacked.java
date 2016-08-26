package com.bluewalrus.main.test.multibar;

import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.chart.datapoint.DataPointMultiBar;
import com.bluewalrus.chart.datapoint.DataPointMultiBar.MultiBarMode;
import com.bluewalrus.chart.draw.point.UIPointMultiBarStacked;
import com.bluewalrus.main.test.ChartTester;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_MultiBar_Stacked extends ChartTester {

	@Override
	public Chart getChart() {

		ArrayList<DataPointMultiBar> multiBarList = new ArrayList<DataPointMultiBar>();

		Color c1 = new Color(112, 12, 52);
		Color c2 = new Color(62, 82, 212);
		Color c3 = new Color(72, 52, 112);
		Color c4 = new Color(82, 112, 32);
		Color c5 = new Color(170, 62, 22);

		ArrayList<DataPointBar> values = new ArrayList<DataPointBar>();
		values.add(new DataPointBar("Automobile", 51, c1));
		values.add(new DataPointBar("Food Industry", 25, c2));
		values.add(new DataPointBar("Cosmetics", 10, c3));
		values.add(new DataPointBar("Travel Products", 5, c4));
		values.add(new DataPointBar("Government", 67, c5));

		DataPointMultiBar mb1 = new DataPointMultiBar(values, "2007",
				MultiBarMode.STACK_ON_TOP);
		multiBarList.add(mb1);

		ArrayList<DataPointBar> values2 = new ArrayList<DataPointBar>();
		values2.add(new DataPointBar("Automobile", 80, c1));
		values2.add(new DataPointBar("Food Industry", 45, c2));
		values2.add(new DataPointBar("Cosmetics", 12, c3));
		values2.add(new DataPointBar("Travel Products", 14, c4));
		values2.add(new DataPointBar("Government", 10, c5));

		DataPointMultiBar mb2 = new DataPointMultiBar(values2, "2008",
				MultiBarMode.STACK_ON_TOP);
		multiBarList.add(mb2);

		ArrayList<DataPointBar> values3 = new ArrayList<DataPointBar>();
		values3.add(new DataPointBar("Automobile", 5, c1));
		values3.add(new DataPointBar("Food Industry", 4, c2));
		values3.add(new DataPointBar("Cosmetics", 3, c3));
		values3.add(new DataPointBar("Travel Products", 1, c4));
		values3.add(new DataPointBar("Government", 2, c5));

		DataPointMultiBar mb3 = new DataPointMultiBar(values3, "2009",
				MultiBarMode.STACK_ON_TOP);
		multiBarList.add(mb3);

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		XYDataSeries series = new XYDataSeries(multiBarList,
				new UIPointMultiBarStacked(80), null, "1994");

		xySeriesList.add(series);

		XYChart chart = new XYChart("Stacked", "Year", "y", xySeriesList);

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "Multibar : stacked";
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_MultiBar_Stacked();
		t.testChart(t.getChart());
	}

}
