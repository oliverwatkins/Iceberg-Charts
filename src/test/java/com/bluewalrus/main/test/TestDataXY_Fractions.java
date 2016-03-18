package com.bluewalrus.main.test;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.datapoint.DataPoint;

public class TestDataXY_Fractions extends ChartTester {
	
	@Override
	public Chart getChart() {


		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(0.1, 0.2));
		values.add(new DataPoint(0.3, 0.42));
		values.add(new DataPoint(0.76, 0.89));
		
		XYChart lineChart = new XYChart(values, "Fractions", "X", "Y");
		
		JFrame frame = new JFrame();
		frame.add(lineChart);
		frame.setSize(700, 500);
		frame.setVisible(true);
		
		
		return lineChart;
	}
	
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Fractions();
		t.testChart(t.getChart());
	}
	
}
