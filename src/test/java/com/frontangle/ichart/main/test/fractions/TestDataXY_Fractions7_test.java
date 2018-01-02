package com.frontangle.ichart.main.test.fractions;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointCircle;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;

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
		
		XYChart chart = new XYChart("","","",s, ya, xa, false);
		
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
