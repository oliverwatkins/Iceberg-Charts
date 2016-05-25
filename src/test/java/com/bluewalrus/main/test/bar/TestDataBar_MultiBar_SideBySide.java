package com.bluewalrus.main.test.bar;

import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.chart.datapoint.DataPointMultiBar;
import com.bluewalrus.chart.datapoint.DataPointMultiBar.MultiBarMode;
import com.bluewalrus.chart.draw.point.UIPointMultiBarSideBySide;
import com.bluewalrus.main.test.ChartTester;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_MultiBar_SideBySide extends ChartTester {

	@Override
	public Chart getChart() {
        
        ArrayList<DataPointMultiBar> multiBarList = new ArrayList<DataPointMultiBar>();

        ArrayList<DataPointBar> values = new ArrayList<DataPointBar>();
        values.add(new DataPointBar("Automobile", 51, Color.RED));
        values.add(new DataPointBar("Food Industry", 25, Color.BLUE ));
        values.add(new DataPointBar("Cosmetics", 10, Color.GREEN));
        values.add(new DataPointBar("Travel Products", 5, Color.ORANGE ));
        values.add(new DataPointBar("Government", 67, Color.GRAY ));
        
        DataPointMultiBar mb1 = new DataPointMultiBar(values, "2007", MultiBarMode.SIDE_BY_SIDE);
        multiBarList.add(mb1);

        ArrayList<DataPointBar> values2 = new ArrayList<DataPointBar>();
        values2.add(new DataPointBar("Automobile", 80, Color.RED ));
        values2.add(new DataPointBar("Food Industry", 45, Color.BLUE ));
        values2.add(new DataPointBar("Cosmetics", 12, Color.GREEN ));
        values2.add(new DataPointBar("Travel Products", 14, Color.ORANGE ));
        values2.add(new DataPointBar("Government", 10, Color.GRAY ));
        
        DataPointMultiBar mb2 = new DataPointMultiBar(values2, "2008", MultiBarMode.SIDE_BY_SIDE);
        multiBarList.add(mb2);

        ArrayList<DataPointBar> values3 = new ArrayList<DataPointBar>();
        values3.add(new DataPointBar("Automobile", 5, Color.RED ));
        values3.add(new DataPointBar("Food Industry", 4, Color.BLUE ));
        values3.add(new DataPointBar("Cosmetics",  3, Color.GREEN ));
        values3.add(new DataPointBar("Travel Products", 1, Color.ORANGE ));
        values3.add(new DataPointBar("Government", 2, Color.GRAY));
        
        DataPointMultiBar mb3 = new DataPointMultiBar(values3, "2009", MultiBarMode.SIDE_BY_SIDE);
        multiBarList.add(mb3);

        
        ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

        XYDataSeries series = new XYDataSeries(multiBarList, new UIPointMultiBarSideBySide(), null, "1994");

        xySeriesList.add(series);
        
        XYChart chart = new XYChart("Side by Side", "Year", "y", xySeriesList);
        
        return chart;

    }
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_MultiBar_SideBySide();
		t.testChart(t.getChart());
	}
	

}
