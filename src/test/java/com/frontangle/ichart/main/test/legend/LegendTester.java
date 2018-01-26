package com.frontangle.ichart.main.test.legend;

import java.awt.BorderLayout;
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

public class LegendTester extends JFrame {

	
    public static void main(String[] args) throws Exception {
    	LegendTester frame = new LegendTester();
        frame.setVisible(true);
    }
    
	/**
	 * 
	 * @throws Exception
	 */
    public LegendTester() throws Exception {
    	JTabbedPane p = createPanel();
    	
        getContentPane().add(p);

    }
    	
    	
	public JTabbedPane createPanel() throws ParseException {
    		
    	final ArrayList<JComponent> charts = new ArrayList<JComponent>();
    	
        JTabbedPane tabbedPaneBar = new JTabbedPane();

        JPanel p = null;
        JComponent chart = null;

        
        
        /***
         * 
         * 
         * BARS
         * 
         */

        p = createTabbedPane(tabbedPaneBar, "Simple Grids");
        chart = new TestData_Legend_1().getChartPanel();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Graph Paper");
        chart = new TestData_Legend_2_Left().getChartPanel();
        charts.add(chart);
        p.add(chart);

        p = createTabbedPane(tabbedPaneBar, "Fill Y");
        chart = new TestData_Legend_2_Top().getChartPanel();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPaneBar, "Fill X");
        chart = new TestData_Legend_3_Bottom().getChartPanel();
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
