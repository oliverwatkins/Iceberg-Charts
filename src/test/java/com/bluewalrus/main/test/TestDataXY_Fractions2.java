package com.bluewalrus.main.test;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.scaling.LinearNumericalAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;

public class TestDataXY_Fractions2 extends ChartTester {
	
	@Override
	public Chart getChart() {


		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(0.1, 0.2));
		values.add(new DataPoint(0.3, 0.42));
		values.add(new DataPoint(0.76, 0.89));
		
		ArrayList<XYDataSeries> s = new ArrayList<XYDataSeries>();
		
		XAxis xa = new XAxis(
				new LinearNumericalAxisScalingX(-1.3, 3.0, 
					new NumericalInterval(5, 0.1), 
					null, 
					null), 
				"1");
		YAxis ya = new YAxis(new LinearNumericalAxisScalingY(0.0,100.0,
					new NumericalInterval(5, 10.0), 
					null, 
					null), 				
				"2");
		
		XYChart lineChart = new XYChart(s, ya, xa);
		
		lineChart.setTitle("Negative To Positive");
		
		JFrame frame = new JFrame();
		frame.add(lineChart);
		frame.setSize(700, 500);
		frame.setVisible(true);
		
		
		return lineChart;
	}
	
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Fractions2();
		t.testChart(t.getChart());
	}
	
}
