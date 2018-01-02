package com.frontangle.ichart.main.test.xy;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.DataPointCandleStick;
import com.frontangle.ichart.chart.draw.point.UIPointCandleStick;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;

public class TestDataXY_CandlePlot extends ChartTester {

	
	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	
	public static void main(String[] s) throws ParseException {

		ChartTester t = new TestDataXY_CandlePlot();
		t.testChart(t.getChart());
	}

	public Chart getChart() {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();

		values.add(new DataPointCandleStick(10, 80, 70, 60, 50, true));
		values.add(new DataPointCandleStick(20, 80, 75, 50, 15, true));
		values.add(new DataPointCandleStick(30, 67, 45, 55, 41, false)); // 3rd
																			// has
																			// to
																			// be
																			// less
																			// than
																			// 4th!!
		values.add(new DataPointCandleStick(40, 63, 61, 56, 50, true));
		values.add(new DataPointCandleStick(50, 69, 56, 50, 43, true));
		values.add(new DataPointCandleStick(60, 78, 70, 64, 50, true));
		values.add(new DataPointCandleStick(70, 99, 75, 44, 31, true));
		values.add(new DataPointCandleStick(80, 80, 60, 70, 50, false)); // 3rd
																			// has
																			// to
																			// be
																			// less
																			// than
																			// 4th!!
		values.add(new DataPointCandleStick(90, 80, 75, 50, 25, true));
		values.add(new DataPointCandleStick(100, 80, 70, 60, 50, true));
		values.add(new DataPointCandleStick(110, 80, 75, 50, 15, true));

		XYDataSeries series = new XYDataSeries(values, new UIPointCandleStick(
				new Color(181, 197, 207, 100)), null, "1994");

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(0.0, 100.0,
				50.0, 10.0, null), "y Axis");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(0.0, 100.0,
				50.0, 10.0, null), "x Axis");

		xySeriesList.add(series);

		XYChart chart = new XYChart("","","",xySeriesList, yAxis, xAxis, false);

		chart.setSize(1000, 500);

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("Candle Plot");

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "XY: Candle plot";
	}
}
