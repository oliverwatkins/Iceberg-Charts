package com.bluewalrus.main.test.bubble;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPointWithMagnitude;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointBubble;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;

public class TestDataBubble_2_series extends ChartTester {

	@Override
	public Chart getChart() {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPointWithMagnitude> values = new ArrayList<DataPointWithMagnitude>();
		ArrayList<DataPointWithMagnitude> values2 = new ArrayList<DataPointWithMagnitude>();

		values.add(new DataPointWithMagnitude(15, 12, 40));
		values.add(new DataPointWithMagnitude(40, 20, 50));
		values.add(new DataPointWithMagnitude(90, 89, 110));
		values.add(new DataPointWithMagnitude(210, 110, 110));
		values.add(new DataPointWithMagnitude(230, 130, 24));
		values.add(new DataPointWithMagnitude(250, 110, 80));
		values.add(new DataPointWithMagnitude(280, 150, 150));
		values.add(new DataPointWithMagnitude(290, 180, 111));
		values.add(new DataPointWithMagnitude(300, 110, 123));
		values.add(new DataPointWithMagnitude(312, 120, 79));
		values.add(new DataPointWithMagnitude(344, 20, 12));
		values.add(new DataPointWithMagnitude(378, 80, 55));
		values.add(new DataPointWithMagnitude(400, 60, 66));

		values2.add(new DataPointWithMagnitude(15, 22, 20));
		values2.add(new DataPointWithMagnitude(40, 30, 70));
		values2.add(new DataPointWithMagnitude(90, 99, 60));
		values2.add(new DataPointWithMagnitude(210, 140, 87));
		values2.add(new DataPointWithMagnitude(300, 110, 12));
		values2.add(new DataPointWithMagnitude(350, 175, 76));
		values2.add(new DataPointWithMagnitude(370, 134, 40));
		values2.add(new DataPointWithMagnitude(400, 349, 80));

		XYDataSeries<DataPointWithMagnitude> series = new XYDataSeries<DataPointWithMagnitude>(
				values, new UIPointBubble(Color.BLUE), null, "Series 1");
		XYDataSeries<DataPointWithMagnitude> series2 = new XYDataSeries<DataPointWithMagnitude>(
				values2, new UIPointBubble(Color.ORANGE), null, "Series 2");


        NumericalInterval i1 = new NumericalInterval(5, 50.0, new Line(Color.WHITE, false, 1));
        NumericalInterval i2 = new NumericalInterval(2, 10.0, null);
        

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(-44.0, 400.0,
				i1, i2, null), "Y Axis");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(-12.0, 400.0,
				i1, i2, null), "X Axis");

		xySeriesList.add(series);
		xySeriesList.add(series2);

		XYChart chart = new XYChart(xySeriesList, yAxis, xAxis, 15);

		chart.setSize(700, 400);

		chart.rightOffset = 200;

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("Two Series Bubble Chart");

		chart.setChartBackground(Color.LIGHT_GRAY);

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "Bubble: Multiple series";
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBubble_2_series();
		t.testChart(t.getChart());
	}
}
