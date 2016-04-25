package com.bluewalrus.main.test;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.scaling.LinearNumericalAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;

public class TestDataXY_Fractions extends ChartTester {
	
	@Override
	public Chart getChart() {


		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(0.1, 0.2));
		values.add(new DataPoint(0.3, 0.42));
		values.add(new DataPoint(0.76, 0.89));
		
		ArrayList<XYDataSeries> s = new ArrayList<XYDataSeries>();
		
		XAxis xa = new XAxis(
				new LinearNumericalAxisScalingX(1.505, 3.0, 
					new NumericalInterval(5, 0.1), 
					null, 
					null), 
				"1");
		YAxis ya = new YAxis(new LinearNumericalAxisScalingY(0.0,100.0,
					new NumericalInterval(50, 10.0), 
					null, 
					null), 				
				"2");
		
		
//		ya.axisCatFont = new Font("Arial", Font.PLAIN, 30);
		
		XYChart lineChart = new XYChart(s, ya, xa);
	
		lineChart.setTitle("(range 1.5005 to 3 with 0.1 interval)");
		
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
