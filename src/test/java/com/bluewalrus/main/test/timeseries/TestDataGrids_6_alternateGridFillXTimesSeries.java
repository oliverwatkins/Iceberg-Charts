package com.bluewalrus.main.test.timeseries;

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
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.GridFill;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointSquare;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;

public class TestDataGrids_6_alternateGridFillXTimesSeries extends ChartTester {

	@Override
	public Chart getChart() throws ParseException {

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");

		Date startDate = df.parse("2001-05-3 01-00-00");
		Date endDate = df.parse("2001-07-12 05-33-00");

		String time = "2001-03-01 00-33-00";
		Date dt4 = df.parse(time);

		time = "2001-04-01 00-33-00";
		Date dt5 = df.parse(time);

		time = "2001-07-20 05-33-00";
		Date dt6 = df.parse(time);

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(dt4, 5));
		values.add(new DataPoint(dt5, 8));
		values.add(new DataPoint(dt6, 14));


		XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.BLUE), new Line(Color.BLUE), "Something Blue");

		series.dataPoints = values;


		NumericalInterval yInterval = new NumericalInterval(8, 10.0, new Line(Color.GRAY, false, 1));

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(-90.0, 100.0,
				yInterval, null, null), "Y Axis");

		GridFill gf = new GridFill(new Color(179, 209, 255), Color.RED, false);

		
		NumericalInterval x1 = new NumericalInterval(2, 
				5.0, new Line(Color.GRAY, true, 2));


		x1.styling.graphFill = gf;

		
		
		NumericalInterval x2 = new NumericalInterval(6, 
				20.0, new Line(Color.GRAY, false, 3));


		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(3.0, 97.0, x1,
				x2, null), "Time Series");

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
		ChartTester t = new TestDataGrids_6_alternateGridFillXTimesSeries();
		t.testChart(t.getChart());
	}

}
