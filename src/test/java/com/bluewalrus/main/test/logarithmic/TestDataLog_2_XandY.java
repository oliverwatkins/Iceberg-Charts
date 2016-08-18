package com.bluewalrus.main.test.logarithmic;


import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointSquare;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;
import com.bluewalrus.scaling.LogarithmicAxisScaling;
import com.bluewalrus.scaling.TimeSeriesAxisScaling;

public class TestDataLog_2_XandY extends ChartTester {

	@Override
	public Chart getChart() throws ParseException {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 6));
		values.add(new DataPoint(15, 20));
		values.add(new DataPoint(139, 122));
		values.add(new DataPoint(2001, 4000));
		values.add(new DataPoint(9301, 12000));

		XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.BLUE), new Line(Color.BLUE), "Something Blue");
		series.dataPoints = values;

		NumericalInterval t1 = new NumericalInterval(6, 50.0, new Line(Color.GRAY, false, 1));
		NumericalInterval t2 = new NumericalInterval(3, 10.0, new Line(Color.LIGHT_GRAY, true, 1));
		NumericalInterval t3 = new NumericalInterval(1, 5.0, null);

		YAxis yAxis = new YAxis(new LogarithmicAxisScaling(1, 10000.0), "Y Axis");
		
		XAxis xAxis = new XAxis(new LogarithmicAxisScaling(1, 10000.0), "Log Axis"); //timeInt2, timeInt3), "Time Series");

		xySeriesList.add(series);

		XYChart chart = new XYChart(xySeriesList, yAxis, xAxis);

		chart.setSize(1000, 500);
		chart.rightOffset = 200;

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("Some Kind of XY Chart");

		return chart;
	}
	
	@Override
	public String getNiceTitle() {
		return "Time Series: ???";
	}
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataLog_2_XandY();
		t.testChart(t.getChart());
	}
	
	
}

