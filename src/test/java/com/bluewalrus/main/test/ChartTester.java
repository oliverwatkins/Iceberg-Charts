package com.bluewalrus.main.test;

import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.bluewalrus.chart.Chart;

public abstract class ChartTester extends JFrame{
	
	public abstract JPanel getChart() throws ParseException;
	
	
	public void testChart(JPanel chart) throws ParseException {

		JTabbedPane tabbedPaneBar = new JTabbedPane();

		tabbedPaneBar.addTab("1", chart);

		getContentPane().add(tabbedPaneBar);

		setSize(700, 700);
		setVisible(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	

}
