/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.main.test.pie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.Utils;
import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.pie.MultiLevelPieChart;
import com.bluewalrus.pie.PieChart;
import com.bluewalrus.pie.Segment;
import com.bluewalrus.pie.SimpleIndicatorPieChart;

public class TestDataPie_SimplePie extends ChartTester {
	@Override
	public Chart getChart() {

		ArrayList<Segment> values = new ArrayList<Segment>();
		values.add(new Segment(35, "something", Color.RED));
		values.add(new Segment(32, "something else", Color.BLUE));
		values.add(new Segment(5, "something or other", Color.BLUE.brighter()));
		values.add(new Segment(3, "this", Color.BLUE.darker()));
		values.add(new Segment(7, "that", Color.ORANGE));
		values.add(new Segment(5, "and", Color.CYAN));
		values.add(new Segment(13, "the other", Color.GREEN));

		PieChart chart = new PieChart(values, "Simple Pie");

		chart.setSize(800, 700);

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "Pie : Simple";
	}
}
