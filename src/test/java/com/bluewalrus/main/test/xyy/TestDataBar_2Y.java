package com.bluewalrus.main.test.xyy;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.bar.BarDisplayOptions;
import com.bluewalrus.chart.bar.GradiantRule;
import com.bluewalrus.chart.bar.XYBarDataSeries;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointCircle;
import com.bluewalrus.chart.draw.point.UIPointSquare;
import com.bluewalrus.chart.draw.point.UIPointTriangle;
import com.bluewalrus.main.test.ChartTester;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_2Y extends ChartTester {

	@Override
	public Chart getChart() {

		ArrayList<XYDataSeries> temperatureSeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> tempMax = new ArrayList<DataPoint>();
		tempMax.add(new DataPoint("J", 17.2));
		tempMax.add(new DataPoint("F", 21.1));
		tempMax.add(new DataPoint("M", 23.3));
		tempMax.add(new DataPoint("A", 32.2));
		tempMax.add(new DataPoint("M", 30));
		tempMax.add(new DataPoint("J", 35.2));
		tempMax.add(new DataPoint("J", 36.2));
		tempMax.add(new DataPoint("A", 37.1));
		tempMax.add(new DataPoint("S", 30.0));
		tempMax.add(new DataPoint("O", 26.1));
		tempMax.add(new DataPoint("N", 18.8));
		tempMax.add(new DataPoint("D", 20.5));

		ArrayList<DataPoint> tempMin = new ArrayList<DataPoint>();
		tempMin.add(new DataPoint("J", -30.5));
		tempMin.add(new DataPoint("F", -22.7));
		tempMin.add(new DataPoint("M", -15.5));
		tempMin.add(new DataPoint("A", -6.1));
		tempMin.add(new DataPoint("M", -2.7));
		tempMin.add(new DataPoint("J", -2.7));
		tempMin.add(new DataPoint("J", 3.8));
		tempMin.add(new DataPoint("A", 3.8));
		tempMin.add(new DataPoint("S", 0));
		tempMin.add(new DataPoint("O", -6.1));
		tempMin.add(new DataPoint("N", -14.4));
		tempMin.add(new DataPoint("D", -21.1));

		ArrayList<DataPoint> tempAvg = new ArrayList<DataPoint>();
		tempAvg.add(new DataPoint("J", -2.2));
		tempAvg.add(new DataPoint("F", -0.4));
		tempAvg.add(new DataPoint("M", 3.4));
		tempAvg.add(new DataPoint("A", 7.6));
		tempAvg.add(new DataPoint("M", 12.2));
		tempAvg.add(new DataPoint("J", 15.4));
		tempAvg.add(new DataPoint("J", 17.3));
		tempAvg.add(new DataPoint("A", 16.6));
		tempAvg.add(new DataPoint("S", 13.4));
		tempAvg.add(new DataPoint("O", 8.2));
		tempAvg.add(new DataPoint("N", 2.8));
		tempAvg.add(new DataPoint("D", -0.9));

		XYDataSeries series = new XYDataSeries(new UIPointCircle(Color.RED),
				new Line(Color.RED), "max");
		series.dataPoints = tempMax;

		XYDataSeries series2 = new XYDataSeries(new UIPointSquare(Color.BLUE),
				new Line(Color.BLUE), "min");
		series2.dataPoints = tempMin;

		XYDataSeries series3 = new XYDataSeries(new UIPointTriangle(
				Color.ORANGE), new Line(Color.ORANGE), "average");
		series3.dataPoints = tempAvg;

		temperatureSeriesList.add(series);
		temperatureSeriesList.add(series2);
		temperatureSeriesList.add(series3);

		ArrayList<DataPointBar> barSeries = new ArrayList<DataPointBar>();
		barSeries.add(new DataPointBar("J", 54.0)); 
		barSeries.add(new DataPointBar("F", 45.2));
		barSeries.add(new DataPointBar("M", 60.1)); 
		barSeries.add(new DataPointBar("A", 69.9)); 
		barSeries.add(new DataPointBar("M", 93.4)); 
		barSeries.add(new DataPointBar("J", 123.6)); 
		barSeries.add(new DataPointBar("J", 117.6)); 
		barSeries.add(new DataPointBar("A", 114.5)); 
		barSeries.add(new DataPointBar("S", 90.3)); 
		barSeries.add(new DataPointBar("O", 69.4));
		barSeries.add(new DataPointBar("N", 71.0)); 
		barSeries.add(new DataPointBar("D", 58.4)); 

		BarDisplayOptions bdo = new BarDisplayOptions();

		
		XYBarDataSeries rainfallSeries = new XYBarDataSeries(barSeries,
				bdo, null, "Rainfall");
		
		bdo.setGradiantRule(new GradiantRule(40, 100, new Color(230, 242, 255), Color.BLUE));
		
		rainfallSeries.setUpBarDisplayOptions(bdo);

		ArrayList<XYDataSeries> rainfallSeriesList = new ArrayList<XYDataSeries>();
		rainfallSeriesList.add(rainfallSeries);

		XYChart chart = new XYChart("Munich Weather", "Month", "Temperature",
				"Rainfall", rainfallSeriesList, temperatureSeriesList);

		chart.yAxis.axisScaling.interval1.styling.graphLine = null;
		chart.yAxis.axisScaling.interval2.styling.graphLine = null;

		chart.setSize(1000, 600);

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("Munich Weather");

		return chart;
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_2Y();
		t.testChart(t.getChart());
	}

	@Override
	public String getNiceTitle() {
		return "XYY";
	}
}
