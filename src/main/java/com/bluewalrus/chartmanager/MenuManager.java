package com.bluewalrus.chartmanager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.main.test.TestDataBar;
import com.bluewalrus.main.test.TestDataBubble;
import com.bluewalrus.main.test.TestDataLineScatter;

public class MenuManager {
	
	FileManager fm = new FileManager();

	public void setupMenu(final ChartManagerApp app) throws Exception {
		JMenuBar menuBar = new JMenuBar();
		app.setJMenuBar(menuBar);

		JMenu menu = new JMenu("File");
		
		JMenu newItem = new JMenu("New");
		
		JMenuItem newItemBubble = new JMenuItem("Bubble");
		JMenuItem newItemScatter = new JMenuItem("Scatter");
		JMenuItem newItemLine = new JMenuItem("Line");
		JMenuItem newItemXXY = new JMenuItem("XXY");
		
		
		JMenuItem newItemBoxplot = new JMenuItem("Boxplot");

		newItem.add(newItemBubble);
		newItem.add(newItemScatter);
		newItem.add(newItemLine);
		newItem.add(newItemBoxplot);
		newItem.add(newItemXXY);
		
		
		
		
		
		JMenuItem i1 = new JMenuItem("Open");
		JMenuItem i2 = new JMenuItem("Save");
		JMenuItem i3 = new JMenuItem("Save As..");
		
		newItemBubble.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				app.loadChart(TestDataBubble.getTestData_Bubble());
			}
		});
		
		///
		
		newItemScatter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				app.loadChart(TestDataLineScatter.getTestData_Scatter());
			}
		});
		
		
		newItemLine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				app.loadChart(TestDataLineScatter.getTestData_LineExamples());
			}
		});
		
		newItemBoxplot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				app.loadChart(TestDataLineScatter.getTestData_BoxPlot());
			}
		});
		
		///
		
		newItemXXY.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				app.loadChart(TestDataBar.getTestData_Bar2Y());
			}
		});
		
		
		i2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				fm.save(app.chart);
			}
		});
		i3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fm = new FileManager();
				try {
					fm.saveAs(app.chart, app);
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
					XYChart xy = fm.open(app);
					app.loadChart(xy);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		menu.add(newItem);
		menu.add(i1);
		menu.add(i2);
		menu.add(i3);
		menu.addSeparator();
		menuBar.add(menu);
		
		ArrayList<ChartFile> recentCharts = fm.getLatestSavedCharts(app);
		
		for (final ChartFile chartFile : recentCharts) {
			
			JMenuItem i = new JMenuItem("" + chartFile.location);
			i.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						XYChart xy = fm.open(app, chartFile);
						app.loadChart(xy);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
			menu.add(i);
		}
	}

}
