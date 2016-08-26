package com.bluewalrus.main.test.bar;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.bar.BarDisplayOptions;
import com.bluewalrus.chart.bar.XYBarDataSeries;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.main.test.Showcase;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_6_Transparancy extends ChartTester {

	@Showcase
	public Chart getChart() {
		
		XYBarDataSeries barSeries = new XYBarDataSeries();
		barSeries.add(new DataPointBar("J", 37));
		barSeries.add(new DataPointBar("F", 42));
		barSeries.add(new DataPointBar("M", 37));
		barSeries.add(new DataPointBar("A", 39));
		barSeries.add(new DataPointBar("M", 40));
		barSeries.add(new DataPointBar("J", 53));
		barSeries.add(new DataPointBar("J", 80));
		barSeries.add(new DataPointBar("A", 103));
		barSeries.add(new DataPointBar("S", 162));
		barSeries.add(new DataPointBar("O", 180));
		barSeries.add(new DataPointBar("N", 207));

		BarDisplayOptions barDisplayOptions = new BarDisplayOptions();
		barDisplayOptions.color = Color.BLUE;
		barDisplayOptions.transparancy = 0.5f;
		
		barSeries.setUpBarDisplayOptions(barDisplayOptions);
		
		XYChart chart = new XYChart(barSeries, "Refugee Arrivals to Germany", "Month", "Number in '1000s");
		
		chart.clearGraphLines();

        try {
        	Image background = ImageIO.read(new File("src/test/java/com/bluewalrus/main/test/bar/refugees.jpg"));
    		chart.setBackgroundImage(background);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return chart;
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_6_Transparancy();
		t.testChart(t.getChart());
	}
	
	@Override
	public String getNiceTitle()  {
		return "Bar: transparancy";
	}
	

}
