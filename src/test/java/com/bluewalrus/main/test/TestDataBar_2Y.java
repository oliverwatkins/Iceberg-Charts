package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.bar.Bar;
import com.bluewalrus.bar.GradiantRule;
import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYYChart;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.draw.EnumerationAxisScalingX;
import com.bluewalrus.chart.draw.LinearNumericalAxisScalingY;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.point.UIPointCircle;
import com.bluewalrus.point.UIPointSquare;
import com.bluewalrus.point.UIPointTriangle;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_2Y extends ChartTester{



	@Override
	public Chart getChart() {
        
        ArrayList<XYDataSeries> xySeries = new ArrayList<XYDataSeries>();

        ArrayList<DataPoint> values = new ArrayList<DataPoint>();
        values.add(new DataPoint(17.2));
        values.add(new DataPoint(21.1));
        values.add(new DataPoint(23.3));
        values.add(new DataPoint(32.2));
        values.add(new DataPoint(30));
        values.add(new DataPoint(35.2));
        values.add(new DataPoint(36.2));
        values.add(new DataPoint(37.1));
        values.add(new DataPoint(30.0));
        values.add(new DataPoint(26.1));
        values.add(new DataPoint(18.8));
        values.add(new DataPoint(20.5));

        ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();
        values2.add(new DataPoint(-30.5));
        values2.add(new DataPoint(-22.7));
        values2.add(new DataPoint(-15.5));
        values2.add(new DataPoint(-6.1));
        values2.add(new DataPoint(-2.7));
        values2.add(new DataPoint(-2.7));
        values2.add(new DataPoint(3.8));
        values2.add(new DataPoint(3.8));
        values2.add(new DataPoint(0));
        values2.add(new DataPoint(-6.1));
        values2.add(new DataPoint(-14.4));
        values2.add(new DataPoint(-21.1));
        
        ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
        values3.add(new DataPoint(-2.2));
        values3.add(new DataPoint(-0.4));
        values3.add(new DataPoint(3.4));
        values3.add(new DataPoint(7.6));
        values3.add(new DataPoint(12.2));
        values3.add(new DataPoint(15.4));
        values3.add(new DataPoint(17.3));
        values3.add(new DataPoint(16.6));
        values3.add(new DataPoint(13.4));
        values3.add(new DataPoint(8.2));
        values3.add(new DataPoint(2.8));
        values3.add(new DataPoint(-0.9));

        XYDataSeries series = new XYDataSeries(new UIPointCircle(Color.RED), 
        		new Line(Color.RED), "max");
        series.dataPoints = values;

        XYDataSeries series2 = new XYDataSeries(new UIPointSquare(Color.BLUE), 
        		new Line(Color.BLUE), "min");
        series2.dataPoints = values2;

        XYDataSeries series3 = new XYDataSeries(new UIPointTriangle(Color.ORANGE), 
        		new Line(Color.ORANGE), "average");
        series3.dataPoints = values3;

        YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(-50.0, 100.0, 50.0, 10.0, 1.0), "Temperature");
        XAxis xAxis = new XAxis(new EnumerationAxisScalingX(), "Month");
        
        YAxis yAxis2 = new YAxis(new LinearNumericalAxisScalingY(0.0, 200.0, 100.0, 20.0, 0.0), "Precipitation");

        xySeries.add(series);
        xySeries.add(series2);
        xySeries.add(series3);

        GradiantRule rule = new GradiantRule(0, 130, Color.BLUE, Color.RED, 100);
        
        
        ArrayList<DataPoint> barValues = new ArrayList<DataPoint>();
        
        
//      ArrayList<Bar> barSeries = new ArrayList<Bar>();
        barValues.add(new DataPoint(54.0, 1));

        
        barValues.add(new DataPoint(45.2, 2)); //rule, "F"));
        barValues.add(new DataPoint(60.1, 3));
        barValues.add(new DataPoint(69.9, 4));
        barValues.add(new DataPoint(93.4, 5));
        barValues.add(new DataPoint(123.6,6));
        barValues.add(new DataPoint(117.6,7));;
        barValues.add(new DataPoint(114.5,8));
        barValues.add(new DataPoint(90.3, 9));
        barValues.add(new DataPoint(69.4, 10));
        barValues.add(new DataPoint(71.0,11));
        barValues.add(new DataPoint(58.4, 12));
        
//        ArrayList<Bar> barSeries = new ArrayList<Bar>();
//        barSeries.add(new Bar(54.0, rule, "J"));
//        barSeries.add(new Bar(45.2, rule, "F"));
//        barSeries.add(new Bar(60.1, rule, "M"));
//        barSeries.add(new Bar(69.9, rule, "A"));
//        barSeries.add(new Bar(93.4, rule, "M"));
//        barSeries.add(new Bar(123.6, rule, "J"));
//        barSeries.add(new Bar(117.6, rule, "J"));
//        barSeries.add(new Bar(114.5, rule, "A"));
//        barSeries.add(new Bar(90.3, rule, "S"));
//        barSeries.add(new Bar(69.4, rule, "O"));
//        barSeries.add(new Bar(71.0, rule, "N"));
//        barSeries.add(new Bar(58.4, rule, "D"));
        
        ArrayList<XYDataSeries> xySeries2 = new ArrayList<XYDataSeries>();
        
        XYDataSeries series2_2 = new XYDataSeries(new UIPointCircle(Color.RED), 
        		new Line(Color.RED), "max");
        series2_2.dataPoints = barValues;
        
        
        
        xySeries2.add(series2_2);

//        XYYChart chart = new XYYChart(xAxis, yAxis, yAxis2, barSeries, xySeries, 30);
        
        XYYChart chart = new XYYChart("", "", "", xySeries2, xySeries, true);
        

//        chart.width = 1000;
//        chart.height = 600;
        chart.setSize(1000, 600);
        chart.barWidth = 39;
        
        chart.rightOffset = 340;
        
        chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
        chart.setTitle("Munich Weather");
        
        return chart;

    }
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_2Y();
		t.testChart(t.getChart());
	}


}
