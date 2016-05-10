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
import com.bluewalrus.main.test.bar.TestDataBar_1_Simple;
import com.bluewalrus.main.test.bar.TestDataBar_2_Simple_negative;
import com.bluewalrus.main.test.bar.TestDataBar_BarCanBeXY;
import com.bluewalrus.main.test.bar.TestDataBar_MultiBar_Stacked;
import com.bluewalrus.main.test.bar.TestDataBar_thinLines;
import com.bluewalrus.main.test.xyy.TestDataBar_2Y;

public class BarsTester extends JFrame {

	/**
	 * 
	 * @throws Exception
	 */
    public BarsTester() throws Exception {

    	final ArrayList<JComponent> charts = new ArrayList<JComponent>();
    	
        JTabbedPane tabbedPane = new JTabbedPane();
    	
        JTabbedPane tabbedPaneBar = new JTabbedPane();
        JTabbedPane tabbedPaneLine = new JTabbedPane();
        JTabbedPane tabbedPanePie = new JTabbedPane();
        JTabbedPane tabbedPaneGridFills = new JTabbedPane();
        JTabbedPane tabbedPaneFracNegatives = new JTabbedPane();
        JTabbedPane tabbedPaneTime = new JTabbedPane();
        JTabbedPane tabbedPaneXYY = new JTabbedPane();
        
        
        tabbedPane.add("Bar Charts", tabbedPaneBar);

        JPanel p = null;
        JComponent chart = null;

        
        
        /***
         * 
         * 
         * BARS
         * 
         */

        p = createTabbedPane(tabbedPaneBar, "Bar - postitive negative (category)");
        chart = new TestDataBar_1_Simple().getChart();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPaneBar, "Bar - postitive negative (category)");
        chart = new TestDataBar_2_Simple_negative().getChart();
        charts.add(chart);
        p.add(chart);
        
        
        p = createTabbedPane(tabbedPaneBar, "Bar - Can be XY (non category)");
        chart = new TestDataBar_BarCanBeXY().getChart();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Thin Lines");
        chart = new TestDataBar_thinLines().getChart();
        charts.add(chart);
        p.add(chart);
        
        
        
        
        p = createTabbedPane(tabbedPaneBar, "Bar - 2 Y Axes");
        chart = new TestDataBar_2Y().getChart();
        charts.add(chart);
        p.add(chart);
        
//        p = createTabbedPane(tabbedPaneBar, "MultiBar - side by side");
//        chart = new TestDataBar_MultiBar_SideBySide().getChart();
//        charts.add(chart);
//        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "MultiBar - stacked");
        chart = new TestDataBar_MultiBar_Stacked().getChart();
        charts.add(chart);
        p.add(chart);
        
        


        


//        tabbedPaneTime       
        JButton b = new JButton("Create PNG");
        
        getContentPane().add(tabbedPane);
        getContentPane().add(b, BorderLayout.SOUTH);

        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i = 0;
				for (JComponent chart2 : charts) {
					
					int width = 0;
					int height = 0;
					
					
					if (chart2 instanceof Chart) {
						width = ((Chart)chart2).getWidth(); 
						height = ((Chart)chart2).getHeight();
					}else {
						width = chart2.getWidth(); 
						height = chart2.getHeight();
					}
					
					BufferedImage image = new BufferedImage(width, height, 
							BufferedImage.TYPE_INT_ARGB);

					Graphics g2 = image.getGraphics();
					chart2.paint(g2);
					
//					System.out.println("saving ");   
//					
//					g2.setColor(Color.GREEN);
//					g2.drawRect(4, 4, width -2, height-2);
//					System.out.println("saving ");

					try {
						ImageIO.write(image, "PNG", new File("src\\main\\resources\\screenshots\\chart-image-" + i + ".png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("saving ");
					i++;
				}
			}
		});
        
        setSize(1300, 800);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws Exception {
        BarsTester frame = new BarsTester();
        frame.setVisible(true);
    }

    private JPanel createTabbedPane(JTabbedPane tabbedPane, String string) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        tabbedPane.add(string, panel);
        return panel;
    }

}
