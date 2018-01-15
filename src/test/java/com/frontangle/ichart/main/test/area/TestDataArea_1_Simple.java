package com.frontangle.ichart.main.test.area;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.DataPointBar;
import com.frontangle.ichart.chart.draw.Area;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.main.test.Showcase;

/**
 * 
 * @author Oliver Watkins
 */
public class TestDataArea_1_Simple extends ChartTester {

	public static String path = "src\\main\\resources\\showcase\\screenshots\\";

	@Showcase
	public Chart getChart() {
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 96));
		values.add(new DataPoint(58, 43));
		values.add(new DataPoint(101, 90));
		values.add(new DataPoint(135, 67));
		values.add(new DataPoint(150, 70));

		XYDataSeries<DataPoint> xyDataSeries = new XYDataSeries<DataPoint>("");
		xyDataSeries.dataPoints = values;
		xyDataSeries.area = new Area();
		
		XYChart chart = new XYChart("My Easy Example", "X Axis",
				"Y Axis", xyDataSeries);

		return chart;
	}


	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataArea_1_Simple();
		t.testChart(t.getChart());
	}

	@Override
	public String getNiceTitle() {
		return "Area:Simple";
	}

}
