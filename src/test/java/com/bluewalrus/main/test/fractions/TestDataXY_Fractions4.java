package com.bluewalrus.main.test.fractions;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.main.test.ChartTester;

public class TestDataXY_Fractions4 extends ChartTester {
	
	@Override
	public Chart getChart() {


		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(0.1, 0.2));
		values.add(new DataPoint(0.3, 0.42));
		values.add(new DataPoint(0.76, 0.1));
		
		ArrayList<XYDataSeries> s = new ArrayList<XYDataSeries>();
				
		XYChart chart = new XYChart(values, "Big Range", "", "");
		
		return chart;
	}
	
	@Override
	public String getNiceTitle()  {
		return "Fractions 4";
	}
	
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Fractions4();
		t.testChart(t.getChart());
	}
	
}
