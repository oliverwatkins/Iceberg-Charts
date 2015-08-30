/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.chartmanager.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;

public class ChartPropertiesPanel extends JPanel {
	
	private XYChart chart;
	
	public ChartPropertiesPanel(Chart chart) {

		this.chart = (XYChart)chart;
		setBorder(BorderFactory.createTitledBorder("XY Chart Properties"));

		initComponents();
	}


	private void initComponents() {

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;

		this.setLayout(new GridBagLayout());

		
		final ColorChooserPanel p = new ColorChooserPanel("BackgroundColor ");
		
		p.setOnColorChangeListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				chart.backgroundColor = p.getChosenColor();
				chart.updateUI();
				
			}
		});
		
		this.add(p, gbc);
		
		gbc.gridy = 1;
		
		final ColorChooserPanel p2 = new ColorChooserPanel("Axis Color ");
		
		p2.setOnColorChangeListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Color c = p2.getChosenColor();
				
				chart.yAxis.axisColor = c;
				chart.xAxis.axisColor = c;
				chart.updateUI();
			}
		});

		
		this.add(p2, gbc);


	}
}
