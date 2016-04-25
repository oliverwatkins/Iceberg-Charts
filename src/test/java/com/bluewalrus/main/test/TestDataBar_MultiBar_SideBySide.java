package com.bluewalrus.main.test;

import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.MultiBarChart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.chart.datapoint.MultiBar;
import com.bluewalrus.chart.datapoint.MultiBar.MultiBarMode;
import com.bluewalrus.scaling.EnumerationAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_MultiBar_SideBySide extends ChartTester {

	@Override
	public Chart getChart() {
        
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
        
        
//        YAxis yAxis = new YAxis(new LinearNumericalAxisDrawY(0.0, 100.0, 20.0, 10.0, 5.0), "thousand dollars");
//
//        XAxis xAxis = new XAxis(new EnumerationAxisDrawX(), "year");
        
        MultiBarChart chart = new MultiBarChart(
        		ml,
        		"Advertising Revenue By Sector",
        		"year",
        		"thousand dollars"
        		);
        
        
//      XYChart barChart = new XYChart(values, "Really Big Text", "yadda yadda", "yadda yadda", 12);        		
//		xAxis,
//		yAxis,
        
        
//        chart.setTitle("Advertising Revenue By Sector");
//        chart.rightOffset = 170;
//        chart.setSize(600, 500);
        return chart;

    }
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_MultiBar_SideBySide();
		t.testChart(t.getChart());
	}
	

}
