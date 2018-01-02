package com.frontangle.ichart.main.test.fractions;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.main.test.ChartTester;

public class TestDataXY_Fractions5 extends ChartTester {
	
	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	@Override
	public Chart getChart() {


		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(0.001, 0.001));
		values.add(new DataPoint(0.002, 0.002));
		values.add(new DataPoint(0.003, 0.003));
		
		ArrayList<XYDataSeries> s = new ArrayList<XYDataSeries>();
				
		XYChart chart = new XYChart("", "", "", values, false);
		
		return chart;
	}
	
	@Override
	public String getNiceTitle()  {
		return "Fractions 5";
	}
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Fractions5();
		t.testChart(t.getChart());
	}
	
}
