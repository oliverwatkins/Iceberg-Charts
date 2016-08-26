package com.bluewalrus.main.test.xy;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.Utils;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.main.test.ChartTester;

/**
 * @copyright @author Oliver Watkins (www.blue-walrus.com) All Rights Reserved
 */
public class TestDataBar_FontFun extends ChartTester {

	@Override
	public Chart getChart() {

		ArrayList<Color> colors = Utils
				.makeGradients(Color.RED, Color.BLUE, 12);

		ArrayList<DataPointBar> values = new ArrayList<DataPointBar>();
		values.add(new DataPointBar("One", 10, colors.get(0)));
		values.add(new DataPointBar("Two", 14, colors.get(1)));
		values.add(new DataPointBar("Three", 12, colors.get(3)));
		values.add(new DataPointBar("Four", 1, colors.get(4)));
		values.add(new DataPointBar("Five", 18, colors.get(5)));
		values.add(new DataPointBar("Six", 40, colors.get(6)));

		XYChart chart = new XYChart(values, "Really Big Text", "yadda yadda",
				"yadda yadda", 40);

		chart.xAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
		chart.xAxis.font = new Font("Blackadder ITC", Font.PLAIN, 20);
		chart.xAxis.labelText = "Playing with fonts";

		chart.yAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
		chart.yAxis.font = new Font("Blackadder ITC", Font.PLAIN, 20);

		chart.setTitleFont("Ravie", Font.PLAIN, 70);
		chart.setTitle("Really Big Text");
		chart.setSize(900, 500);

		return chart;
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_FontFun();
		t.testChart(t.getChart());
	}

	@Override
	public String getNiceTitle() {
		return "XY: Font Fun";
	}
}
