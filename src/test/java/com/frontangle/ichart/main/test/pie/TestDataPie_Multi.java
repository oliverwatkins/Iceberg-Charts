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

import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.Utils;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.pie.MultiLevelPieChart;
import com.frontangle.ichart.pie.Segment;

public class TestDataPie_Multi extends ChartTester {

	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	
	public Chart getChart() {
		

		ArrayList<Segment> values = new ArrayList<Segment>();
		values.add(new Segment(15, "music", Color.RED));
		values.add(new Segment(52, "photos", Color.BLUE));
		values.add(new Segment(33, "applications", Color.GREEN));

		Color c = Color.BLUE;
		Segment s0 = values.get(0);
		Segment s1 = values.get(1);
		Segment s2 = values.get(2);

		{
			c = s0.color;
			s0.children.add(new Segment(1, s0, 80.0, "jazz"));
			s0.children.add(new Segment(1, s0, 7.0, "rock"));
			s0.children.add(new Segment(1, s0, 13.0, "classical"));

			Utils.makeGradients(Color.BLUE, Color.LIGHT_GRAY, s0.children);
		}
		{
			c = Color.cyan;
			s1.children
					.add(new Segment(1, s1, 80.0, "holiday snaps", darken(c)));
			s1.children.add(new Segment(1, s1, 7.0, "weddings",
					darken(darken(c))));
			s1.children.add(new Segment(1, s1, 13.0, "baby",
					darken(darken(darken(c)))));
		}
		{
			c = Color.YELLOW;
			s2.children.add(new Segment(1, s2, 80.0, "Windows", darken(c)));
			s2.children.add(new Segment(1, s2, 7.0, "Favourites",
					darken(darken(c))));
			s2.children.add(new Segment(1, s2, 13.0, "Database",
					darken(darken(darken(c)))));
		}
		Segment s20 = s2.children.get(0);
		{
			c = Color.PINK;
			s20.children.add(new Segment(2, s20, 50.0, "Office", darken(c)));
			s20.children.add(new Segment(2, s20, 7.0, "Visio",
					darken(darken(c))));
			s20.children.add(new Segment(2, s20, 43.0, "Paint",
					darken(darken(darken(c)))));
		}
		Segment s22 = s2.children.get(2);
		{
			c = Color.WHITE;
			s22.children.add(new Segment(2, s22, 50.0, "Oracle", darken(c)));
			s22.children.add(new Segment(2, s22, 50.0, "Access",
					darken(darken(c))));
		}

		Segment s200 = s20.children.get(0);
		{
			c = Color.ORANGE;
			s200.children.add(new Segment(3, s200, 10.0, "Word", darken(c)));
			s200.children.add(new Segment(3, s200, 80.0, "Excel",
					darken(darken(c))));
			s200.children.add(new Segment(3, s200, 5.0, "Access",
					darken(darken(darken(c)))));
			s200.children.add(new Segment(3, s200, 5.0, "Powerpoint",
					darken(darken(darken(darken(c))))));
		}

		Segment s10 = s1.children.get(0);
		{
			c = Color.white;
			s10.children.add(new Segment(2, s10, 30.0, "Rome"));
			s10.children.add(new Segment(2, s10, 20.0, "Paris"));
			s10.children.add(new Segment(2, s10, 10.0, "London"));
			s10.children.add(new Segment(2, s10, 10.0, "Beach"));
			s10.children.add(new Segment(2, s10, 30.0, "China"));

			Utils.makeGradients(Color.BLUE, Color.GREEN, s10.children);
		}
		MultiLevelPieChart chart = new MultiLevelPieChart(values,
				"Disk Space Usage");

		chart.setSize(600, 600);

		chart.topOffset = 50;
		chart.leftOffset = 50;
		chart.rightOffset = 50;
		chart.bottomOffset = 50;
		chart.initialWidth = 100;
		chart.incrementWidth = 50;

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "Pie : Multi Level";
	}

	private static Color darken(Color c) {
		return c.darker();
	}
}
