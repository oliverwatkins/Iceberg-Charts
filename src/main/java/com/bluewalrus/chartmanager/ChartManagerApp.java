///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.bluewalrus.chartmanager;
//
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JPanel;
//
//import com.bluewalrus.chart.XYChart;
//import com.bluewalrus.chartmanager.panels.AxisPropertiesPanel;
//import com.bluewalrus.chartmanager.panels.ChartPropertiesPanel;
//import com.bluewalrus.chartmanager.panels.GridPanel;
//import com.bluewalrus.main.test.TestDataBubble;
//
//
//public class ChartManagerApp extends JFrame {
//
//	public ActionListener applyAction;
//
//	public XYChart chart;
//
//	private GridPanel gridPanel;
//
//	private ChartPropertiesPanel chartPropertiesPanel;
//	private AxisPropertiesPanel xAxisPanel;
//	private AxisPropertiesPanel yAxisPanel;
//
//	public ChartManagerApp() throws Exception {
//
//		MenuManager menu = new MenuManager();
//		menu.setupMenu(this);
//		
//		loadChart(TestDataBubble.getTestData_Bubble());
//
//		setSize(1300, 620);
//
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//
//
//	void loadChart(final XYChart chart) {
//		
//		this.chart = chart;
//		
//		if (getContentPane().getComponentCount() != 0) {
//
//			XYChart oldChart = (XYChart)getContentPane().getComponent(0);
//
//			getContentPane().remove(oldChart);
//			getContentPane().add(chart, 0);
//			
//			xAxisPanel.setChart(chart, chart.xAxis);
//			yAxisPanel.setChart(chart, chart.yAxis);
//			chartPropertiesPanel.setChart(chart);
//			gridPanel.setChart(chart);
//			
//			
//			
//			getContentPane().revalidate();
//			
//		}else { //new
//			initUI(chart);
//		}
//	}
//
//	private void initUI(final XYChart chart) {
//		
//		JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
//		JPanel rightPanel = new JPanel(new GridLayout(3, 1));
//
//		xAxisPanel = new AxisPropertiesPanel(chart.xAxis, chart, this);
//		yAxisPanel = new AxisPropertiesPanel(chart.yAxis, chart, this);
//
//		chartPropertiesPanel = new ChartPropertiesPanel(chart);
//
//		JButton applyButton = new JButton("Apply");
//
//		bottomPanel.add(xAxisPanel);
//		bottomPanel.add(yAxisPanel);
//		bottomPanel.add(chartPropertiesPanel);
//		bottomPanel.add(applyButton);
//		
//		gridPanel = new GridPanel(chart, this);
//
//		rightPanel.add(gridPanel);
//		
//		applyAction = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				xAxisPanel.apply();
//				yAxisPanel.apply();
//
//				ChartManagerApp.this.chart.updateUI();
//			}
//		};
//
//		applyButton.addActionListener(applyAction);
//		
//		getContentPane().add(chart);
//		
//		
//		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
//		getContentPane().add(rightPanel, BorderLayout.EAST);
//	}
//
//	public static void main(String[] args) throws Exception {
//		ChartManagerApp frame = new ChartManagerApp();
//		frame.setVisible(true);
//	}
//
//
//}
