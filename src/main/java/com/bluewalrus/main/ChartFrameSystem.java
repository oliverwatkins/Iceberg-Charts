/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.main;

import com.bluewalrus.chart.BubbleChart;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.main.test.TestDataBar;
import com.bluewalrus.main.test.TestDataBubble;
import com.bluewalrus.main.test.TestDataLineScatter;
import com.bluewalrus.main.test.TestDataPie;
import com.bluewalrus.main.test.TestDataPieBubble;
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
import javax.swing.JSlider;
import javax.swing.JTabbedPane;

/**
 *
 * @author lauren
 */
public class ChartFrameSystem  extends JFrame {

    public ChartFrameSystem() {

    	final ArrayList<JComponent> charts = new ArrayList<JComponent>();
    	
        BubbleChart chart = TestDataBubble.getTestData_Bubble();
        
        JSlider s = new JSlider(JSlider.VERTICAL, 10, 40, 10);
        JSlider s2 = new JSlider(JSlider.HORIZONTAL, 10, 40, 10);
        
        BottomControls bc = new BottomControls(chart);
        
        getContentPane().add(chart);
        getContentPane().add(bc, BorderLayout.SOUTH);
        getContentPane().add(s, BorderLayout.EAST);
        
        setSize(1400, 1100);

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
