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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chartmanager.panels.AxisPropertiesPanel;
import com.bluewalrus.chartmanager.panels.ChartPropertiesPanel;
import com.bluewalrus.chartmanager.panels.GridPanel;
import com.bluewalrus.main.test.TestDataBubble;


public class ChartManagerApp extends JFrame {

	public ActionListener applyAction;

	private XYChart chart;
	
	FileManager fm = new FileManager();

	private GridPanel gridPanel;

	private ChartPropertiesPanel chartPropertiesPanel;

	private AxisPropertiesPanel xAxisPanel;

	private AxisPropertiesPanel yAxisPanel;

	public ChartManagerApp() throws Exception {

		setupMenu();
		

		chart = TestDataBubble.getTestData_Bubble();

		loadChart(chart);
		
		

		setSize(1300, 620);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setupMenu() throws Exception {
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
					XYChart xy = fm.open(ChartManagerApp.this);
					loadChart(xy);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		

		menu.add(i1);
		menu.add(i2);
		menu.add(i3);
		menu.addSeparator();
		menuBar.add(menu);
		
		ArrayList<ChartFile> recentCharts = fm.getLatestSavedCharts(ChartManagerApp.this);
		
		for (final ChartFile chartFile : recentCharts) {
			
			JMenuItem i = new JMenuItem("" + chartFile.location);
			i.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						XYChart xy = fm.open(ChartManagerApp.this, chartFile);
						loadChart(xy);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
			menu.add(i);
		}
	}

	private void loadChart(final XYChart chart) {
		
		this.chart = chart;
		



		
		if (getContentPane().getComponentCount() != 0) {
//			Component[] a = getContentPane().getComponents();
			

			XYChart oldC = (XYChart)getContentPane().getComponent(0);

			getContentPane().remove(oldC);
			getContentPane().add(chart, 0);
			
			
//			panelY.chart = chart;
//			panelX.chart = chart;
//			
			xAxisPanel.setChart(chart);
			yAxisPanel.setChart(chart);
			
//			yAxisPanel.chart = chart;
//			chartPropertiesPanel
//			gridPanel
			
			
			getContentPane().repaint();
			
		}else { //new
			
			initUI(chart);
			
		}
		
		
	}

	private void initUI(final XYChart chart) {
		
		JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
		JPanel rightPanel = new JPanel(new GridLayout(3, 1));

		xAxisPanel = new AxisPropertiesPanel(chart.xAxis, chart, this);
		yAxisPanel = new AxisPropertiesPanel(chart.yAxis, chart, this);

		chartPropertiesPanel = new ChartPropertiesPanel(chart);

		JButton applyButton = new JButton("Apply");

		bottomPanel.add(xAxisPanel);
		bottomPanel.add(yAxisPanel);
		bottomPanel.add(chartPropertiesPanel);
		bottomPanel.add(applyButton);
		
		gridPanel = new GridPanel(chart, this);

		rightPanel.add(gridPanel);
		
		applyAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				xAxisPanel.apply();
				yAxisPanel.apply();

				ChartManagerApp.this.chart.updateUI();
			}
		};

		applyButton.addActionListener(applyAction);
		
		getContentPane().add(chart);
		
		
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		getContentPane().add(rightPanel, BorderLayout.EAST);
	}

	public static void main(String[] args) throws Exception {
		ChartManagerApp frame = new ChartManagerApp();
		frame.setVisible(true);
	}


}
