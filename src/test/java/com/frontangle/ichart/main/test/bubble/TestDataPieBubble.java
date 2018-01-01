/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontangle.ichart.main.test.bubble;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.PieBubbleChart;
import com.frontangle.ichart.chart.PieBubbleChartSettings;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.DataPointPieChart;
import com.frontangle.ichart.chart.draw.point.UIPointPieChart;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.pie.Segment;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;

/**
 *
 * @author oliver
 */
public class TestDataPieBubble extends ChartTester {
	@Override
	public Chart getChart() {

		System.out.println("getTestData_Bubble2");

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();

		ArrayList<Segment> al = new ArrayList<Segment>();

		al = new ArrayList<Segment>();
		al.add(new Segment(2, Color.RED));
		al.add(new Segment(40, Color.CYAN));
		al.add(new Segment(8, Color.BLUE));
		al.add(new Segment(25, Color.ORANGE));
		al.add(new Segment(5, Color.BLACK));
		al.add(new Segment(5, Color.GRAY));
		al.add(new Segment(15, Color.PINK));

		DataPointPieChart dppc = new DataPointPieChart(al, 40, 40, 41.4,
				"Argentina ");

		values.add(dppc);

		al = new ArrayList<Segment>();
		al.add(new Segment(40, Color.RED));
		al.add(new Segment(30, Color.CYAN));
		al.add(new Segment(20, Color.BLUE));
		al.add(new Segment(10, Color.ORANGE));

		dppc = new DataPointPieChart(al, 40, 50, 23, "Australia ");

		values.add(dppc);

		al = new ArrayList<Segment>();
		al.add(new Segment(4, Color.RED));
		al.add(new Segment(9, Color.CYAN));
		al.add(new Segment(30, Color.BLUE));
		al.add(new Segment(10, Color.ORANGE));
		al.add(new Segment(30, Color.BLACK));
		al.add(new Segment(1, Color.GRAY));
		al.add(new Segment(16, Color.PINK));

		dppc = new DataPointPieChart(al, 100, 100, 11.2, "Belgium ");

		values.add(dppc);

		al = new ArrayList<Segment>();
		al.add(new Segment(2, Color.RED));
		al.add(new Segment(2, Color.CYAN));
		al.add(new Segment(2, Color.BLUE));
		al.add(new Segment(2, Color.ORANGE));
		al.add(new Segment(2, Color.BLACK));
		al.add(new Segment(80, Color.GRAY));
		al.add(new Segment(10, Color.PINK));

		dppc = new DataPointPieChart(al, 95, 120, 0.7, "Bhutan ");

		values.add(dppc);

		al = new ArrayList<Segment>();
		// al.add(new Segment(2, Color.RED));
		al.add(new Segment(15, Color.CYAN));
		al.add(new Segment(5, Color.BLUE));
		al.add(new Segment(10, Color.ORANGE));
		// al.add(new Segment(2, Color.BLACK));
		// al.add(new Segment(80, Color.GRAY));
		al.add(new Segment(70, Color.PINK));

		dppc = new DataPointPieChart(al, 60, 70, 4.2, "Croatia ");

		values.add(dppc);

		PieBubbleChartSettings pbcs = new PieBubbleChartSettings();

		pbcs.frontalTransparancy = 100;
		pbcs.setRadialColorStartingPoint1(Color.WHITE);
		pbcs.setRadialColorStartingPoint2(Color.GREEN);

		XYDataSeries<DataPoint> series = new XYDataSeries<DataPoint>(values,
				new UIPointPieChart(Color.BLUE, pbcs), null, "1994");


		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(0.0, 100.0,
				10.0, null, null), "GNI ");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(0.0, 100.0,
				50.0, 10.0, null), "Life Expectancy ");


		xySeriesList.add(series);

		double factor = 1;

		PieBubbleChart chart = new PieBubbleChart(xySeriesList, yAxis, xAxis,
				factor, pbcs);

		chart.setSize(800, 600);

		chart.rightOffset = 200;

		chart.setTitle("Country Export Data");
		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "Bubble: Pie Bubble";
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataPieBubble();
		t.testChart(t.getChart());
	}
}
