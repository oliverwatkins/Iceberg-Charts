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
public class TestDataBar_thinLines extends ChartTester {

	@Override
	public Chart getChart() {
        ArrayList<DataPointBar> values = new ArrayList<DataPointBar>();

        for (int i = 0; i < 365; i++) {
            double d = Math.random();
            values.add(new DataPointBar("", (int) (100 * d), Color.GRAY));
        }
        
        
        

        YAxis yAxis = new YAxis(new LinearNumericalAxisDrawY(0.0, 100.0, 50.0, 10.0, 1.0), "Percent Sunlight");
        
        XAxis xAxis = new XAxis(new LinearNumericalAxisDrawX(0.0, 365.0), "yadda yadda");
        
        System.out.println("xAxis.axisDraw " + xAxis.axisDraw);
        System.out.println("xAxis.axisDraw.maxValue " + xAxis.axisDraw.maxValue);
        xAxis.axisDraw.maxValue = 365.0;
        
        xAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
        xAxis.labelText = "Day of Year"; 
        
        
        
        
        
        BarChart barChart = new BarChart(xAxis, yAxis, values, 1);

        barChart.setSize(1000, 500);
        barChart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
        barChart.setTitle("Annual Sunlight Variability");

        return barChart;
    }

    


}
