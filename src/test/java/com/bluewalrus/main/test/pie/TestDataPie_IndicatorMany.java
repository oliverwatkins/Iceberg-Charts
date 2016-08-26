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

public class TestDataPie_IndicatorMany extends ChartTester {
	@Override
	public JPanel getChart() {

    	SimpleIndicatorPieChart pieChart = 
    			new SimpleIndicatorPieChart(34, "Simple Indicator", 10);
    	
    	pieChart.setSize(10, 10);
    	
        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel bottomPanel = new JPanel(new GridBagLayout());

        topPanel.setBorder(BorderFactory.createTitledBorder("Class"));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Student Pass Rates for 2011"));
        
        topPanel.add(new JLabel("School :"));
        topPanel.add(new JLabel("Greythorn Tech"));
        topPanel.add(new JLabel("Grade : "));
        topPanel.add(new JLabel("Level 9"));
        
        panel.add(topPanel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();

        int col1 = 0;
        int col2 = 1;
        
        int row = 0;

        gbc.gridy = row;
        gbc.gridx = col1;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        bottomPanel.add(new JLabel("English :"), gbc);
        gbc.weightx = 0;
        gbc.gridx = col2;
        bottomPanel.add(createLittleIndicatorPanel(34), gbc);

        row++;
        
        gbc.gridy = row;
        gbc.gridx = col1;
        gbc.weightx = 1;
        bottomPanel.add(new JLabel("German :"), gbc);
        gbc.weightx = 0;
        gbc.gridx = col2;
        bottomPanel.add(createLittleIndicatorPanel(90), gbc);

        row++;

        gbc.gridy = row;
        gbc.gridx = col1;
        gbc.weightx = 1;
        bottomPanel.add(new JLabel("French :"), gbc);
        gbc.gridx = col2;
        gbc.weightx = 0;
        bottomPanel.add(createLittleIndicatorPanel(34), gbc);

        row++;

        gbc.gridy = row;
        gbc.gridx = col1;
        gbc.weightx = 1;
        bottomPanel.add(new JLabel("Spanish :"), gbc);
        gbc.gridx = col2;
        gbc.weightx = 0;
        bottomPanel.add(createLittleIndicatorPanel(98), gbc);

        row++;
        
        gbc.weightx = 1;
        gbc.gridy = row;
        gbc.gridx = col1;
        bottomPanel.add(new JLabel("Maths A :"), gbc);
        gbc.weightx = 0;
        gbc.gridx = col2;
        bottomPanel.add(createLittleIndicatorPanel(50), gbc);

        row++;
        
        gbc.weightx = 1;
        gbc.gridy = row;
        gbc.gridx = col1;
        bottomPanel.add(new JLabel("Maths B :"), gbc);
        gbc.weightx = 0;
        gbc.gridx = col2;
        bottomPanel.add(createLittleIndicatorPanel(67), gbc);

        row++;
        
        panel.add(bottomPanel);
        
        return panel;
    }
	
	@Override
	public String getNiceTitle() {
		return "Pie : indicator many";
	}
    
    
    private static Component createLittleIndicatorPanel(int i) {
    	
    	JPanel p = new JPanel();
    	
    	p.add(new JLabel(i + " % "));
    	p.add(new SimpleIndicatorPieChart(i, "Simple Indicator", 10));
    	
		return p;
	}


	public static SimpleIndicatorPieChart getTestData_MultiIndicator() {

        ArrayList<Segment> values = new ArrayList<Segment>();
        values.add(new Segment(15, Color.RED));
        values.add(new Segment(52, Color.BLUE));
        values.add(new Segment(33, Color.GREEN));
    	
    	SimpleIndicatorPieChart pieChart = new SimpleIndicatorPieChart(34, values, "Simple Indicator");
        
        return pieChart;
    }

    


    public static MultiLevelPieChart getTestData_multiLevelPie() {

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
            s1.children.add(new Segment(1, s1, 80.0, "holiday snaps", darken(c)));
            s1.children.add(new Segment(1, s1, 7.0, "weddings", darken(darken(c))));
            s1.children.add(new Segment(1, s1, 13.0, "baby", darken(darken(darken(c)))));
        }
        {
            c = Color.YELLOW;
            s2.children.add(new Segment(1, s2, 80.0, "Windows", darken(c)));
            s2.children.add(new Segment(1, s2, 7.0, "Favourites", darken(darken(c))));
            s2.children.add(new Segment(1, s2, 13.0, "Database", darken(darken(darken(c)))));
        }
        Segment s20 = s2.children.get(0);
        {
            c = Color.PINK;
            s20.children.add(new Segment(2, s20, 50.0, "Office", darken(c)));
            s20.children.add(new Segment(2, s20, 7.0, "Visio", darken(darken(c))));
            s20.children.add(new Segment(2, s20, 43.0, "Paint", darken(darken(darken(c)))));
        }
        Segment s22 = s2.children.get(2);
        {
            c = Color.WHITE;
            s22.children.add(new Segment(2, s22, 50.0, "Oracle", darken(c)));
            s22.children.add(new Segment(2, s22, 50.0, "Access", darken(darken(c))));
        }

        Segment s200 = s20.children.get(0);
        {
            c = Color.ORANGE;
            s200.children.add(new Segment(3, s200, 10.0, "Word", darken(c)));
            s200.children.add(new Segment(3, s200, 80.0, "Excel", darken(darken(c))));
            s200.children.add(new Segment(3, s200, 5.0, "Access", darken(darken(darken(c)))));
            s200.children.add(new Segment(3, s200, 5.0, "Powerpoint", darken(darken(darken(darken(c))))));
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
        MultiLevelPieChart pieChart = new MultiLevelPieChart(values, "Disk Space Usage");
        
        pieChart.setSize(600,600);
        
        pieChart.topOffset = 50;
        pieChart.leftOffset = 50;
        pieChart.rightOffset = 50;
        pieChart.bottomOffset = 50;
        pieChart.initialWidth = 100;
        pieChart.incrementWidth = 50;
        
        return pieChart;
    }
    
    private static Color darken(Color c) {
        return c.darker();
    }
}
