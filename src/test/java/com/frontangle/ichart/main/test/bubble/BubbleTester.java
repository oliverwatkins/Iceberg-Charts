package com.frontangle.ichart.main.test.bubble;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.main.test.xyy.TestDataBar_2Y;

public class BubbleTester extends JFrame {

	public static void main(String[] args) throws Exception {
		BubbleTester frame = new BubbleTester();
		frame.setVisible(true);
	}

	/**
	 * 
	 * @throws Exception
	 */
	public BubbleTester() throws Exception {
		JTabbedPane p = createPanel();

		getContentPane().add(p);

	}

	public JTabbedPane createPanel() throws ParseException {

		final ArrayList<JComponent> charts = new ArrayList<JComponent>();

		JTabbedPane tabbedPaneBar = new JTabbedPane();

		JPanel p = null;
		JComponent chart = null;

		p = createTabbedPane(tabbedPaneBar, "Bubble Guns");
		chart = new TestDataBubble_1_guns().getChartPanel();
		charts.add(chart);
		p.add(chart);

		p = createTabbedPane(tabbedPaneBar, "Bubble Series");
		chart = new TestDataBubble_2_series().getChartPanel();
		charts.add(chart);
		p.add(chart);

		p = createTabbedPane(tabbedPaneBar, "Pie Bubble");
		chart = new TestDataPieBubble().getChartPanel();
		charts.add(chart);
		p.add(chart);



		setSize(1300, 800);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		return tabbedPaneBar;
	}

	private JPanel createTabbedPane(JTabbedPane tabbedPane, String string) {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		tabbedPane.add(string, panel);
		return panel;
	}

}
