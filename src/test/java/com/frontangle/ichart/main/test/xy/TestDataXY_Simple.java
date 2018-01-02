package com.frontangle.ichart.main.test.xy;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.main.test.ChartTester;

public class TestDataXY_Simple extends ChartTester {

	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	
	@Override
	public Chart getChart() {

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 96));
		values.add(new DataPoint(58, 43));
		values.add(new DataPoint(101, 90));
		values.add(new DataPoint(135, 67));
		values.add(new DataPoint(150, 70));

		XYChart chart = new XYChart("My Easy Example", "X Axis",
				"Y Axis", values, false);

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "XY: Simple";
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Simple();
		t.testChart(t.getChart());
	}

}
