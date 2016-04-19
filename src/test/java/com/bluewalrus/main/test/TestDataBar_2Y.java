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
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.point.UIPointBar;
import com.bluewalrus.point.UIPointCircle;
import com.bluewalrus.point.UIPointSquare;
import com.bluewalrus.point.UIPointTriangle;
import com.bluewalrus.scaling.EnumerationAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_2Y extends ChartTester{



	@Override
	public Chart getChart() {
        
        ArrayList<XYDataSeries> xySeries = new ArrayList<XYDataSeries>();

        ArrayList<DataPoint> values = new ArrayList<DataPoint>();
        values.add(new DataPoint(1, 17.2));
        values.add(new DataPoint(2, 21.1));
        values.add(new DataPoint(3, 23.3));
        values.add(new DataPoint(4, 32.2));
        values.add(new DataPoint(5, 30));
        values.add(new DataPoint(6, 35.2));
        values.add(new DataPoint(7, 36.2));
        values.add(new DataPoint(8, 37.1));
        values.add(new DataPoint(9, 30.0));
        values.add(new DataPoint(10, 26.1));
        values.add(new DataPoint(11, 18.8));
        values.add(new DataPoint(12, 20.5));

        ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();
        values2.add(new DataPoint(1, -30.5));
        values2.add(new DataPoint(2, -22.7));
        values2.add(new DataPoint(3, -15.5));
        values2.add(new DataPoint(4, -6.1));
        values2.add(new DataPoint(5, -2.7));
        values2.add(new DataPoint(6, -2.7));
        values2.add(new DataPoint(7, 3.8));
        values2.add(new DataPoint(8, 3.8));
        values2.add(new DataPoint(9, 0));
        values2.add(new DataPoint(10, -6.1));
        values2.add(new DataPoint(11, -14.4));
        values2.add(new DataPoint(12, -21.1));
        
        ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
        values3.add(new DataPoint(1, -2.2));
        values3.add(new DataPoint(2, -0.4));
        values3.add(new DataPoint(3, 3.4));
        values3.add(new DataPoint(4, 7.6));
        values3.add(new DataPoint(5, 12.2));
        values3.add(new DataPoint(6, 15.4));
        values3.add(new DataPoint(7, 17.3));
        values3.add(new DataPoint(8, 16.6));
        values3.add(new DataPoint(9, 13.4));
        values3.add(new DataPoint(10, 8.2));
        values3.add(new DataPoint(11, 2.8));
        values3.add(new DataPoint(12, -0.9));

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
        barValues.add(new DataPoint(1,54.0));
        barValues.add(new DataPoint(2,45.2)); //rule, "F"));
        barValues.add(new DataPoint(3,60.1));
        barValues.add(new DataPoint(4,69.9));
        barValues.add(new DataPoint(5,93.4));
        barValues.add(new DataPoint(6,123.6));
        barValues.add(new DataPoint(7,117.6));;
        barValues.add(new DataPoint(8,114.5));
        barValues.add(new DataPoint(9,90.3));
        barValues.add(new DataPoint(10,69.4));
        barValues.add(new DataPoint(11,71.0));
        barValues.add(new DataPoint(12,58.4));
        
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
        
        XYDataSeries series2_2 = new XYDataSeries(new UIPointBar(Color.PINK), 
        		null, "Rainfall");
        series2_2.dataPoints = barValues;
        
        
        
        xySeries2.add(series2_2);

//        XYYChart chart = new XYYChart(xAxis, yAxis, yAxis2, barSeries, xySeries, 30);
        
        XYYChart chart = new XYYChart("Munich Weather", "Month", "Temperature" ,"Rainfall", xySeries2, xySeries, true);
        

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
