package com.frontangle.ichart.main.test.bar;

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
import com.frontangle.ichart.main.test.multibar.TestDataBar_MultiBar_Stacked;
import com.frontangle.ichart.main.test.xyy.TestDataBar_2Y;

public class BarsTester extends JFrame {

	
    public static void main(String[] args) throws Exception {
        BarsTester frame = new BarsTester();
        frame.setVisible(true);
    }
    
	/**
	 * 
	 * @throws Exception
	 */
    public BarsTester() throws Exception {
    	JTabbedPane p = createPanel();
    	
        getContentPane().add(p);

    }
    	
    	
	public JTabbedPane createPanel() throws ParseException {
    		

    	final ArrayList<JComponent> charts = new ArrayList<JComponent>();
    	
        JTabbedPane tabbedPaneBar = new JTabbedPane();

        JPanel p = null;
        JComponent chart = null;

        p = createTabbedPane(tabbedPaneBar, "Simple Bar");
        chart = new TestDataBar_1_Simple().getChartPanel();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Simple Negative");
        chart = new TestDataBar_2_Simple_negative().getChartPanel();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Fixed Axis");
        chart = new TestDataBar_3_Simple_fixed_axis().getChartPanel();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Gradiant Color");
        chart = new TestDataBar_4_GradientColor().getChartPanel();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Pos Neg Color");
        chart = new TestDataBar_5_PosNegColor().getChartPanel();
        charts.add(chart);
        p.add(chart);

        p = createTabbedPane(tabbedPaneBar, "Transparancy");
        chart = new TestDataBar_6_Transparancy().getChartPanel();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Bar - 2 Y Axes");
        chart = new TestDataBar_2Y().getChartPanel();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "MultiBar - stacked");
        chart = new TestDataBar_MultiBar_Stacked().getChartPanel();
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
