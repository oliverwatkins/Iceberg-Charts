package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.bar.GradiantRule;
import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYYChart;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
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
        
        ArrayList<XYDataSeries> temperatureSeriesList = new ArrayList<XYDataSeries>();

        
        
      ArrayList<DataPoint> values = new ArrayList<DataPoint>();
      values.add(new DataPoint("J", 17.2));
      values.add(new DataPoint("F", 21.1));
      values.add(new DataPoint("M", 23.3));
      values.add(new DataPoint("A", 32.2));
      values.add(new DataPoint("M", 30));
      values.add(new DataPoint("J", 35.2));
      values.add(new DataPoint("J", 36.2));
      values.add(new DataPoint("A", 37.1));
      values.add(new DataPoint("S", 30.0));
      values.add(new DataPoint("O", 26.1));
      values.add(new DataPoint("N", 18.8));
      values.add(new DataPoint("D", 20.5));

      ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();
      values2.add(new DataPoint("J", -30.5));
      values2.add(new DataPoint("F", -22.7));
      values2.add(new DataPoint("M", -15.5));
      values2.add(new DataPoint("A", -6.1));
      values2.add(new DataPoint("M", -2.7));
      values2.add(new DataPoint("J", -2.7));
      values2.add(new DataPoint("J", 3.8));
      values2.add(new DataPoint("A", 3.8));
      values2.add(new DataPoint("S", 0));
      values2.add(new DataPoint("O", -6.1));
      values2.add(new DataPoint("N", -14.4));
      values2.add(new DataPoint("D", -21.1));
      
      ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
      values3.add(new DataPoint("J", -2.2));
      values3.add(new DataPoint("F", -0.4));
      values3.add(new DataPoint("M", 3.4));
      values3.add(new DataPoint("A", 7.6));
      values3.add(new DataPoint("M", 12.2));
      values3.add(new DataPoint("J", 15.4));
      values3.add(new DataPoint("J", 17.3));
      values3.add(new DataPoint("A", 16.6));
      values3.add(new DataPoint("S", 13.4));
      values3.add(new DataPoint("O", 8.2));
      values3.add(new DataPoint("N", 2.8));
      values3.add(new DataPoint("D", -0.9));
        
        
        

        
        
        
        
        
        
        
        
        

        XYDataSeries series = new XYDataSeries(new UIPointCircle(Color.RED), 
        		new Line(Color.RED), "max");
        series.dataPoints = values;

        XYDataSeries series2 = new XYDataSeries(new UIPointSquare(Color.BLUE), 
        		new Line(Color.BLUE), "min");
        series2.dataPoints = values2;

        XYDataSeries series3 = new XYDataSeries(new UIPointTriangle(Color.ORANGE), 
        		new Line(Color.ORANGE), "average");
        series3.dataPoints = values3;

//        YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(-50.0, 100.0, 50.0, 10.0, 1.0), "Temperature");
//        XAxis xAxis = new XAxis(new EnumerationAxisScalingX(), "Month");
//        
//        YAxis yAxis2 = new YAxis(new LinearNumericalAxisScalingY(0.0, 200.0, 100.0, 20.0, 0.0), "Precipitation");

        temperatureSeriesList.add(series);
        temperatureSeriesList.add(series2);
        temperatureSeriesList.add(series3);

        GradiantRule rule = new GradiantRule(0, 130, Color.BLUE, Color.RED, 100);
        
        ArrayList<DataPointBar> barSeries = new ArrayList<DataPointBar>();
        barSeries.add(new DataPointBar("J", 54.0, Color.RED));
        barSeries.add(new DataPointBar("F", 45.2, Color.RED));
        barSeries.add(new DataPointBar("M", 60.1, Color.RED));
        barSeries.add(new DataPointBar("A", 69.9, Color.RED));
        barSeries.add(new DataPointBar("M", 93.4, Color.RED));
        barSeries.add(new DataPointBar("J", 123.6, Color.RED));
        barSeries.add(new DataPointBar("J", 117.6, Color.RED));
        barSeries.add(new DataPointBar("A", 114.5, Color.RED));
        barSeries.add(new DataPointBar("S", 90.3, Color.RED));
        barSeries.add(new DataPointBar("O", 69.4, Color.RED));
        barSeries.add(new DataPointBar("N", 71.0, Color.RED));
        barSeries.add(new DataPointBar("D", 58.4, Color.RED ));
        
        
        XYDataSeries rainfallSeries = new XYDataSeries(barSeries, 
        		new UIPointBar(Color.BLACK, 50), 
        		null, 
        		"Rainfall");

        ArrayList<XYDataSeries> rainfallSeriesList = new ArrayList<XYDataSeries>();
        rainfallSeriesList.add(rainfallSeries);

        XYYChart chart = new XYYChart("Munich Weather", 
        		"Month", 
        		"Temperature",
        		"Rainfall", 
        		rainfallSeriesList, 
        		temperatureSeriesList, true);
        
        chart.setSize(1000, 600);
        
        chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
        chart.setTitle("Munich Weather");
        
        return chart;

    }
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_2Y();
		t.testChart(t.getChart());
	}


}
