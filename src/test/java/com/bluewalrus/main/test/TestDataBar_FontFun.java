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
public class TestDataBar_FontFun extends ChartTester {

	@Override
	public Chart getChart() {

    	
        ArrayList<Color> colors = Utils.makeGradients(Color.RED, Color.BLUE, 12);
        
        ArrayList<DataPointBar> values = new ArrayList<DataPointBar>();
        values.add(new DataPointBar(1, 10, colors.get(0)));
        values.add(new DataPointBar(2, 14, colors.get(1)));
//        values.add(new Bar(12, colors.get(3), "4"));
//        values.add(new Bar(18, colors.get(4), "5"));
//        values.add(new Bar(19, colors.get(5), "6"));
//        values.add(new Bar(23, colors.get(6), "7"));
//        values.add(new Bar(33, colors.get(7), "8"));
//        values.add(new Bar(11, colors.get(8), "9"));
//        values.add(new Bar(16, colors.get(9), "10"));
//        values.add(new Bar(13, colors.get(10), "11"));
//        values.add(new Bar(21, colors.get(11), "12"));


        
        
        
        
        YAxis yAxis = new YAxis(new LinearNumericalAxisDrawY(0.0, 300.0, 50.0, 0.0, 0.0), "Numbers");
        yAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
        yAxis.font = new Font("Blackadder ITC", Font.PLAIN, 20);
        
        XAxis xAxis = new XAxis(new EnumerationAxisDrawX(), "yadda yadda");
        
        xAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
        xAxis.font = new Font("Blackadder ITC", Font.PLAIN, 20);
        xAxis.labelText = "Playing with fonts";
        
        BarChart barChart = new BarChart(xAxis, yAxis, values, 38);
        barChart.topOffset = 160;

        barChart.setTitleFont("Ravie", Font.PLAIN, 70);
        barChart.setTitle("Really Big Text");
        barChart.setSize(900, 500);

        return barChart;
    }

	


}
