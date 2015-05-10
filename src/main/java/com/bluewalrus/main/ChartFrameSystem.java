/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.main;

import com.bluewalrus.chart.BubbleChart;
import com.bluewalrus.main.test.TestDataBubble;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author lauren
 */
public class ChartFrameSystem  extends JFrame {

    public ChartFrameSystem() {

    	final ArrayList<JComponent> charts = new ArrayList<JComponent>();
    	
        BubbleChart chart = TestDataBubble.getTestData_Bubble();
        
        JPanel jpanel = new JPanel(new GridLayout(1, 3));
        
        AxisPropertiesPanel panelX = new AxisPropertiesPanel(chart.xAxis);
        AxisPropertiesPanel panelY = new AxisPropertiesPanel(chart.yAxis);
        
        ChartPropertiesPanel panelC = new ChartPropertiesPanel(chart);
        
        JButton b = new JButton("Apply");
        
        jpanel.add(panelX);
        jpanel.add(panelY);
        jpanel.add(panelC);
        jpanel.add(b);
        
        getContentPane().add(chart);
        getContentPane().add(jpanel, BorderLayout.SOUTH);
//        getContentPane().add(s, BorderLayout.EAST);
        
        setSize(1600, 900);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ChartFrameSystem frame = new ChartFrameSystem();
        frame.setVisible(true);
    }

    private JPanel createTabbedPane(JTabbedPane tabbedPane, String string) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        tabbedPane.add(string, panel);
        return panel;
    }

}
