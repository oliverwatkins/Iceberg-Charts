/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluewalrus.main.test;

import com.bluewalrus.chart.XAxis;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.PieBubbleChart;
import com.bluewalrus.chart.PieBubbleChartSettings;
import com.bluewalrus.chart.YAxis;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointPieChart;
import com.bluewalrus.pie.Segment;
import com.bluewalrus.point.UIPointPieChart;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

/**
 *
 * @author oliver
 */
public class TestDataPieBubble {

    public static Chart getTestData_Bubble2() {

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

        DataPointPieChart dppc = new DataPointPieChart(al, 40, 40, 41.4, "Argentina ");

        values.add(dppc);

        al = new ArrayList<Segment>();
        al.add(new Segment(40, Color.RED));
        al.add(new Segment(30, Color.CYAN));
        al.add(new Segment(20, Color.BLUE));
        al.add(new Segment(10, Color.ORANGE));
//        al.add(new Segment(0, Color.BLACK));
//        al.add(new Segment(0, Color.GRAY));
//        al.add(new Segment(0, Color.PINK));

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
//        al.add(new Segment(2, Color.RED));
        al.add(new Segment(15, Color.CYAN));
        al.add(new Segment(5, Color.BLUE));
        al.add(new Segment(10, Color.ORANGE));
//        al.add(new Segment(2, Color.BLACK));
//        al.add(new Segment(80, Color.GRAY));
        al.add(new Segment(70, Color.PINK));        

        dppc = new DataPointPieChart(al, 60, 70, 4.2, "Croatia ");

        values.add(dppc);
        
        
        

        PieBubbleChartSettings pbcs = new PieBubbleChartSettings();

        pbcs.frontalTransparancy = 100;
        pbcs.setRadialColorStartingPoint1(Color.WHITE);
        pbcs.setRadialColorStartingPoint2(Color.GREEN);

        XYDataSeries series = new XYDataSeries(values, new UIPointPieChart(Color.BLUE, pbcs), null, "1994");

        YAxis yAxis = new YAxis(0.0, 100.0, 10.0, null, null, "GNI ");
        XAxis xAxis = new XAxis(0.0, 100.0, 50.0, 10.0, null, "Life Expectancy ");

        xySeriesList.add(series);

        double factor = 1;

//        PieBubbleChart.RadialGradientSettings.frontalTransparancy = 100;
        PieBubbleChart chart = new PieBubbleChart(xySeriesList, yAxis, xAxis, factor, pbcs);

        chart.width = 800;
        chart.height = 600;

        chart.rightOffset = 200;

        chart.titleFont = new Font("Ariel", Font.PLAIN, 24);
        chart.title = "Country Export Data";

        return chart;
    }
}
