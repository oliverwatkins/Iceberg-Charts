package com.bluewalrus.main.test.bar;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.scaling.LinearNumericalAxisScaling;

/**
 * 
 * TODO
 * 
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
        
        YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(0.0, 100.0, 50.0, 10.0, 1.0), "Percent Sunlight");
        
        XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(0.0, 365.0), "yadda yadda");
        
        xAxis.axisScaling.setMaxValue(365.0);
        
        xAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
        xAxis.labelText = "Day of Year"; 
        
        XYChart barChart = new XYChart(values, "Annual Sunlight Variability", "Day of Year", "Percent Sunlight", 1);
        
        barChart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));

        return barChart;
    }

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_thinLines();
		t.testChart(t.getChart());
	}
	
	
	@Override
	public String getNiceTitle()  {
		return "Bar: thin lines";
	}
    


}
