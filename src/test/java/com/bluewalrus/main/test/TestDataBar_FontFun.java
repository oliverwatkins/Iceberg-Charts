package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.bar.Utils;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.draw.EnumerationAxisDrawX;
import com.bluewalrus.chart.draw.LinearNumericalAxisDrawY;
import com.bluewalrus.datapoint.DataPointBar;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_FontFun extends ChartTester {

	@Override
	public Chart getChart() {

    	
        ArrayList<Color> colors = Utils.makeGradients(Color.RED, Color.BLUE, 12);
        
        ArrayList<DataPointBar> values = new ArrayList<DataPointBar>();
        values.add(new DataPointBar("asdf", 10, colors.get(0)));
        values.add(new DataPointBar("asdf", 14, colors.get(1)));
        values.add(new DataPointBar("asdf", 12, colors.get(3)));
        values.add(new DataPointBar("asdf", 1, colors.get(4)));
        values.add(new DataPointBar("asdf", 18, colors.get(5)));
        values.add(new DataPointBar("asdf", 40, colors.get(6)));
//        values.add(new Bar(33, colors.get(7), "8"));
//        values.add(new Bar(11, colors.get(8), "9"));
//        values.add(new Bar(16, colors.get(9), "10"));
//        values.add(new Bar(13, colors.get(10), "11"));
//        values.add(new Bar(21, colors.get(11), "12"));


        
        
        
        
//        YAxis yAxis = new YAxis(new LinearNumericalAxisDrawY(0.0, 300.0, 50.0, 0.0, 0.0), "Numbers");

        
//        XAxis xAxis = new XAxis(new EnumerationAxisDrawX(), "yadda yadda");
        
        
        XYChart barChart = new XYChart(values, "Really Big Text", "yadda yadda", "yadda yadda", 40);
        
        barChart.xAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
        barChart.xAxis.font = new Font("Blackadder ITC", Font.PLAIN, 20);
        barChart.xAxis.labelText = "Playing with fonts";
//        barChart.topOffset = 160;

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
