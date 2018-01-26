package com.frontangle.ichart.main.test.math;

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

public class MathTester extends JFrame {

	
    public static void main(String[] args) throws Exception {
    	MathTester frame = new MathTester();
        frame.setVisible(true);
    }
    
	/**
	 * 
	 * @throws Exception
	 */
    public MathTester() throws Exception {
    	JTabbedPane p = createPanel();
    	
        getContentPane().add(p);

    }
    	
    	
	public JTabbedPane createPanel() throws ParseException {
    		

		
    	final ArrayList<JComponent> charts = new ArrayList<JComponent>();
    	
//        JTabbedPane tabbedPane = new JTabbedPane();
    	
        JTabbedPane tabbedPaneBar = new JTabbedPane();

        
        
//        tabbedPane.add("Bar Charts", tabbedPaneBar);

        JPanel p = null;
        JComponent chart = null;

        
        
        /***
         * 
         * 
         * BARS
         * 
         */

        p = createTabbedPane(tabbedPaneBar, "Simple ");
        chart = new TestDataXY_Math().getChartPanel();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPaneBar, "Polynomals");
        chart = new TestDataXY_Polynomals().getChartPanel();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Sin/Cos");
        chart = new TestDataXY_SineCurve().getChartPanel();
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
