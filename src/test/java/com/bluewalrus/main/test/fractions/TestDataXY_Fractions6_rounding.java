package com.bluewalrus.main.test.fractions;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;

public class TestDataXY_Fractions6_rounding extends ChartTester {

	@Override
	public Chart getChart() {

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(0.1, 0.2));
		values.add(new DataPoint(0.3, 0.42));
		values.add(new DataPoint(0.76, 0.89));

		ArrayList<XYDataSeries> s = new ArrayList<XYDataSeries>();

		LinearNumericalAxisScaling lnas = new LinearNumericalAxisScaling(1.001, 1.002,
				new NumericalInterval(5, 0.0001), null, null);
		
		//comment this out to see how it looks before rounding :
		lnas.decimalFormat = new DecimalFormat("##0.0000");
		
		XAxis xa = new XAxis(lnas, "1");
		
		
		YAxis ya = new YAxis(new LinearNumericalAxisScaling(0.0, 100.0,
				new NumericalInterval(5, 10.0), null, null), "2");

		XYChart chart = new XYChart(s, ya, xa);
		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "Fractions 3";
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Fractions6_rounding();
		t.testChart(t.getChart());
	}

}
