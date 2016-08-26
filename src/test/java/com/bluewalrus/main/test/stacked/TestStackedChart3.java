package com.bluewalrus.main.test.stacked;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.StackedXYChart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.IntervalStyling;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointCandleStick;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointCandleStick;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.main.test.bar.TestDataBar_5_PosNegColor;
import com.bluewalrus.main.test.math.TestDataXY_SineCurve;
import com.bluewalrus.main.test.xy.TestDataXY_Scatter;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;

public class TestStackedChart3 extends ChartTester {
	public Chart getChart() {

		// x-Axis needs to be shared
		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(), "X");
		xAxis.axisScaling.setMinValue(0);
		xAxis.axisScaling.setMaxValue(100);

		XYChart topChart = (XYChart) new TestDataBar_5_PosNegColor().getChart();
		topChart.xAxis = xAxis;

		XYChart bottomChart = (XYChart) new TestDataXY_SineCurve().getChart();
		bottomChart.xAxis = xAxis;

		XYChart middleChart = (XYChart) new TestDataXY_Scatter().getChart();
		middleChart.xAxis = xAxis;

		ArrayList<XYChart> charts = new ArrayList<XYChart>();
		charts.add(topChart);
		charts.add(middleChart);
		charts.add(bottomChart);

		// height percentages
		ArrayList<Integer> percentages = new ArrayList<Integer>();
		percentages.add(40);
		percentages.add(30);
		percentages.add(30);

		StackedXYChart chart = new StackedXYChart(
				"Three random charts with same X Axis", charts, percentages);

		return chart;
	}

	public XYChart getTopChart(XAxis xAxis) {

		XYChart topChart = (XYChart) new TestDataBar_5_PosNegColor().getChart();
		topChart.xAxis = xAxis;
		return topChart;

	}

	public XYChart getBarChart(XAxis xAxis) {

		XYChart bottomChart = (XYChart) new TestDataXY_SineCurve().getChart();
		bottomChart.xAxis = xAxis;
		return bottomChart;

	}

	public XYChart getScatterChart(XAxis xAxis) {

		XYChart middleChart = (XYChart) new TestDataXY_Scatter().getChart();
		middleChart.xAxis = xAxis;
		return middleChart;

	}

	public XYChart getCandleChart(XAxis xAxis) {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();

		values.add(new DataPointCandleStick(10, 83, 70, 60, 50, true));
		values.add(new DataPointCandleStick(20, 90, 75, 50, 15, true));
		values.add(new DataPointCandleStick(30, 87, 45, 55, 41, false)); // 3rd
																			// has
																			// to
																			// be
																			// less
																			// than
																			// 4th!!
		values.add(new DataPointCandleStick(40, 83, 61, 56, 50, true));
		values.add(new DataPointCandleStick(50, 89, 56, 50, 43, true));
		values.add(new DataPointCandleStick(60, 78, 70, 64, 50, true));
		values.add(new DataPointCandleStick(70, 99, 75, 44, 31, true));
		values.add(new DataPointCandleStick(80, 80, 60, 70, 50, false)); // 3rd
																			// has
																			// to
																			// be
																			// less
																			// than
																			// 4th!!
		values.add(new DataPointCandleStick(90, 99, 75, 50, 25, true));
		values.add(new DataPointCandleStick(100, 93, 70, 60, 50, true));
		values.add(new DataPointCandleStick(110, 95, 71, 55, 44, true));
		values.add(new DataPointCandleStick(120, 94, 67, 53, 41, true));
		values.add(new DataPointCandleStick(130, 81, 65, 51, 42, true));
		values.add(new DataPointCandleStick(140, 85, 61, 41, 31, true));
		values.add(new DataPointCandleStick(150, 81, 61, 44, 35, true));
		values.add(new DataPointCandleStick(160, 55, 53, 50, 49, true));
		values.add(new DataPointCandleStick(170, 71, 48, 41, 15, true));
		values.add(new DataPointCandleStick(180, 70, 41, 36, 15, true));
		values.add(new DataPointCandleStick(190, 68, 75, 50, 15, true));
		values.add(new DataPointCandleStick(200, 67, 95, 60, 55, true));
		values.add(new DataPointCandleStick(210, 73, 77, 66, 52, true));
		values.add(new DataPointCandleStick(220, 75, 74, 50, 15, true));
		values.add(new DataPointCandleStick(230, 77, 45, 55, 41, false)); // 3rd
																			// has
																			// to
																			// be
																			// less
																			// than
																			// 4th!!
		values.add(new DataPointCandleStick(240, 78, 45, 56, 35, false)); // 3rd
																			// has
																			// to
																			// be
																			// less
																			// than
																			// 4th!!
		values.add(new DataPointCandleStick(250, 75, 56, 50, 43, true));
		values.add(new DataPointCandleStick(260, 78, 70, 64, 50, true));
		values.add(new DataPointCandleStick(270, 99, 75, 44, 31, true));
		values.add(new DataPointCandleStick(280, 80, 60, 70, 50, false)); // 3rd
																			// has
																			// to
																			// be
																			// less
																			// than
																			// 4th!!
		values.add(new DataPointCandleStick(290, 77, 51, 41, 25, true));
		values.add(new DataPointCandleStick(300, 41, 31, 13, 11, true));
		values.add(new DataPointCandleStick(310, 35, 31, 17, 15, true));
		values.add(new DataPointCandleStick(320, 14, 12, 9, 5, true));
		values.add(new DataPointCandleStick(330, 17, 13, 11, 2, true));
		values.add(new DataPointCandleStick(340, 16, 11, 10, 9, true));
		values.add(new DataPointCandleStick(350, 19, 15, 13, 11, true));
		values.add(new DataPointCandleStick(360, 23, 21, 20, 15, true));
		values.add(new DataPointCandleStick(370, 41, 40, 31, 30, true));
		values.add(new DataPointCandleStick(380, 54, 51, 50, 49, true));
		values.add(new DataPointCandleStick(390, 67, 56, 50, 17, true));
		values.add(new DataPointCandleStick(400, 63, 61, 50, 15, true));

		XYDataSeries series = new XYDataSeries(values, new UIPointCandleStick(
				new Color(255, 51, 0)), null, "1994");


		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(0.0, 100.0, 50.0, 10.0, null), "Price (USD)");
		
		yAxis.axisScaling.interval1.styling = new IntervalStyling(5, new Line(Color.LIGHT_GRAY, true), null);
		yAxis.axisScaling.interval2.styling = new IntervalStyling(2, new Line(Color.LIGHT_GRAY, true), null);
		

		xySeriesList.add(series);

		XYChart chart = new XYChart(xySeriesList, yAxis, xAxis);

		chart.setSize(1000, 500);
		chart.rightOffset = 200;

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("Box Plot");

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "Stacked 3";
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestStackedChart3();
		t.testChart(t.getChart());
	}
}
