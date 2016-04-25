package com.bluewalrus.main.test;

import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.bar.GridLine;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.Utils;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.scaling.EnumerationAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_NegativeAndPositive extends ChartTester {

	@Override
	public Chart getChart() {
        
        ArrayList<Color> colors = Utils.makeGradients(Color.ORANGE, Color.CYAN, 5);
        
        NumericalInterval tick1 = new NumericalInterval(20, 20.0, new GridLine(Color.GRAY,true,1)); //grid line
        
        NumericalInterval tick2 = new NumericalInterval(10, 10.0, null); //no grid line
        NumericalInterval tick3 = new NumericalInterval(5, 5.0, null);
        
        YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(-40.0, 100.0, tick1, tick2, tick3), "Number of Fruits");
        yAxis.labelText = "Price USD";
        
        XAxis xAxis = new XAxis(new EnumerationAxisScalingX(), "Commodity");

        ArrayList<DataPointBar> bars = new ArrayList<DataPointBar>();
        bars.add(new DataPointBar("Apple", 90, colors.get(0)));
        bars.add(new DataPointBar("Banana",14, colors.get(1)));
        bars.add(new DataPointBar("Barley",-7, colors.get(2)));
        bars.add(new DataPointBar( "Rice",30, colors.get(3)));
        bars.add(new DataPointBar( "Wheat",10, colors.get(4)));
        bars.add(new DataPointBar( "Oranges", 30, colors.get(4)));
        bars.add(new DataPointBar( "Corn", 54, colors.get(4)));

//		XYChart lineChart = new XYChart(values, "My Easy Example", "X Axis", "Y Axis");

        XYChart barChart = new XYChart(bars, "My Bars", "X Axis", "Y Axis", 30);
        
        barChart.setTitle("Change In Commodity Price");
        barChart.setSize(800, 200);
        		
        return barChart;
    }
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_NegativeAndPositive();
		t.testChart(t.getChart());
	}

    
    
}
