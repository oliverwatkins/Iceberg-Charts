package com.frontangle.ichart.main.test.gridlinefill;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.GridFill;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;

public class TestDataGrids_5_Gradiant extends ChartTester {

	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	@Override
	public Chart getChart() {

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 5));
		values.add(new DataPoint(7, 8));
		values.add(new DataPoint(8, 14));


		NumericalInterval yInterval1 = new NumericalInterval(2, 10.0, new Line(Color.GRAY, false, 1));

		NumericalInterval yInterval2 = new NumericalInterval(1, 1.0, null);
		NumericalInterval yInterval3 = new NumericalInterval(0, 1.0, null);


		NumericalInterval xInterval1 = new NumericalInterval(0, 10.0, new Line(Color.BLACK, false, 0));
		
		xInterval1.styling.graphFill = new GridFill(Color.LIGHT_GRAY, Color.WHITE, true);
		
		NumericalInterval xInterval2 = new NumericalInterval(0, 2.0, new Line(Color.LIGHT_GRAY, false, 1));
		
		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(0.0, 35.0, yInterval1, yInterval2, yInterval3), "Y Axis");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(15.0, 68.0, xInterval1, xInterval2, null), "X Axis");


		XYChart lineChart = new XYChart("Gradiant", "","",values, yAxis, xAxis);

		lineChart.setSize(1000, 500);

		return lineChart;
	}

	@Override
	public String getNiceTitle() {
		return "Grids: Gradient fill";
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataGrids_5_Gradiant();
		t.testChart(t.getChart());
	}
}
