package com.frontangle.ichart.main.test.xy;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.Utils;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.datapoint.DataPointBar;
import com.frontangle.ichart.main.test.ChartTester;

/**
 * 
 * @author Oliver Watkins
 */
public class TestDataBar_FontFun extends ChartTester {

	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
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

		XYChart chart = new XYChart("", "\u2202 \u2201 \u2602 \u266B \u20AC umbrella sales", "number of sunny days", values, 40);
		
		String s = "\u2202";
				
		System.out.println("unicode \u2202 \u2602 " + s);
		Font font = new Font("Lucida Sans Regular", Font.PLAIN, 32);
        
		chart.xAxis.axisCatFont = font;
		chart.xAxis.font = font;

		chart.yAxis.axisCatFont = new Font("Blackadder ITC", Font.PLAIN, 16);
		chart.yAxis.font = new Font("Blackadder ITC", Font.PLAIN, 20);

		chart.setTitleFont(new Font("Lucida Sans Regular", Font.PLAIN, 32));
		chart.setTitle("\u2202 \u2201 \u2602 \u266B \u20AC");
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
