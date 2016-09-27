package com.bluewalrus.main;

import java.awt.BorderLayout;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SingleChartDemo extends JFrame {



	public SingleChartDemo() throws ParseException {

//		JTabbedPane tabbedPaneBar = new JTabbedPane();
//
//		Chart chart2 = new TestDataGrids_2_GraphPaper().getChart();
//
//		tabbedPaneBar.addTab("1", chart2);
//
//		getContentPane().add(tabbedPaneBar);
//
//		setSize(1300, 800);
//
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws Exception {
		SingleChartDemo frame = new SingleChartDemo();
		frame.setVisible(true);
	}

	private JPanel createTabbedPane(JTabbedPane tabbedPane, String string) {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		tabbedPane.add(string, panel);
		return panel;
	}

}
