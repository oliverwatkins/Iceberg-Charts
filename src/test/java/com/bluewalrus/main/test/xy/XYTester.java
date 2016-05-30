package com.bluewalrus.main.test.xy;

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

public class XYTester extends JFrame {

	
    public static void main(String[] args) throws Exception {
    	XYTester frame = new XYTester();
        frame.setVisible(true);
    }
    
	/**
	 * 
	 * @throws Exception
	 */
    public XYTester() throws Exception {
    	JTabbedPane p = createPanel();
    	
        getContentPane().add(p);

    }
    	
    	
	public JTabbedPane createPanel() {
    		

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

        p = createTabbedPane(tabbedPaneBar, "Simple XY");
        chart = new TestDataXY_Simple().getChart();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPaneBar, "Simple XY Series");
        chart = new TestDataXY_Simple_Series().getChart();
        charts.add(chart);
        p.add(chart);

        p = createTabbedPane(tabbedPaneBar, "Scatter");
        chart = new TestDataXY_Scatter().getChart();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Line Examples");
        chart = new TestDataXY_LineExamples().getChart();
        charts.add(chart);
        p.add(chart);

        p = createTabbedPane(tabbedPaneBar, "Candle Plot");
        chart = new TestDataXY_CandlePlot().getChart();
        charts.add(chart);
        p.add(chart);

        p = createTabbedPane(tabbedPaneBar, "Box Plot");
        chart = new TestDataXY_Boxplot().getChart();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Font Fun");
        chart = new TestDataBar_FontFun().getChart();
        charts.add(chart);
        p.add(chart);
        
        
        JButton b = new JButton("Create PNG");

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
        
        
        return tabbedPaneBar;
    }



    private JPanel createTabbedPane(JTabbedPane tabbedPane, String string) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        tabbedPane.add(string, panel);
        return panel;
    }

}
