package com.bluewalrus.main.test;


import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.bar.GridFill;
import com.bluewalrus.chart.bar.GridLine;
import com.bluewalrus.chart.bar.Line;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.point.UIPointSquare;
import com.bluewalrus.scaling.LinearNumericalAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;
import com.bluewalrus.scaling.TimeSeriesAxisScalingX;

public class TestDataGrids_GraphPaper extends ChartTester{
	
	@Override
	public Chart getChart() {

		NumericalInterval yInterval1 = new NumericalInterval(0, 10.0, new GridLine(Color.BLACK, false, 1));
		NumericalInterval yInterval2 = new NumericalInterval(0, 5.0, new GridLine(Color.GRAY, false, 1));
		NumericalInterval yInterval3 = new NumericalInterval(0, 1.0, new GridLine(Color.LIGHT_GRAY, false, 1));

		NumericalInterval xInterval1 = new NumericalInterval(0, 10.0, new GridLine(Color.BLACK, false, 1));
		NumericalInterval xInterval2 = new NumericalInterval(0, 5.0, new GridLine(Color.GRAY, false, 1));
		NumericalInterval xInterval3 = new NumericalInterval(0, 1.0, new GridLine(Color.LIGHT_GRAY, false, 1));

		
		YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(0.0, 100.0, yInterval1, yInterval2, yInterval3), "Y Axis");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScalingX(0.0, 100.0, xInterval1, xInterval2, xInterval3), "X Axis");

		XYChart lineChart = new XYChart(new ArrayList<DataPoint>(), "Graphpaper", yAxis, xAxis);

		return lineChart;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataGrids_GraphPaper();
		t.testChart(t.getChart());
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

