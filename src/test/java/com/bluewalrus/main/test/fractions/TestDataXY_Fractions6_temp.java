package com.bluewalrus.main.test.fractions;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.main.test.ChartTester;

public class TestDataXY_Fractions6_temp extends ChartTester {
	
	@Override
	public Chart getChart() {

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(-0.10000009, 0.001));
		values.add(new DataPoint(0.002, 0.002));
		values.add(new DataPoint(0.003, 0.003));
		
		ArrayList<XYDataSeries> s = new ArrayList<XYDataSeries>();
				
		XYChart chart = new XYChart(values, "", "", "");
		
		return chart;
	}
	
	@Override
	public String getNiceTitle()  {
		return "Fractions 6";
	}
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Fractions6_temp();
		t.testChart(t.getChart());
	}
	
}
