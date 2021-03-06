/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.frontangle.ichart.main.test.pie;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.pie.Segment;
import com.frontangle.ichart.pie.SimpleIndicatorPieChart;

public class TestDataPie_IndicatorSimple extends ChartTester {

	
	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	
	public Chart getChart() {

		ArrayList<Segment> values = new ArrayList<Segment>();
		values.add(new Segment(15, Color.RED));
		values.add(new Segment(52, Color.BLUE));
		values.add(new Segment(33, Color.GREEN));

		SimpleIndicatorPieChart chart = new SimpleIndicatorPieChart(34, values,
				"Simple Indicator");

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "Pie : indicator simple";
	}

}
