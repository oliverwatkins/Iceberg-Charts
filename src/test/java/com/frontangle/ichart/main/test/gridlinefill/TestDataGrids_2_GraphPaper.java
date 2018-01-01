package com.frontangle.ichart.main.test.gridlinefill;


import java.awt.Color;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;

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

