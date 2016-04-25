package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.Utils;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.scaling.EnumerationAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_FontFun extends ChartTester {

	@Override
	public Chart getChart() {

    	
        ArrayList<Color> colors = Utils.makeGradients(Color.RED, Color.BLUE, 12);
        
        ArrayList<DataPointBar> values = new ArrayList<DataPointBar>();
        values.add(new DataPointBar("One", 10, colors.get(0)));
        values.add(new DataPointBar("Two", 14, colors.get(1)));
        values.add(new DataPointBar("Three", 12, colors.get(3)));
        values.add(new DataPointBar("Four", 1, colors.get(4)));
        values.add(new DataPointBar("Five", 18, colors.get(5)));
        values.add(new DataPointBar("Six", 40, colors.get(6)));        
        
        XYChart barChart = new XYChart(values, "Really Big Text", "yadda yadda", "yadda yadda", 40);
        
        barChart.xAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
        barChart.xAxis.font = new Font("Blackadder ITC", Font.PLAIN, 20);
        barChart.xAxis.labelText = "Playing with fonts";

        barChart.yAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
        barChart.yAxis.font = new Font("Blackadder ITC", Font.PLAIN, 20);
        
        barChart.setTitleFont("Ravie", Font.PLAIN, 70);
        barChart.setTitle("Really Big Text");
        barChart.setSize(900, 500);

        return barChart;
    }
	
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_FontFun();
		t.testChart(t.getChart());
	}
	

	


}
