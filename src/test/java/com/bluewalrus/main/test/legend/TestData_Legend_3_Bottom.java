package com.bluewalrus.main.test.legend;

import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.legend.LegendPosition;
import com.bluewalrus.main.test.ChartTester;

public class TestData_Legend_3_Bottom extends ChartTester {

	public Chart getChart() {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 30));
		values.add(new DataPoint(10, 11));
		values.add(new DataPoint(15, 14));
		values.add(new DataPoint(20, 5));
		values.add(new DataPoint(25, 8));

		ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();
		values2.add(new DataPoint(5, 2));
		values2.add(new DataPoint(10, 33));
		values2.add(new DataPoint(15, 6));
		values2.add(new DataPoint(20, 14));
		values2.add(new DataPoint(25, 17));

		ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
		values3.add(new DataPoint(5, 130));
		values3.add(new DataPoint(10, 74));
		values3.add(new DataPoint(15, 67));
		values3.add(new DataPoint(20, 22));
		values3.add(new DataPoint(25, 68));

		ArrayList<DataPoint> values4 = new ArrayList<DataPoint>();
		values4.add(new DataPoint(5, 90));
		values4.add(new DataPoint(10, 65));
		values4.add(new DataPoint(15, 80));
		values4.add(new DataPoint(20, 83));
		values4.add(new DataPoint(23, 90));

		XYDataSeries series = new XYDataSeries(values, "First");
		XYDataSeries series2 = new XYDataSeries(values2, "Second");
		XYDataSeries series3 = new XYDataSeries(values3, "Third");
		XYDataSeries series4 = new XYDataSeries(values4, "Fourth");

		XYChart chart = new XYChart("Many Series Example", "My X Axis",
				"My Y Axis", series, series2, series3, series4);

		chart.legendPosition = LegendPosition.BOTTOM;
		
		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "XY: Simple Series";
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestData_Legend_3_Bottom();
		t.testChart(t.getChart());
	}

}
