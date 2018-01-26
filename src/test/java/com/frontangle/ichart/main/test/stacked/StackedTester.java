package com.frontangle.ichart.main.test.stacked;

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

public class StackedTester extends JFrame {

	
    public static void main(String[] args) throws Exception {
    	StackedTester frame = new StackedTester();
        frame.setVisible(true);
    }
    
	/**
	 * 
	 * @throws Exception
	 */
    public StackedTester() throws Exception {
    	JTabbedPane p = createPanel();
    	
        getContentPane().add(p);

    }
    	
    	
	public JTabbedPane createPanel() throws ParseException {

    	final ArrayList<JComponent> charts = new ArrayList<JComponent>();
    	
    	
        JTabbedPane tabbedPaneBar = new JTabbedPane();

        JPanel p = null;
        JComponent chart = null;

        p = createTabbedPane(tabbedPaneBar, "Stacked 1");
        chart = new TestStackedChart().getChartPanel();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPaneBar, "Stacked 2");
        chart = new TestStackedChart2().getChartPanel();
        charts.add(chart);
        p.add(chart);

        p = createTabbedPane(tabbedPaneBar, "Stacked 3");
        chart = new TestStackedChart3().getChartPanel();
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
