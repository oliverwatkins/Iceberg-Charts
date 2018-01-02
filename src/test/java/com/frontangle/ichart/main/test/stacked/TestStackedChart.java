package com.frontangle.ichart.main.test.stacked;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.StackedXYChart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointBar;
import com.frontangle.ichart.chart.draw.point.UIPointCircle;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;

public class TestStackedChart extends ChartTester {
	
	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	
	public Chart getChart() {

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(), "Y1");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(), "X");
		
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

		XYChart lineChart = new XYChart("","","",xAxis, yAxis); 
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

		series3.pointType = new UIPointBar(Color.GREEN, 50);
		
		list = new ArrayList<XYDataSeries>();
		list.add(series3);
		
		YAxis yAxis2 = new YAxis(new LinearNumericalAxisScaling(), "Y2");
		yAxis2.axisScaling.setMinValue(0);
		yAxis2.axisScaling.setMaxValue(100);
		
		XYChart lineChart2 = new XYChart("","","",xAxis, yAxis2); 
		lineChart2.data = list;
		
		ArrayList charts = new ArrayList<XYChart>();
		charts.add(lineChart);
		charts.add(lineChart2);
		
		ArrayList percentages = new ArrayList<Integer>();
		percentages.add(30);
		percentages.add(70);
		
		StackedXYChart chart = new StackedXYChart("Stacked Chart", charts, percentages);
		
		return chart;
	}
	
	
	@Override
	public String getNiceTitle() {
		return "Stacked 1";
	}
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestStackedChart();
		t.testChart(t.getChart());
	}
}
