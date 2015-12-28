package com.bluewalrus.main;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.main.test.TestDataBar;
import com.bluewalrus.main.test.TestDataLineScatter;

public class SingleChartDemo extends JFrame {

    public SingleChartDemo() {
    	
        JTabbedPane tabbedPaneBar = new JTabbedPane();
        
        Chart chart = TestDataBar.getTestData_BarNegativeAndPositive();
        
        Chart chart2 = TestDataBar.getTestData_BarNegativeAndPositiveNumerical();

        tabbedPaneBar.addTab("1", chart);

        tabbedPaneBar.addTab("2", chart2);
        
        getContentPane().add(tabbedPaneBar);
        
        setSize(1300, 800);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SingleChartDemo frame = new SingleChartDemo();
        frame.setVisible(true);
    }

    private JPanel createTabbedPane(JTabbedPane tabbedPane, String string) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        tabbedPane.add(string, panel);
        return panel;
    }

}
