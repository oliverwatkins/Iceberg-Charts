package com.bluewalrus.main.test.gridlinefill;


import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;

public class TestDataGrids_2_GraphPaper extends ChartTester{
	
	@Override
	public Chart getChart() {

		NumericalInterval yInterval1 = new NumericalInterval(0, 10.0, new Line(Color.BLACK, false, 1));
		NumericalInterval yInterval2 = new NumericalInterval(0, 5.0, new Line(Color.GRAY, false, 1));
		NumericalInterval yInterval3 = new NumericalInterval(0, 1.0, new Line(Color.LIGHT_GRAY, false, 1));

		NumericalInterval xInterval1 = new NumericalInterval(0, 10.0, new Line(Color.BLACK, false, 1));
		NumericalInterval xInterval2 = new NumericalInterval(0, 5.0, new Line(Color.GRAY, false, 1));
		NumericalInterval xInterval3 = new NumericalInterval(0, 1.0, new Line(Color.LIGHT_GRAY, false, 1));

		
		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(0.0, 100.0, yInterval1, yInterval2, yInterval3), "Y Axis");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(0.0, 100.0, xInterval1, xInterval2, xInterval3), "X Axis");

		XYChart chart = new XYChart(new ArrayList<DataPoint>(), "Graphpaper", yAxis, xAxis);

		return chart;
	}
	
	@Override
	public String getNiceTitle() {
		return "Grids: Graph paper";
	}

	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataGrids_2_GraphPaper();
		t.testChart(t.getChart());
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

