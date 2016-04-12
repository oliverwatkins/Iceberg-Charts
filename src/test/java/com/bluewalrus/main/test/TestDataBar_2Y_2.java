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

public class TestDataBar_2Y_2  extends ChartTester{



	@Override
	public Chart getChart() {
        
        ArrayList<XYDataSeries<?>> xySeries = new ArrayList<XYDataSeries<?>>();

        ArrayList<DataPoint> values = new ArrayList<DataPoint>();
        values.add(new DataPoint(17.2));
        values.add(new DataPoint(21.1));
        values.add(new DataPoint(23.3));

        ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();
        values2.add(new DataPoint(-30.5));
        values2.add(new DataPoint(-22.7));
        values2.add(new DataPoint(-15.5));

        ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
        values3.add(new DataPoint(-2.2));
        values3.add(new DataPoint(-0.4));
        values3.add(new DataPoint(3.4));
        values3.add(new DataPoint(7.6));


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
        
        ArrayList<DataPoint> y2series1 = new ArrayList<DataPoint>();
        values3.add(new DataPoint(3.4));
        values3.add(new DataPoint(7.6));
        values3.add(new DataPoint(3.6));
        
        ArrayList<DataPoint> y2series2 = new ArrayList<DataPoint>();
        values3.add(new DataPoint(3.4));
        values3.add(new DataPoint(7.6));
        values3.add(new DataPoint(3.6));
        
        
        
        ArrayList<XYDataSeries<?>> xySeriesY2 = new ArrayList<XYDataSeries<?>>();
        
        XYDataSeries seriesY2 = new XYDataSeries(new UIPointCircle(Color.RED), 
        		new Line(Color.RED), "max");
        series.dataPoints = values;

        XYDataSeries series2Y2 = new XYDataSeries(new UIPointSquare(Color.BLUE), 
        		new Line(Color.BLUE), "min");
        series2.dataPoints = values2;
        xySeriesY2.add(seriesY2);
        xySeriesY2.add(series2Y2);
        
        

        XYYChart chart = new XYYChart(xAxis, yAxis, yAxis2, xySeries, xySeriesY2, true);

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
