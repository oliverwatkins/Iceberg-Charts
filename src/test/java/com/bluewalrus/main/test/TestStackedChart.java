package com.bluewalrus.main.test;

import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.StackedXYChart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointCircle;
import com.bluewalrus.scaling.LinearNumericalAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;

public class TestStackedChart extends ChartTester {
	public Chart getChart() {

		YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(), "Y");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScalingX(), "X");
		
		yAxis.axisScaling.setMinValue(0);
		yAxis.axisScaling.setMaxValue(100);
		
		xAxis.axisScaling.setMinValue(0);
		xAxis.axisScaling.setMaxValue(100);
		
		
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
		XYDataSeries series4 = new XYDataSeries(values4, "Fourth");
		

		series3.pointType = new UIPointCircle(Color.GREEN);
		series3.line = new Line(Color.GREEN);
		series4.pointType = new UIPointCircle(Color.BLUE);
		series4.line = new Line(Color.BLUE);
		

		
		list = new ArrayList<XYDataSeries>();
		list.add(series3);
		list.add(series4);
		
		XYChart lineChart2 = new XYChart(xAxis, yAxis); 
		lineChart2.data = list;
		


		
		StackedXYChart stackedXYChart = new StackedXYChart(lineChart, lineChart2);
		
		
		return stackedXYChart;
	}
	
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestStackedChart();
		t.testChart(t.getChart());
	}
}
