package com.bluewalrus.main.test.threaded;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.StackedXYChart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.IntervalStyling;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointCandleStick;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointBar;
import com.bluewalrus.chart.draw.point.UIPointCandleStick;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;

public class TestStackedChart_threaded extends ChartTester {
	
	public ArrayList<XYChart> charts = new ArrayList<XYChart>();
	
	
	public Chart getChart() {

		//xAxis needs to be shared
		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(), "X");
		xAxis.axisScaling.setMinValue(0);
		xAxis.axisScaling.setMaxValue(400);
		

		charts.add(getCandleChart(xAxis));
		charts.add(getBarChart(xAxis));
		
		ArrayList<Integer> percentages = new ArrayList<Integer>();
		percentages.add(70);
		percentages.add(30);
		
		StackedXYChart stackedXYChart = new StackedXYChart("Candlestick Chart", charts, percentages);
		
		return stackedXYChart;
	}
	
	
	public XYChart getBarChart(XAxis xAxis) {
		
		ArrayList<XYDataSeries> list = new ArrayList<XYDataSeries>();
		
		ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
		values3.add(new DataPoint(10, 74));
		values3.add(new DataPoint(20, 55));
		values3.add(new DataPoint(30, 55));
		values3.add(new DataPoint(40, 22));
		values3.add(new DataPoint(50, 33));
		values3.add(new DataPoint(60, 44));
		values3.add(new DataPoint(70, 42));
		values3.add(new DataPoint(80, 41));
		values3.add(new DataPoint(90, 25));
		values3.add(new DataPoint(100, 22));
		values3.add(new DataPoint(110, 26));
		values3.add(new DataPoint(120, 23));
		values3.add(new DataPoint(130, 34));
		values3.add(new DataPoint(140, 67));
		values3.add(new DataPoint(150, 76));
		values3.add(new DataPoint(160, 76));
		values3.add(new DataPoint(170, 32));
		values3.add(new DataPoint(180, 67));
		values3.add(new DataPoint(190, 66));
		values3.add(new DataPoint(200, 22));
		values3.add(new DataPoint(210, 25));
		values3.add(new DataPoint(220, 32));
		values3.add(new DataPoint(230, 33));
		values3.add(new DataPoint(240, 13));
		values3.add(new DataPoint(250, 11));
		values3.add(new DataPoint(260, 13));
		values3.add(new DataPoint(270, 62));
		values3.add(new DataPoint(280, 41));
		values3.add(new DataPoint(290, 26));
		values3.add(new DataPoint(300, 21));
		values3.add(new DataPoint(310, 51));
		values3.add(new DataPoint(320, 14));
		values3.add(new DataPoint(330, 12));
		values3.add(new DataPoint(340, 22));
		values3.add(new DataPoint(350, 23));
		values3.add(new DataPoint(360, 26));
		values3.add(new DataPoint(370, 21));
		values3.add(new DataPoint(380, 45));
		values3.add(new DataPoint(390, 22));
		values3.add(new DataPoint(400, 22));
		
		XYDataSeries series3 = new XYDataSeries(values3, "Third");

		series3.pointType = new UIPointBar(new Color(153, 153, 102), 60);
		
		list = new ArrayList<XYDataSeries>();
		list.add(series3);
		
		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(), "Trade Volume");
		yAxis.axisScaling.setMinValue(0);
		yAxis.axisScaling.setMaxValue(120);
		
		yAxis.axisScaling.interval2.styling = new IntervalStyling(3, new Line(Color.LIGHT_GRAY, true), null);
		
		XYChart lineChart2 = new XYChart(xAxis, yAxis); 
		lineChart2.data = list;
		
		return lineChart2;
	}
	
	public XYChart getCandleChart(XAxis xAxis) {


		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();

		values.add(new DataPointCandleStick(10, 83, 70, 60, 50, true));
		values.add(new DataPointCandleStick(20, 90, 75, 50, 15, true));
		values.add(new DataPointCandleStick(30, 87, 45, 55, 41, false)); //3rd has to be less than 4th!!
		values.add(new DataPointCandleStick(40, 83, 61, 56, 50, true));
		values.add(new DataPointCandleStick(50, 89, 56, 50, 43, true));
		values.add(new DataPointCandleStick(60, 78, 70, 64, 50, true));
		values.add(new DataPointCandleStick(70, 99, 75, 44, 31, true));
		values.add(new DataPointCandleStick(80, 80, 60, 70, 50, false)); //3rd has to be less than 4th!!
		values.add(new DataPointCandleStick(90, 99, 75, 50, 25, true));
		values.add(new DataPointCandleStick(100, 93, 70, 60, 50, true));
		values.add(new DataPointCandleStick(110, 95, 71, 55, 44, true));
		values.add(new DataPointCandleStick(120, 94, 67, 53, 41, true));
		values.add(new DataPointCandleStick(130, 81, 65, 51, 42, true));
		values.add(new DataPointCandleStick(140, 85, 61, 41, 31, true));
		values.add(new DataPointCandleStick(150, 81, 61, 44, 35, true));
		values.add(new DataPointCandleStick(160, 55, 53, 50, 49, true));
		values.add(new DataPointCandleStick(170, 71, 48, 41, 15, true));
		values.add(new DataPointCandleStick(180, 70, 41, 36, 15, true));
		values.add(new DataPointCandleStick(190, 68, 75, 50, 15, true));
		values.add(new DataPointCandleStick(200, 67, 95, 60, 55, true));
		values.add(new DataPointCandleStick(210, 73, 77, 66, 52, true));
		values.add(new DataPointCandleStick(220, 75, 74, 50, 15, true));
		values.add(new DataPointCandleStick(230, 77, 45, 55, 41, false)); //3rd has to be less than 4th!!
		values.add(new DataPointCandleStick(240, 78, 45, 56, 35, false)); //3rd has to be less than 4th!!
		values.add(new DataPointCandleStick(250, 75, 56, 50, 43, true));
		values.add(new DataPointCandleStick(260, 78, 70, 64, 50, true));
		values.add(new DataPointCandleStick(270, 99, 75, 44, 31, true));
		values.add(new DataPointCandleStick(280, 80, 60, 70, 50, false)); //3rd has to be less than 4th!!
		values.add(new DataPointCandleStick(290, 77, 51, 41, 25, true));
		values.add(new DataPointCandleStick(300, 41, 31, 13, 11, true));
		values.add(new DataPointCandleStick(310, 35, 31, 17, 15, true));
		values.add(new DataPointCandleStick(320, 14, 12, 9, 5, true));
		values.add(new DataPointCandleStick(330, 17, 13, 11, 2, true));
		values.add(new DataPointCandleStick(340, 16, 11, 10, 9, true));
		values.add(new DataPointCandleStick(350, 19, 15, 13, 11, true));
		values.add(new DataPointCandleStick(360, 23, 21, 20, 15, true));
		values.add(new DataPointCandleStick(370, 41, 40, 31, 30, true));
		values.add(new DataPointCandleStick(380, 54, 51, 50, 49, true));
		values.add(new DataPointCandleStick(390, 67, 56, 50, 17, true));
		values.add(new DataPointCandleStick(400, 63, 61, 50, 15, true));

		XYDataSeries series = new XYDataSeries(values, new UIPointCandleStick(
				new Color(255, 51, 0)), null, "1994");

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(0.0, 100.0, 50.0, 10.0, null), "Price (USD)");

		yAxis.axisScaling.interval1.styling = new IntervalStyling(5, new Line(Color.LIGHT_GRAY, true), null);
		yAxis.axisScaling.interval2.styling = new IntervalStyling(2, new Line(Color.LIGHT_GRAY, true), null);

		xySeriesList.add(series);

		XYChart chart = new XYChart(xySeriesList, yAxis, xAxis);

		chart.setSize(1000, 500);
		chart.rightOffset = 200;

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("Box Plot");

		return chart;
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestStackedChart_threaded();
		t.testChart(t.getChart());
	}


	@Override
	public String getNiceTitle() {
		// TODO Auto-generated method stub
		return "??";
	}
}
