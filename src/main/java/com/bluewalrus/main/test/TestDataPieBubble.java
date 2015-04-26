/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluewalrus.main.test;

import com.bluewalrus.bar.XAxis;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.bar.YAxis;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.PieBubbleChart;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointPieChart;
import com.bluewalrus.pie.Segment;
import com.bluewalrus.point.PieChartPoint;
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
        al.add(new Segment(55, Color.RED));
        al.add(new Segment(34, Color.PINK));
        al.add(new Segment(10, Color.BLUE));
        al.add(new Segment(1, Color.ORANGE));

        
        DataPointPieChart dppc = new DataPointPieChart(al, 10, 20, 30, "One ");
        
        
        
        values.add(dppc);
        
        
        
        
        
        
        
        al = new ArrayList<Segment>();
        al.add(new Segment(5, Color.RED));
        al.add(new Segment(55, Color.PINK));
        al.add(new Segment(30, Color.BLUE));
        al.add(new Segment(10, Color.ORANGE));
        
        dppc = new DataPointPieChart(al, 40, 50, 40, "Two ");
//        
        values.add(dppc);
//
        
        al = new ArrayList<Segment>();
        al.add(new Segment(33, Color.RED));
        al.add(new Segment(33, Color.PINK));
        al.add(new Segment(34, Color.BLUE));
        
        dppc = new DataPointPieChart(al, 60, 70, 15, "Three ");
        
        values.add(dppc);
        
        XYDataSeries series = new XYDataSeries(values, new PieChartPoint(Color.BLUE),
                null, "1994");

        YAxis yAxis = new YAxis(0.0, 100.0, 10.0, null, null, "YYY ");
        XAxis xAxis = new XAxis(0.0, 100.0, 50.0, 10.0, null, "XXX ");

        xySeriesList.add(series);

        double factor = 1;

        PieBubbleChart chart = new PieBubbleChart(xySeriesList, yAxis, xAxis, factor);

        chart.width = 800;
        chart.height = 600;

        chart.rightOffset = 200;

        chart.titleFont = new Font("Ariel", Font.PLAIN, 24);
        chart.title = "asdfasdf";

        return chart;
    }
}
