package com.bluewalrus.main.test.fractions;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointCircle;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;

public class TestDataXY_Fractions7_test extends ChartTester {
	
	@Override
	public Chart getChart() {


		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(92, 90));
		values.add(new DataPoint(93, 10));
		
		XYDataSeries<DataPoint> series = new XYDataSeries<DataPoint>(
				new UIPointCircle(Color.ORANGE, 15),
				new Line(Color.RED, true, 1), "1");
		
		series.dataPoints = values;
		
		ArrayList<XYDataSeries> s = new ArrayList<XYDataSeries>();
		s.add(series);
		
		XAxis xa = new XAxis(
				new LinearNumericalAxisScaling(90.0, 100.0, 
					new NumericalInterval(10, 10.0), 
					null, 
					null), 
				"1");
		YAxis ya = new YAxis(new LinearNumericalAxisScaling(0.0,100.0,
					new NumericalInterval(5, 10.0), 
					null, 
					null), 				
				"2");
		
		XYChart chart = new XYChart(s, ya, xa);
		
		chart.setTitle("Negative To Positive");
		
		return chart;
	}
	
	@Override
	public String getNiceTitle()  {
		return "Fractions 7";
	}
	
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Fractions7_test();
		t.testChart(t.getChart());
	}
	
}
