package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.bar.GridLine;
import com.bluewalrus.bar.Utils;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.point.UIPointBar;
import com.bluewalrus.scaling.EnumerationAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_zero_offchart extends ChartTester {

	@Override
	public Chart getChart() {

        
        NumericalInterval tick1 = new NumericalInterval(20, 10.0, new GridLine(Color.GRAY,true,1)); //grid line
        NumericalInterval tick2 = new NumericalInterval(10, 1.0, null); //no grid line
//        Interval tick3 = new Interval(5, 5.0, null);
        
        YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(-14.2, 16.7, tick1, tick2, null), "Y value");
        XAxis xAxis = new XAxis(new LinearNumericalAxisScalingX(0.0, 25.0, tick1, tick2, null), "X Value - must be even spaced");
        
        
        
        ArrayList<DataPointBar> bars = new ArrayList<DataPointBar>();
        bars.add(new DataPointBar(1, -60, Color.YELLOW, ""));
        bars.add(new DataPointBar(2, -50, Color.YELLOW, ""));
        bars.add(new DataPointBar(3, -40, Color.YELLOW, ""));
        bars.add(new DataPointBar(4, -30, Color.YELLOW, ""));
        bars.add(new DataPointBar(5, -20, Color.YELLOW, ""));
        bars.add(new DataPointBar(6, -12, Color.YELLOW, ""));
        bars.add(new DataPointBar(7, 0, Color.PINK, ""));
        bars.add(new DataPointBar(8, 13, Color.PINK, ""));
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
        
        XYDataSeries<DataPoint> series = new XYDataSeries<DataPoint>(
        		new UIPointBar(Color.RED),
        		null,
        		"");

        series.dataPoints = bars;

        ArrayList<XYDataSeries> a = new ArrayList<XYDataSeries>();
        
        a.add(series);
        
        lineChart.data = a;
        lineChart.setTitle("Bars Can Be Treated as XY");
        
        
        lineChart.setSize(800, 400);
//        lineChart.width = 800;

        return lineChart;
    }
	
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_zero_offchart();
		t.testChart(t.getChart());
	}
	

	


}
