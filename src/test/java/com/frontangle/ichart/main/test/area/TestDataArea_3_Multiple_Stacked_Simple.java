package com.frontangle.ichart.main.test.area;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Area;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.main.test.Showcase;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;

/**
 * 
 * @author Oliver Watkins
 */
public class TestDataArea_3_Multiple_Stacked_Simple extends ChartTester {

	public static String path = "src\\main\\resources\\showcase\\screenshots\\";

	@Showcase
	public Chart getChart() {
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 20));
		values.add(new DataPoint(10, 30));
		values.add(new DataPoint(15, 5));

		
		ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();
		values2.add(new DataPoint(5, 10));
		values2.add(new DataPoint(10, 50));
		values2.add(new DataPoint(15, 5));
		
		
		ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
		values3.add(new DataPoint(5,10));
		values3.add(new DataPoint(10, 50));
		values3.add(new DataPoint(15, 70));

		
		
		XYDataSeries<DataPoint> xyDataSeries = new XYDataSeries<DataPoint>("alabama");
		xyDataSeries.dataPoints = values;
		xyDataSeries.setArea(new Area(new Color(173, 13, 213, 80),Area.AreaType.STACKED));

		XYDataSeries<DataPoint> xyDataSeries2 = new XYDataSeries<DataPoint>("maryland");
		xyDataSeries2.dataPoints = values2;
		xyDataSeries2.setArea(new Area(new Color(113, 213, 113, 80),Area.AreaType.STACKED));
		
		XYDataSeries<DataPoint> xyDataSeries3 = new XYDataSeries<DataPoint>("california");
		xyDataSeries3.dataPoints = values3;
		xyDataSeries3.setArea(new Area(new Color(12, 233, 3, 80),Area.AreaType.STACKED));
		
		ArrayList<XYDataSeries> ds = new ArrayList<XYDataSeries>();
		ds.add(xyDataSeries);
		ds.add(xyDataSeries2);
		ds.add(xyDataSeries3);
		
		XYChart chart = new XYChart("Multiple Area with opacity and overlap", "X Axis",
				"Y Axis", ds);
		LinearNumericalAxisScaling scaling = new LinearNumericalAxisScaling(0, 200);
		scaling.interval1 = new NumericalInterval(1, new Double(20), new Line(Color.GRAY));
		chart.yAxis = new YAxis(scaling, "");

		return chart;
	}


	@org.junit.Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataArea_3_Multiple_Stacked_Simple();
		t.testChart(t.getChart());
	}

	@Override
	public String getNiceTitle() {
		return "Area:Simple";
	}

}
