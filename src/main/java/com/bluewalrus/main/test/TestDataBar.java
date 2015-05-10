package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.Axis;
import com.bluewalrus.chart.Axis.AxisType;
import com.bluewalrus.bar.Bar;
import com.bluewalrus.bar.GradiantRule;
import com.bluewalrus.bar.Interval;
import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.MultiBar;
import com.bluewalrus.bar.MultiBar.MultiBarMode;
import com.bluewalrus.bar.Utils;
import com.bluewalrus.chart.XAxis;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.YAxis;
import com.bluewalrus.chart.BarChart;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.MultiBarChart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYYChart;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.point.BarPoint;
import com.bluewalrus.point.CirclePoint;
import com.bluewalrus.point.SquarePoint;
import com.bluewalrus.point.TrianglePoint;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar {

	
    public static Chart getTestData_BarNegativeAndPositive() {
        
        ArrayList<Color> colors = Utils.makeGradients(Color.ORANGE, Color.CYAN, 5);
        
        Interval tick1 = new Interval(20, 20.0, 
        		new Line(Color.GRAY,true,1)); //grid line
        
        Interval tick2 = new Interval(10, 10.0, null); //no grid line
        Interval tick3 = new Interval(5, 5.0, null);
        
        YAxis yAxis = new YAxis(-40.0, 100.0, tick1, tick2, tick3, "Number of Fruits");
        yAxis.label = "Price USD";
        
        XAxis xAxis = new XAxis("Commodity", AxisType.BLANK);

        ArrayList<Bar> bars = new ArrayList<Bar>();
        bars.add(new Bar(90, colors.get(0), "Apple"));
        bars.add(new Bar(14, colors.get(1), "Banana"));
        bars.add(new Bar(-7, colors.get(2), "Barley"));
        bars.add(new Bar(30, colors.get(3), "Rice"));
        bars.add(new Bar(10, colors.get(4), "Wheat"));
        bars.add(new Bar(30, colors.get(4), "Oranges"));
        bars.add(new Bar(54, colors.get(4), "Corn"));
        
        BarChart barChart = new BarChart(xAxis, yAxis, bars, 40);
        
        barChart.title = "Change In Commodity Price";
        barChart.width = 800;
        		
        return barChart;
    }
	
	
    public static BarChart getTestData_FontFun() {
    	
        ArrayList<Color> colors = Utils.makeGradients(Color.RED, Color.BLUE, 12);
        
        ArrayList<Bar> values = new ArrayList<Bar>();
        values.add(new Bar(10, colors.get(0), "1"));
        values.add(new Bar(14, colors.get(1), "2"));
        values.add(new Bar(300, colors.get(2), "3"));
        values.add(new Bar(12, colors.get(3), "4"));
        values.add(new Bar(18, colors.get(4), "5"));
        values.add(new Bar(19, colors.get(5), "6"));
        values.add(new Bar(23, colors.get(6), "7"));
        values.add(new Bar(33, colors.get(7), "8"));
        values.add(new Bar(11, colors.get(8), "9"));
        values.add(new Bar(16, colors.get(9), "10"));
        values.add(new Bar(13, colors.get(10), "11"));
        values.add(new Bar(21, colors.get(11), "12"));

        YAxis yAxis = new YAxis(0.0, 300.0, 50.0, 0.0, 0.0, "Numbers");
        yAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
        yAxis.yFont = new Font("Blackadder ITC", Font.PLAIN, 20);
        
        XAxis xAxis = new XAxis("yadda yadda", AxisType.BLANK);
        
        xAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
        xAxis.xFont = new Font("Blackadder ITC", Font.PLAIN, 20);
        xAxis.label = "Playing with fonts";
        
        BarChart barChart = new BarChart(xAxis, yAxis, values, 38);
        barChart.topOffset = 160;

        barChart.titleFont = new Font("Ravie", Font.PLAIN, 70);
        barChart.title = "Really Big Text";
        barChart.width = 900;

        return barChart;
    }


    public static BarChart getTestData_thinLines() {
        ArrayList<Bar> values = new ArrayList<Bar>();

        for (int i = 0; i < 365; i++) {
            double d = Math.random();
            values.add(new Bar((int) (100 * d), Color.GRAY, ""));
        }

        YAxis yAxis = new YAxis(0.0, 100.0, 50.0, 10.0, 1.0, "Percent Sunlight");
        
        XAxis xAxis = new XAxis("yadda yadda");
        
        xAxis.maxValue = 365.0;
        
        xAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
        xAxis.label = "Day of Year"; 
        
        BarChart barChart = new BarChart(xAxis, yAxis, values, 1);

        barChart.width = 1000;
        barChart.titleFont = new Font("Ariel", Font.PLAIN, 24);
        barChart.title = "Annual Sunlight Variability";

        return barChart;
    }

    
    public static MultiBarChart getTestData_MultiBar_SideBySide() {
        
        ArrayList<MultiBar> ml = new ArrayList<MultiBar>();

        ArrayList<Bar> values = new ArrayList<Bar>();
        values.add(new Bar(50, Color.RED, "Automobile"));
        values.add(new Bar(25, Color.BLUE, "Food Industry"));
        values.add(new Bar(10, Color.GREEN, "Cosmetics"));
        values.add(new Bar(5, Color.ORANGE, "Travel Products"));
        values.add(new Bar(67, Color.GRAY, "Government"));
        
        MultiBar mb1 = new MultiBar(values, "2007", MultiBarMode.STACK_ON_TOP);
        ml.add(mb1);

        ArrayList<Bar> values2 = new ArrayList<Bar>();
        values2.add(new Bar(80, Color.RED, "Automobile"));
        values2.add(new Bar(45, Color.BLUE, "Food Industry"));
        values2.add(new Bar(12, Color.GREEN, "Cosmetics"));
        values2.add(new Bar(14, Color.ORANGE, "Travel Products"));
        values2.add(new Bar(10, Color.GRAY, "Government"));
        
        MultiBar mb2 = new MultiBar(values2, "2008", MultiBarMode.STACK_ON_TOP);
        ml.add(mb2);

        ArrayList<Bar> values3 = new ArrayList<Bar>();
        values3.add(new Bar(70, Color.RED, "Automobile"));
        values3.add(new Bar(45, Color.BLUE, "Food Industry"));
        values3.add(new Bar(3, Color.GREEN, "Cosmetics"));
        values3.add(new Bar(1, Color.ORANGE, "Travel Products"));
        values3.add(new Bar(2, Color.GRAY, "Government"));
        
        MultiBar mb3 = new MultiBar(values3, "2009", MultiBarMode.STACK_ON_TOP);
        ml.add(mb3);
        
        
        YAxis yAxis = new YAxis(0.0, 100.0, 20.0, 10.0, 5.0, "thousand dollars");

        XAxis xAxis = new XAxis("year", Axis.AxisType.BLANK);
        
        MultiBarChart chart = new MultiBarChart(xAxis,yAxis,ml);
        chart.title = "Advertising Revenue By Sector";
        chart.rightOffset = 170;
        chart.width = 600;
        return chart;

    }

    public static MultiBarChart getTestData_MultiBar_Stacked() {
    	
    	
    	
        ArrayList<MultiBar> ml = new ArrayList<MultiBar>();

        ArrayList<Bar> values = new ArrayList<Bar>();
        values.add(new Bar(50, Color.RED, "Automobile"));
        values.add(new Bar(25, Color.BLUE, "Food Industry"));
        values.add(new Bar(10, Color.GREEN, "Cosmetics"));
        values.add(new Bar(5, Color.ORANGE, "Travel Products"));
        values.add(new Bar(67, Color.GRAY, "Government"));
        
        MultiBar mb1 = new MultiBar(values, "2007", MultiBarMode.STACK_ON_TOP);
        ml.add(mb1);

        ArrayList<Bar> values2 = new ArrayList<Bar>();
        values2.add(new Bar(80, Color.RED, "Automobile"));
        values2.add(new Bar(45, Color.BLUE, "Food Industry"));
        values2.add(new Bar(12, Color.GREEN, "Cosmetics"));
        values2.add(new Bar(14, Color.ORANGE, "Travel Products"));
        values2.add(new Bar(10, Color.GRAY, "Government"));
        
        MultiBar mb2 = new MultiBar(values2, "2008", MultiBarMode.STACK_ON_TOP);
        ml.add(mb2);

        ArrayList<Bar> values3 = new ArrayList<Bar>();
        values3.add(new Bar(70, Color.RED, "Automobile"));
        values3.add(new Bar(45, Color.BLUE, "Food Industry"));
        values3.add(new Bar(3, Color.GREEN, "Cosmetics"));
        values3.add(new Bar(1, Color.ORANGE, "Travel Products"));
        values3.add(new Bar(2, Color.GRAY, "Government"));
        
        MultiBar mb3 = new MultiBar(values3, "2009", MultiBarMode.STACK_ON_TOP);
        ml.add(mb3);
        
        
        
        
        
        
        
        
        
        
        
        YAxis yAxis = new YAxis(0.0, 200.0, 50.0, 20.0, 10.0, "thousand dollars");

        XAxis xAxis = new XAxis("Year", Axis.AxisType.BLANK);

        MultiBarChart chart = new MultiBarChart(xAxis,yAxis,ml,true);
//        chart.barWidth = 30;
        chart.title = "Advertising Revenue By Sector";
        chart.rightOffset = 170;
        
        return chart;
    }
    
    
    public static Chart getTestData_Bar2Y() {
        
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

        XYDataSeries series = new XYDataSeries(new CirclePoint(Color.RED), 
        		new Line(Color.RED), "max");
        series.dataPoints = values;

        XYDataSeries series2 = new XYDataSeries(new SquarePoint(Color.BLUE), 
        		new Line(Color.BLUE), "min");
        series2.dataPoints = values2;

        XYDataSeries series3 = new XYDataSeries(new TrianglePoint(Color.ORANGE), 
        		new Line(Color.ORANGE), "average");
        series3.dataPoints = values3;

        YAxis yAxis = new YAxis(-50.0, 100.0, 50.0, 10.0, 1.0, "Temperature");
        XAxis xAxis = new XAxis("Month", AxisType.BLANK);
        
        YAxis yAxis2 = new YAxis(0.0, 200.0, 100.0, 20.0, 0.0, "Precipitation");

        xySeries.add(series);
        xySeries.add(series2);
        xySeries.add(series3);

        GradiantRule rule = new GradiantRule(0, 130, Color.BLUE, Color.RED, 100);
        
        ArrayList<Bar> barSeries = new ArrayList<Bar>();
        barSeries.add(new Bar(54.0, rule, "J"));
        barSeries.add(new Bar(45.2, rule, "F"));
        barSeries.add(new Bar(60.1, rule, "M"));
        barSeries.add(new Bar(69.9, rule, "A"));
        barSeries.add(new Bar(93.4, rule, "M"));
        barSeries.add(new Bar(123.6, rule, "J"));
        barSeries.add(new Bar(117.6, rule, "J"));
        barSeries.add(new Bar(114.5, rule, "A"));
        barSeries.add(new Bar(90.3, rule, "S"));
        barSeries.add(new Bar(69.4, rule, "O"));
        barSeries.add(new Bar(71.0, rule, "N"));
        barSeries.add(new Bar(58.4, rule, "D"));

        XYYChart chart = new XYYChart(xAxis, yAxis, yAxis2, barSeries, xySeries, 30);

        chart.width = 1000;
        chart.height = 600;
        chart.barWidth = 39;
        
        chart.rightOffset = 340;
        
        chart.titleFont = new Font("Ariel", Font.PLAIN, 24);
        chart.title = "Munich Weather";
        
        return chart;

    }
    
    public static Chart getTestData_BarCanBeXY() {
        
        Interval tick1 = new Interval(20, 20.0, new Line(Color.GRAY,true,1)); //grid line
        Interval tick2 = new Interval(10, 10.0, null); //no grid line
        Interval tick3 = new Interval(5, 5.0, null);
        
        YAxis yAxis = new YAxis(-80.0, 100.0, tick1, tick2, tick3, "Y value");
        
        XAxis xAxis = new XAxis(0.0, 25.0, tick1, tick2, tick3, "X Value - must be even spaced");
        
        ArrayList<DataPointBar> bars = new ArrayList<DataPointBar>();
        bars.add(new DataPointBar(1, -60, Color.YELLOW, ""));
        bars.add(new DataPointBar(2, -50, Color.YELLOW, ""));
        bars.add(new DataPointBar(3, -40, Color.YELLOW, ""));
        bars.add(new DataPointBar(4, -30, Color.YELLOW, ""));
        bars.add(new DataPointBar(5, -20, Color.YELLOW, ""));
        bars.add(new DataPointBar(6, -10, Color.YELLOW, ""));
        bars.add(new DataPointBar(7, 0, Color.PINK, ""));
        bars.add(new DataPointBar(8, 10, Color.PINK, ""));
        bars.add(new DataPointBar(9, 20, Color.PINK, ""));
        bars.add(new DataPointBar(10, 30, Color.PINK, "ten"));
        bars.add(new DataPointBar(11, 40, Color.PINK, ""));
        bars.add(new DataPointBar(12, 50, Color.PINK, ""));
        bars.add(new DataPointBar(13, 60, Color.PINK, ""));
        bars.add(new DataPointBar(14, 70, Color.PINK, ""));
        bars.add(new DataPointBar(15, 80, Color.PINK, ""));
        bars.add(new DataPointBar(16, 90, Color.PINK, ""));
        bars.add(new DataPointBar(17, 100, Color.PINK, ""));
        bars.add(new DataPointBar(18, 105, Color.PINK, ""));
        bars.add(new DataPointBar(19, 109, Color.PINK, ""));
        bars.add(new DataPointBar(20, 115, Color.PINK, "twenty"));
        bars.add(new DataPointBar(21, 130, Color.PINK, ""));
        bars.add(new DataPointBar(22, 135, Color.PINK, ""));
        bars.add(new DataPointBar(23, 132, Color.PINK, ""));
        
        XYChart lineChart = new XYChart(xAxis, yAxis);
        
        XYDataSeries<DataPointBar> series = new XYDataSeries<DataPointBar>(
        		new BarPoint(Color.RED, lineChart),
        		null,
        		"");

        series.dataPoints = bars;

        ArrayList<XYDataSeries> a = new ArrayList<XYDataSeries>();
        
        a.add(series);
        
        lineChart.data = a;
        lineChart.title = "Bars Can Be Treated as XY";
        lineChart.width = 800;

        return lineChart;
    }

}
