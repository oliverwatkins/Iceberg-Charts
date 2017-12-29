package com.bluewalrus.main.test.bar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.ParseException;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.bar.XYBarDataSeries;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.main.test.Showcase;


public class Scoreboard extends ChartTester{
	XYBarDataSeries barSeries;
	DataPointBar b1,b2,b3, b4;
	int MaxValue = 10;
	XYChart chart;

	@Showcase
	public Chart getChart() {

		barSeries = new XYBarDataSeries();

		b1 = new DataPointBar("MH", 0, Color.red);
		barSeries.add(b1);

		b2 = new DataPointBar("HO", 0, Color.green);
		barSeries.add(b2);
		
		b3 = new DataPointBar("HH", 0, Color.blue);
		barSeries.add(b3);
		
		b4 = new DataPointBar("Some other guys!", 5, Color.blue);
		barSeries.add(b4);
		
		chart = new XYChart(barSeries, "Score Board!", "Name",	"Score");
		chart.yAxis.setMinValue(0);
		chart.yAxis.setMaxValue(MaxValue);
		chart.setAutoscrolls(true);
		
		chart.setTitleFont("Tahoma", Font.BOLD, 45);
		
		chart.xAxis.axisCatFont = new Font("Arial", Font.PLAIN, 30);
		chart.xAxis.font = new Font("Arial", Font.PLAIN, 25);
		
		chart.yAxis.axisCatFont = new Font("Arial", Font.PLAIN, 30);
		chart.yAxis.font = new Font("Arial", Font.PLAIN, 25);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		barSeries.
		
		return chart;
	}

	public static void main(String[] args) throws ParseException, InterruptedException {
		Scoreboard t = new Scoreboard();
		t.testChart(t.getChart());
		Thread.sleep(5000);
		t.b3.y=20;
		((XYChart)t.getChart()).reInitialiseScaling();
		
		
		t.repaint();
	}

	@Override
	public String getNiceTitle() {
		// TODO Auto-generated method stub
		return "Score Board!";
	}
}