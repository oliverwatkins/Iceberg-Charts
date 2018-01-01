package com.frontangle.ichart.main.test.timeseries;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.axis.TimeInterval;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointSquare;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;
import com.frontangle.ichart.scaling.TimeSeriesAxisScaling;

public class TestDataGrids_7_TimeSeries extends ChartTester {

	@Override
	public Chart getChart() throws ParseException {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");

		Date startDate = df.parse("1997-01-01 01-00-00");
		Date endDate = df.parse("2002-01-20 05-33-00");

		String time = "1985-01-01 00-33-00";
		Date dt4 = df.parse(time);

		time = "1992-01-01 00-33-00";
		Date dt5 = df.parse(time);

		time = "1999-07-20 05-33-00";
		Date dt6 = df.parse(time);

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(dt4, 5));
		values.add(new DataPoint(dt5, 8));
		values.add(new DataPoint(dt6, 14));


		XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.BLUE), new Line(Color.BLUE), "Something Blue");
		series.dataPoints = values;

		NumericalInterval t1 = new NumericalInterval(6, 50.0, new Line(Color.GRAY, false, 1));
		NumericalInterval t2 = new NumericalInterval(3, 10.0, new Line(Color.LIGHT_GRAY, true, 1));
		NumericalInterval t3 = new NumericalInterval(1, 5.0, null);

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(-90.0, 100.0,
				t1, t2, t3), "Y Axis");

		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("");
		
		TimeInterval timeInt1 = new TimeInterval(7, TimeInterval.Type.YEAR, new Line(Color.GRAY, false, 6), yearFormat);
		TimeInterval timeInt2 = new TimeInterval(2, TimeInterval.Type.MONTH, new Line(Color.GRAY, false, 3), monthFormat);
		TimeInterval timeInt3 = new TimeInterval(2, TimeInterval.Type.NONE, new Line(Color.GRAY, false, 1));
		
		
//		
		
		XAxis xAxis = new XAxis(new TimeSeriesAxisScaling(startDate, endDate, timeInt1, timeInt2, timeInt3), "Time Series"); //timeInt2, timeInt3), "Time Series");

		xySeriesList.add(series);

		XYChart chart = new XYChart(xySeriesList, yAxis, xAxis);

		chart.setSize(1000, 500);
		chart.rightOffset = 200;

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("Some Kind of XY Chart");

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "Time Series: ???";
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataGrids_7_TimeSeries();
		t.testChart(t.getChart());
	}

}
