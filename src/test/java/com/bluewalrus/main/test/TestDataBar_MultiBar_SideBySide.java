package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.bar.Bar;
import com.bluewalrus.bar.GradiantRule;
import com.bluewalrus.bar.GridLine;
import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.Utils;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.BarChart;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.MultiBarChart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYYChart;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.draw.EnumerationAxisDrawX;
import com.bluewalrus.chart.draw.LinearNumericalAxisDrawX;
import com.bluewalrus.chart.draw.LinearNumericalAxisDrawY;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.datapoint.MultiBar;
import com.bluewalrus.datapoint.MultiBar.MultiBarMode;
import com.bluewalrus.point.UIPointBar;
import com.bluewalrus.point.UIPointCircle;
import com.bluewalrus.point.UIPointSquare;
import com.bluewalrus.point.UIPointTriangle;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_MultiBar_SideBySide {

	


    
    public static MultiBarChart getTestData_MultiBar_SideBySide() {
        
        ArrayList<MultiBar> ml = new ArrayList<MultiBar>();

        ArrayList<DataPointBar> values = new ArrayList<DataPointBar>();
        values.add(new DataPointBar("Automobile", 51, Color.RED));
        values.add(new DataPointBar("Food Industry", 25, Color.BLUE ));
        values.add(new DataPointBar("Cosmetics", 10, Color.GREEN));
        values.add(new DataPointBar("Travel Products", 5, Color.ORANGE ));
        values.add(new DataPointBar("Government", 67, Color.GRAY ));
        
        MultiBar mb1 = new MultiBar(values, "2007", MultiBarMode.STACK_ON_TOP);
        ml.add(mb1);

        ArrayList<DataPointBar> values2 = new ArrayList<DataPointBar>();
        values2.add(new DataPointBar("Automobile", 80, Color.RED ));
        values2.add(new DataPointBar("Food Industry", 45, Color.BLUE ));
        values2.add(new DataPointBar("Cosmetics", 12, Color.GREEN ));
        values2.add(new DataPointBar("Travel Products", 14, Color.ORANGE ));
        values2.add(new DataPointBar("Government", 10, Color.GRAY ));
        
        MultiBar mb2 = new MultiBar(values2, "2008", MultiBarMode.STACK_ON_TOP);
        ml.add(mb2);

        ArrayList<DataPointBar> values3 = new ArrayList<DataPointBar>();
        values3.add(new DataPointBar("Automobile", 70, Color.RED ));
        values3.add(new DataPointBar("Food Industry", 45, Color.BLUE ));
        values3.add(new DataPointBar("Cosmetics",  3, Color.GREEN ));
        values3.add(new DataPointBar("Travel Products", 1, Color.ORANGE ));
        values3.add(new DataPointBar("Government", 2, Color.GRAY));
        
        MultiBar mb3 = new MultiBar(values3, "2009", MultiBarMode.STACK_ON_TOP);
        ml.add(mb3);
        
        
        YAxis yAxis = new YAxis(new LinearNumericalAxisDrawY(0.0, 100.0, 20.0, 10.0, 5.0), "thousand dollars");

        XAxis xAxis = new XAxis(new EnumerationAxisDrawX(), "year");
        
        MultiBarChart chart = new MultiBarChart(xAxis,yAxis,ml);
        chart.setTitle("Advertising Revenue By Sector");
        chart.rightOffset = 170;
        chart.setSize(600, 500);
        return chart;

    }

}
