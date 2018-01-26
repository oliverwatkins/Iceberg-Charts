package com.frontangle.ichart.main.test.multibar;

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

public class MultiBarTester extends JFrame {

	
    public static void main(String[] args) throws Exception {
        MultiBarTester frame = new MultiBarTester();
        frame.setVisible(true);
    }
    
	/**
	 * 
	 * @throws Exception
	 */
    public MultiBarTester() throws Exception {
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

        p = createTabbedPane(tabbedPaneBar, " Multi-Bar : Side by Side");
        chart = new TestDataBar_MultiBar_SideBySide().getChartPanel();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPaneBar, "Multi-Bar : Stacked");
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
