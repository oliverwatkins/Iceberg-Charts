package com.bluewalrus.main.test.bubble;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointWithMagnitude;
import com.bluewalrus.chart.draw.point.UIPointBubble;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;

public class TestDataBubble_1_guns extends ChartTester {
	@Override
	public Chart getChart() {

		System.out.println("getTestData_Bubble2");

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();

		// homicide
		values.add(new DataPointWithMagnitude("Alabama", 51.70, 2.8, 4779736));
		values.add(new DataPointWithMagnitude("Alaska", 57.80, 2.7, 710231));
		values.add(new DataPointWithMagnitude("Arizona", 31.10, 3.6, 6392017));
		values.add(new DataPointWithMagnitude("Arkansas", 55.30, 3.2, 2915918));
		values.add(new DataPointWithMagnitude("California", 21.30, 3.4,
				37253956));
		values.add(new DataPointWithMagnitude("Colorado", 34.70, 1.3, 5029196));
		values.add(new DataPointWithMagnitude("Connecticut", 16.70, 2.7,
				3574097));
		values.add(new DataPointWithMagnitude("Delaware", 25.50, 4.2, 897934));
		values.add(new DataPointWithMagnitude("District of Columbia", 3.60,
				16.5, 601723));
		values.add(new DataPointWithMagnitude("Florida", 24.50, 3.9, 19687653));
		values.add(new DataPointWithMagnitude("Georgia", 40.30, 3.8, 9920000));
		values.add(new DataPointWithMagnitude("Hawaii", 6.70, 0.5, 1360301));
		values.add(new DataPointWithMagnitude("Idaho", 55.30, 0.8, 1567582));
		values.add(new DataPointWithMagnitude("Illinois", 20.20, 2.8, 12830632));
		values.add(new DataPointWithMagnitude("Indiana", 39.10, 2.2, 6483802));
		values.add(new DataPointWithMagnitude("Iowa", 42.90, 0.7, 3046355));
		values.add(new DataPointWithMagnitude("Kansas", 42.10, 2.2, 2853118));
		values.add(new DataPointWithMagnitude("Kentucky", 47.70, 2.7, 4339367));
		values.add(new DataPointWithMagnitude("Louisiana", 44.10, 7.7, 4533372));
		values.add(new DataPointWithMagnitude("Maine", 40.50, 0.8, 1328361));
		values.add(new DataPointWithMagnitude("Maryland", 21.30, 5.1, 5773552));
		values.add(new DataPointWithMagnitude("Massachusetts", 12.60, 1.8,
				6547629));
		values.add(new DataPointWithMagnitude("Michigan", 38.40, 4.2, 9883640));
		values.add(new DataPointWithMagnitude("Minnesota", 41.70, 1, 5303925));
		values.add(new DataPointWithMagnitude("Mississippi", 55.30, 4, 2967297));
		values.add(new DataPointWithMagnitude("Missouri", 41.70, 5.4, 5988927));
		values.add(new DataPointWithMagnitude("Montana", 57.70, 1.2, 989415));
		values.add(new DataPointWithMagnitude("Nebraska", 38.60, 1.8, 1826341));
		values.add(new DataPointWithMagnitude("Nevada", 33.80, 3.1, 2700551));
		values.add(new DataPointWithMagnitude("New Hampshire", 30.00, 0.4,
				1316470));
		values.add(new DataPointWithMagnitude("New Jersey", 12.30, 2.8, 8791894));
		values.add(new DataPointWithMagnitude("New Mexico", 34.80, 3.3, 2059179));
		values.add(new DataPointWithMagnitude("New York", 18, 2.7, 19378102));
		values.add(new DataPointWithMagnitude("North Carolina", 41.30, 3,
				9535483));
		values.add(new DataPointWithMagnitude("North Dakota", 50.70, 0.6,
				672591));
		values.add(new DataPointWithMagnitude("Ohio", 32.40, 2.7, 11536504));
		values.add(new DataPointWithMagnitude("Oklahoma", 42.90, 3, 3751351));
		values.add(new DataPointWithMagnitude("Oregon", 39.80, 0.9, 3831074));
		values.add(new DataPointWithMagnitude("Pennsylvania", 34.70, 3.6,
				12702379));
		values.add(new DataPointWithMagnitude("Rhode Island", 12.80, 1.5,
				1052567));
		values.add(new DataPointWithMagnitude("South Carolina", 42.30, 4.5,
				4625364));
		values.add(new DataPointWithMagnitude("South Dakota", 56.60, 1, 814180));
		values.add(new DataPointWithMagnitude("Tennessee", 43.90, 2.5, 6346105));
		values.add(new DataPointWithMagnitude("Texas", 35.90, 3.2, 25145561));
		values.add(new DataPointWithMagnitude("Utah", 43.90, 0.8, 2763885));
		values.add(new DataPointWithMagnitude("Vermont", 42.00, 0.3, 625741));
		values.add(new DataPointWithMagnitude("Virginia", 35.10, 3.1, 8001024));
		values.add(new DataPointWithMagnitude("Washington", 33.10, 1.4, 6724540));
		values.add(new DataPointWithMagnitude("West Virginia", 55.40, 1.5,
				1852994));
		values.add(new DataPointWithMagnitude("Wisconsin", 44.40, 1.7, 5686986));
		values.add(new DataPointWithMagnitude("Wyoming", 59.70, 0.9, 563626));

		XYDataSeries series = new XYDataSeries(values, new UIPointBubble(
				Color.BLUE), null, "1994");

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(0.0, 10.0, 1.0,
				null, null), "No. of Homicides per 100,000 ");
		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(0.0, 100.0,
				50.0, 10.0, null), "Gun Ownership (%)");

		xySeriesList.add(series);

		double factor = 0.000005;

		XYChart chart = new XYChart(xySeriesList, yAxis, xAxis, factor);

		chart.setSize(900, 650);

		chart.rightOffset = 200;

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("Gun Ownership versus Homicide Rate");

		return chart;
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBubble_1_guns();
		t.testChart(t.getChart());
	}

	@Override
	public String getNiceTitle() {
		return "Bubble: Single series";
	}

}
