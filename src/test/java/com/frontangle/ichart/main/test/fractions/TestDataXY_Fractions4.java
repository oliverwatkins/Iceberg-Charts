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

public class TestDataXY_Fractions4 extends ChartTester {
	
	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	@Override
	public Chart getChart() {


		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(0.1, 0.2));
		values.add(new DataPoint(0.3, 0.42));
		values.add(new DataPoint(0.76, 0.1));
		
		ArrayList<XYDataSeries> s = new ArrayList<XYDataSeries>();
				
		XYChart chart = new XYChart("Big Range", "x", "y", values, false );
		
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
