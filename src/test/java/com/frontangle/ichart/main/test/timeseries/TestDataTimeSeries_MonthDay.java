package com.frontangle.ichart.main.test.timeseries;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.IntervalStyling;
import com.frontangle.ichart.chart.axis.TimeInterval;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.GridFill;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointCircle;
import com.frontangle.ichart.chart.draw.point.UIPointSquare;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.scaling.TimeSeriesAxisScaling;

public class TestDataTimeSeries_MonthDay extends ChartTester {

	
	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	
	@Override
	public Chart getChart() throws ParseException {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");

		Date startDate = df.parse("2001-01-03 10-00-00"); // 3 Jan
		Date endDate = df.parse("2001-04-7 05-33-00"); // 7 april



		ArrayList<DataPoint> values = createData();

		XYDataSeries series = new XYDataSeries(new UIPointCircle(Color.MAGENTA, 11), new Line(
				Color.MAGENTA, false, 4), "Time Series");
		series.dataPoints = values;

		SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("d");

		TimeInterval timeInt1 = new TimeInterval(2, TimeInterval.Type.MONTH, new Line(Color.GRAY, false, 2), monthFormat);

		timeInt1.styling = new IntervalStyling(10, new Line(Color.BLACK, false, 2), new GridFill(Color.LIGHT_GRAY, Color.WHITE, false));

		TimeInterval timeInt2 = new TimeInterval(2, TimeInterval.Type.DAY, new Line(Color.GRAY, false, 1), dayFormat);
		timeInt2.styling = new IntervalStyling(2, new Line(Color.GRAY, false, 1), null);
		timeInt2.styling.intervalFont = new Font("Blackadder ITC", Font.PLAIN, 20);

		XAxis xAxis = new XAxis(new TimeSeriesAxisScaling(startDate, endDate, timeInt1, timeInt2, null), "Time Series 1");

		XYChart lineChart = new XYChart("Time Series 1", "X", "Y", series);

		lineChart.xAxis = xAxis;
		lineChart.setTitle("Month Day Time Series (3rd Jan - 7th April)");

		timeInt1.styling.intervalFont = new Font("Blackadder ITC", Font.BOLD, 16);
		timeInt2.styling.intervalFont = new Font("Blackadder ITC", Font.PLAIN, 12);

		timeInt1.styling.graphFill = new GridFill(Color.WHITE, new Color(224, 235, 235), false);

		return lineChart;
	}

	private ArrayList<DataPoint> createData() throws ParseException {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		String time = "2001-03-01 00-33-00";
		Date dt4 = df.parse(time);

		time = "2001-04-01 00-33-00";
		Date dt5 = df.parse(time);
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(df.parse("2001-01-03 00-33-00"), 15));
		values.add(new DataPoint(df.parse("2001-01-06 00-33-00"), 25));
		values.add(new DataPoint(df.parse("2001-01-09 00-33-00"), 35));
		values.add(new DataPoint(df.parse("2001-01-12 00-33-00"), 42));
		values.add(new DataPoint(df.parse("2001-01-14 00-33-00"), 36));
		values.add(new DataPoint(df.parse("2001-01-17 00-33-00"), 21));
		values.add(new DataPoint(df.parse("2001-01-22 00-33-00"), 14));
		
		values.add(new DataPoint(df.parse("2001-02-01 00-33-00"),15));
		values.add(new DataPoint(df.parse("2001-02-02 00-33-00"), 25));
		values.add(new DataPoint(df.parse("2001-02-03 00-33-00"), 35));
		values.add(new DataPoint(df.parse("2001-02-04 00-33-00"), 45));
		values.add(new DataPoint(df.parse("2001-02-07 00-33-00"),15));
		values.add(new DataPoint(df.parse("2001-02-08 00-33-00"), 15));
		values.add(new DataPoint(df.parse("2001-02-09 00-33-00"), 27));
		values.add(new DataPoint(df.parse("2001-02-22 00-33-00"), 15));
		
		
		values.add(new DataPoint(df.parse("2001-03-01 00-33-00"), 12));
		values.add(new DataPoint(df.parse("2001-03-03 00-33-00"), 15));
		values.add(new DataPoint(df.parse("2001-03-04 00-33-00"), 23));
		values.add(new DataPoint(df.parse("2001-03-05 00-33-00"), 31));
		values.add(new DataPoint(df.parse("2001-03-07 00-33-00"), 34));
		values.add(new DataPoint(df.parse("2001-03-13 00-33-00"), 25));
		values.add(new DataPoint(df.parse("2001-03-16 00-33-00"), 12));
		values.add(new DataPoint(df.parse("2001-03-18 00-33-00"), 04));

		values.add(new DataPoint(df.parse("2001-04-01 00-33-00"), 01));
		values.add(new DataPoint(df.parse("2001-04-05 00-33-00"), 03));
		
		
		return values;
	}

	@Override
	public String getNiceTitle() {
		return "Time Series: Month Day";
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataTimeSeries_MonthDay();
		t.testChart(t.getChart());
	}
}
