package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;

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
public class TestDataBar_BarNegativeAndPositiveNumerical extends ChartTester {
	@Override
	public JPanel getChart() {

		
        ArrayList<Color> colors = Utils.makeGradients(Color.ORANGE, Color.CYAN, 5);
        
        NumericalInterval tick1 = new NumericalInterval(20, 20.0, 
        		new GridLine(Color.GRAY,true,1)); //grid line
        
        NumericalInterval tick2 = new NumericalInterval(10, 10.0, null); //no grid line
        NumericalInterval tick3 = new NumericalInterval(5, 5.0, null);
        
        YAxis yAxis = new YAxis(new LinearNumericalAxisDrawY(-40.0, 100.0, tick1, tick2, tick3), "Number of Fruits");
        yAxis.labelText = "Price USD";
        
        XAxis xAxis = new XAxis(new EnumerationAxisDrawX(), "Commodity");

        ArrayList<DataPointBar> bars = new ArrayList<DataPointBar>();
        bars.add(new DataPointBar(10, 90, colors.get(0)));
        bars.add(new DataPointBar(20, 14, colors.get(1)));
        bars.add(new DataPointBar(30, -7, colors.get(2)));
        bars.add(new DataPointBar(40, 30, colors.get(3)));
        bars.add(new DataPointBar(50, 10, colors.get(4)));
        bars.add(new DataPointBar(60, 30, colors.get(4)));
        bars.add(new DataPointBar(70, 54, colors.get(4)));
        
        BarChart barChart = new BarChart(xAxis, yAxis, bars, 40);
        
        barChart.setTitle("Change In Commodity Price");
        barChart.setSize(800, 200);
        		
        return barChart;
	}
	
}
