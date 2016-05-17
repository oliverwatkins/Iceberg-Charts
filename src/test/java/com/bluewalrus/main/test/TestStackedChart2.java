package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.StackedXYChart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointCandleStick;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointBar;
import com.bluewalrus.chart.draw.point.UIPointCandleStick;
import com.bluewalrus.chart.draw.point.UIPointCircle;
import com.bluewalrus.scaling.LinearNumericalAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;

public class TestStackedChart2 extends ChartTester {
	public Chart getChart() {

		YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(), "Y1");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScalingX(), "X");
		
		yAxis.axisScaling.setMinValue(0);
		yAxis.axisScaling.setMaxValue(50);
		
		xAxis.axisScaling.setMinValue(0);
		xAxis.axisScaling.setMaxValue(30);
		
		
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


		XYDataSeries series = new XYDataSeries(values, "First");
		XYDataSeries series2 = new XYDataSeries(values2, "Second");

		series.pointType = new UIPointCircle(Color.ORANGE);
		series.line = new Line(Color.ORANGE);
		series2.pointType = new UIPointCircle(Color.RED);
		series2.line = new Line(Color.RED);
		
		
		ArrayList<XYDataSeries> list = new ArrayList<XYDataSeries>();
		list.add(series);
		list.add(series2);

		XYChart lineChart = new XYChart(xAxis, yAxis); 
		lineChart.data = list;
		
		
		
		
		
		
		
		

		ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
		values3.add(new DataPoint(5, 91));
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
		
		XYDataSeries series3 = new XYDataSeries(values3, "Third");
//		XYDataSeries series4 = new XYDataSeries(values4, "Fourth");
		

		series3.pointType = new UIPointBar(Color.GREEN, 50);
//		series3.line = new Line(Color.GREEN);
//		series4.pointType = new UIPointBar(Color.BLUE);
//		series4.line = new Line(Color.BLUE);
		

		
		list = new ArrayList<XYDataSeries>();
		list.add(series3);
//		list.add(series4);
		
		YAxis yAxis2 = new YAxis(new LinearNumericalAxisScalingY(), "Y2");
		yAxis2.axisScaling.setMinValue(0);
		yAxis2.axisScaling.setMaxValue(100);
		
		
		XYChart lineChart2 = new XYChart(xAxis, yAxis2); 
		lineChart2.data = list;
		


		
		ArrayList charts = new ArrayList<XYChart>();
		charts.add(getCandleChart());
		charts.add(lineChart2);
		
		ArrayList percentages = new ArrayList<Integer>();
		percentages.add(30);
		percentages.add(70);
		
		
		StackedXYChart stackedXYChart = new StackedXYChart("Stacked Chart", charts, percentages);
		
		
		return stackedXYChart;
	}
	
	
	public XYChart getCandleChart() {

		// TEST_DATA_START

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
//		values.add(new DataPointBoxPlot(5, 53, 15, 26, 37, 49, 70));

		values.add(new DataPointCandleStick(10, 80, 70, 60, 50, true));
		values.add(new DataPointCandleStick(20, 80, 75, 50, 15, true));
		values.add(new DataPointCandleStick(30, 67, 45, 55, 41, false)); //3rd has to be less than 4th!!
		values.add(new DataPointCandleStick(40, 63, 61, 56, 50, true));
		values.add(new DataPointCandleStick(50, 69, 56, 50, 43, true));
		values.add(new DataPointCandleStick(60, 78, 70, 64, 50, true));
		values.add(new DataPointCandleStick(70, 99, 75, 44, 31, true));
		values.add(new DataPointCandleStick(80, 80, 60, 70, 50, false)); //3rd has to be less than 4th!!
		values.add(new DataPointCandleStick(90, 80, 75, 50, 25, true));
		values.add(new DataPointCandleStick(100, 80, 70, 60, 50, true));
		values.add(new DataPointCandleStick(110, 80, 75, 50, 15, true));
		
		
//		values.add(new DataPointCandleStick(40, 54, 40, 46, 73, true));
//		values.add(new DataPointCandleStick(50, 30, 34, 39, 72, false));
//		values.add(new DataPointCandleStick(60, 45, 36, 45, 58, true));
//		values.add(new DataPointCandleStick(70, 47, 41, 45, 49, false));
//		values.add(new DataPointCandleStick(80, 70, 68, 64, 50, false));

		XYDataSeries series = new XYDataSeries(values, new UIPointCandleStick(
				new Color(181, 197, 207, 100)), null, "1994");

		YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(0.0, 100.0, 50.0, 10.0, null), "y Axis");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScalingX(0.0, 100.0, 50.0, 10.0, null), "x Axis");

		xySeriesList.add(series);

		XYChart chart = new XYChart(xySeriesList, yAxis, xAxis);

		chart.setSize(1000, 500);
		chart.rightOffset = 200;

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("Box Plot");

		return chart;
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestStackedChart2();
		t.testChart(t.getChart());
	}
}
