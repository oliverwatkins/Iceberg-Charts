/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.chartmanager;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chartmanager.panels.AxisPropertiesPanel;
import com.bluewalrus.chartmanager.panels.ChartPropertiesPanel;
import com.bluewalrus.chartmanager.panels.GridPanel;
import com.bluewalrus.main.test.TestDataBubble;


public class ChartManagerApp extends JFrame {

	public ActionListener applyAction;

	
	ArrayList<ChartFile> recentCharts = new ArrayList<ChartFile>();

	private final XYChart chart;
	
	FileManager fm = new FileManager();

	public ChartManagerApp() throws Exception {

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu menu = new JMenu("File");

		
		JMenuItem i1 = new JMenuItem("Open");
		JMenuItem i2 = new JMenuItem("Save");
		JMenuItem i3 = new JMenuItem("Save As..");
		
		
		i2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				fm.save(chart);
			}
		});
		i3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fm = new FileManager();
				try {
					fm.saveAs(chart, ChartManagerApp.this);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		i1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fm = new FileManager();
				try {
					fm.open(ChartManagerApp.this);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

		menu.add(i1);
		menu.add(i2);
		menu.add(i3);
		menuBar.add(menu);
		
		recentCharts = fm.getLatestSavedCharts(ChartManagerApp.this);
		
		for (ChartFile chartFile : recentCharts) {
			menu.add(new JMenuItem("" + chartFile.location));
		}
		

		chart = TestDataBubble.getTestData_Bubble();

		JPanel jpanel = new JPanel(new GridLayout(1, 3));
		JPanel jpanel2 = new JPanel(new GridLayout(3, 1));

		final AxisPropertiesPanel panelX = new AxisPropertiesPanel(chart.xAxis,
				chart, this);
		final AxisPropertiesPanel panelY = new AxisPropertiesPanel(chart.yAxis,
				chart, this);

		final ChartPropertiesPanel panelC = new ChartPropertiesPanel(chart);

		JButton applyButton = new JButton("Apply");

		jpanel.add(panelX);
		jpanel.add(panelY);
		jpanel.add(panelC);
		jpanel.add(applyButton);

		jpanel2.add(new GridPanel(chart, this));

		applyAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				panelX.apply();
				panelY.apply();
				panelC.apply();

				chart.updateUI();
			}
		};

		applyButton.addActionListener(applyAction);

		getContentPane().add(chart);
		getContentPane().add(jpanel, BorderLayout.SOUTH);
		getContentPane().add(jpanel2, BorderLayout.EAST);

		setSize(1300, 620);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws Exception {
		ChartManagerApp frame = new ChartManagerApp();
		frame.setVisible(true);
	}

	private JPanel createTabbedPane(JTabbedPane tabbedPane, String string) {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		tabbedPane.add(string, panel);
		return panel;
	}

}
