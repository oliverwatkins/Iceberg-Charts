package com.frontangle.ichart.main.test.area;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Area;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.main.test.Showcase;

/**
 * 
 * @author Oliver Watkins
 */
public class TestDataArea_1_Multiple extends ChartTester {

	public static String path = "src\\main\\resources\\showcase\\screenshots\\";

	@Showcase
	public Chart getChart() {
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 96));
		values.add(new DataPoint(58, 43));
		values.add(new DataPoint(101, 90));
		values.add(new DataPoint(135, 67));
		values.add(new DataPoint(150, 70));
		
		ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();
		values2.add(new DataPoint(5, 1));
		values2.add(new DataPoint(58, 63));
		values2.add(new DataPoint(101, 290));
		values2.add(new DataPoint(135, 167));
		values2.add(new DataPoint(150, 80));
		
		ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
		values3.add(new DataPoint(5,6));
		values3.add(new DataPoint(58, 33));
		values3.add(new DataPoint(101, 77));
		values3.add(new DataPoint(135, 123));
		values3.add(new DataPoint(150, 321));
		
		
		XYDataSeries<DataPoint> xyDataSeries = new XYDataSeries<DataPoint>("a");
		xyDataSeries.dataPoints = values;
		xyDataSeries.setArea(new Area(new Color(173, 13, 213, 80)));

		XYDataSeries<DataPoint> xyDataSeries2 = new XYDataSeries<DataPoint>("b");
		xyDataSeries2.dataPoints = values2;
		xyDataSeries2.setArea(new Area(new Color(113, 213, 113, 80)));
		
		XYDataSeries<DataPoint> xyDataSeries3 = new XYDataSeries<DataPoint>("c");
		xyDataSeries3.dataPoints = values3;
		xyDataSeries3.setArea(new Area(new Color(12, 233, 3, 80)));
		
		ArrayList<XYDataSeries> ds = new ArrayList<XYDataSeries>();
		ds.add(xyDataSeries);
		ds.add(xyDataSeries2);
		ds.add(xyDataSeries3);
		
		XYChart chart = new XYChart("Multiple Area with opacity and overlap", "X Axis",
				"Y Axis", ds);

		return chart;
	}


	@org.junit.Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataArea_1_Multiple();
		t.testChart(t.getChart());
	}

	@Override
	public String getNiceTitle() {
		return "Area:Simple";
	}

}
