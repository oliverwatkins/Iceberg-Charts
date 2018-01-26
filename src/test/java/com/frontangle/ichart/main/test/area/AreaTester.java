package com.frontangle.ichart.main.test.area;

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

public class AreaTester extends JFrame {

	
    public static void main(String[] args) throws Exception {
    	AreaTester frame = new AreaTester();
        frame.setVisible(true);
    }
    
	/**
	 * 
	 * @throws Exception
	 */
    public AreaTester() throws Exception {
    	JTabbedPane p = createPanel();
    	
        getContentPane().add(p);

    }
    	
    	
	public JTabbedPane createPanel() throws ParseException {

    	final ArrayList<JComponent> charts = new ArrayList<JComponent>();
    	
        JTabbedPane tabbedPaneBar = new JTabbedPane();

        JPanel p = null;
        JComponent chart = null;

        p = createTabbedPane(tabbedPaneBar, "Simple Area");
        chart = new TestDataArea_1_Simple().getChartPanel();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Multiple Area Overlay");
        chart = new TestDataArea_2_Multiple_Layered().getChartPanel();
        charts.add(chart);
        p.add(chart);
        
        
        p = createTabbedPane(tabbedPaneBar, "Multiple Area Stacked Simple");
        chart = new TestDataArea_3_Multiple_Stacked_Simple().getChartPanel();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Multiple Area Stacked");
        chart = new TestDataArea_4_Multiple_Stacked().getChartPanel();
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
