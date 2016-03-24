package com.bluewalrus.main.test;


import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bluewalrus.bar.GridFill;
import com.bluewalrus.bar.GridLine;
import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.draw.LinearNumericalAxisScalingX;
import com.bluewalrus.chart.draw.LinearNumericalAxisScalingY;
import com.bluewalrus.chart.draw.TimeSeriesAxisScalingX;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.point.UIPointSquare;

public class TestDataGrids_Gradiant extends ChartTester{
	
	@Override
	public Chart getChart() {
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 5));
		values.add(new DataPoint(7, 8));
		values.add(new DataPoint(8, 14));

		NumericalInterval yInterval1 = new NumericalInterval(2, 10.0, new GridLine(Color.GRAY, false, 1));
		NumericalInterval yInterval2 = new NumericalInterval(1, 1.0, null);
		NumericalInterval yInterval3 = new NumericalInterval(0, 1.0, null);

		NumericalInterval xInterval1 = new NumericalInterval(0, 10.0, new GridLine(Color.BLACK, false, 0));
		
		xInterval1.styling.graphFill = new GridFill(Color.LIGHT_GRAY, Color.WHITE, true);
		
		NumericalInterval xInterval2 = new NumericalInterval(0, 2.0, new GridLine(Color.LIGHT_GRAY, false, 1));
		
		YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(0.0, 35.0, yInterval1, yInterval2, yInterval3), "Y Axis");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScalingX(15.0, 68.0, xInterval1, xInterval2, null), "X Axis");


		XYChart lineChart = new XYChart(values, "Gradiant", yAxis, xAxis);

		lineChart.setSize(1000, 500);

		return lineChart;
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataGrids_Gradiant();
		t.testChart(t.getChart());
	}
	
	
	
	
	
	
	
	
}

