package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.bar.GridLine;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.draw.LinearNumericalAxisScalingX;
import com.bluewalrus.chart.draw.LinearNumericalAxisScalingY;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointWithMagnitude;
import com.bluewalrus.point.UIPointBubble;

public class TestDataBubble_2_series extends ChartTester {

	@Override
	public Chart getChart() {

        ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

        ArrayList<DataPoint> values = new ArrayList<DataPoint>();
        ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();

        values.add(new DataPointWithMagnitude("A", 15, 12, 40));
        values.add(new DataPointWithMagnitude("B", 40, 20, 50));
        values.add(new DataPointWithMagnitude("C", 90, 89, 110));
        values.add(new DataPointWithMagnitude("D", 210, 110, 110));
        values.add(new DataPointWithMagnitude("E", 300, 130, 24));
        values.add(new DataPointWithMagnitude("F", 350, 140, 80));
        values.add(new DataPointWithMagnitude("G", 370, 150, 20));
        values.add(new DataPointWithMagnitude("H", 400, 160, 120));

        values2.add(new DataPointWithMagnitude("A", 15, 22, 20));
        values2.add(new DataPointWithMagnitude("B", 40, 30, 70));
        values2.add(new DataPointWithMagnitude("C", 90, 99, 60));
        values2.add(new DataPointWithMagnitude("D", 210, 140, 87));
        values2.add(new DataPointWithMagnitude("E", 300, 110, 12));
        values2.add(new DataPointWithMagnitude("F", 350, 175, 76));
        values2.add(new DataPointWithMagnitude("G", 370, 134, 40));
        values2.add(new DataPointWithMagnitude("H", 400, 349, 80));

        XYDataSeries<DataPointWithMagnitude> series = new XYDataSeries<DataPointWithMagnitude>(values, new UIPointBubble(Color.BLUE), null, "Series 1");
        XYDataSeries<DataPointWithMagnitude> series2 = new XYDataSeries<DataPointWithMagnitude>(values2, new UIPointBubble(Color.ORANGE), null, "Series 2");

        NumericalInterval i1 = new NumericalInterval(5, 50.0, new GridLine(Color.GRAY, false, 2));
        NumericalInterval i2 = new NumericalInterval(2, 10.0, new GridLine(Color.LIGHT_GRAY, false, 1));
        
        YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(-44.0, 400.0, i1, i2, null), "Y Axis");
        XAxis xAxis = new XAxis(new LinearNumericalAxisScalingX(-12.0, 400.0, i1, i2, null), "X Axis");

        xySeriesList.add(series);
        xySeriesList.add(series2);

        XYChart chart = new XYChart(xySeriesList, yAxis, xAxis, 15);

        
//        chart.width = 700;
        
        chart.setSize(700, 200);
        
        
//        chart.height = 450;
        chart.rightOffset = 200;

        chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
        chart.setTitle("Two Series Bubble Chart");
        
        chart.setBackground(Color.PINK);

        return chart;
    }
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBubble_2_series();
		t.testChart(t.getChart());
	}
}
