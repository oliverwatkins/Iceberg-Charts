package com.bluewalrus.main.test.logarithmic;

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

import com.bluewalrus.chart.Chart;

public class LogTester extends JFrame {

	
    public static void main(String[] args) throws Exception {
    	LogTester frame = new LogTester();
        frame.setVisible(true);
    }
    
	/**
	 * 
	 * @throws Exception
	 */
    public LogTester() throws Exception {
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

        p = createTabbedPane(tabbedPaneBar, "Simple Log ");
        chart = new TestDataLog_1().getChart();
        charts.add(chart);
        p.add(chart);

        
//        p = createTabbedPane(tabbedPaneBar, "Double");
//        chart = new TestDataXY_Polynomals().getChart();
//        charts.add(chart);
//        p.add(chart);
//        
//        p = createTabbedPane(tabbedPaneBar, "Sin/Cos");
//        chart = new TestDataXY_SineCurve().getChart();
//        charts.add(chart);
//        p.add(chart);
        


//        JButton b = new JButton("Create PNG");
//
//        getContentPane().add(b, BorderLayout.SOUTH);
//
//        b.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				int i = 0;
//				for (JComponent chart2 : charts) {
//					
//					int width = 0;
//					int height = 0;
//					
//					
//					if (chart2 instanceof Chart) {
//						width = ((Chart)chart2).getWidth(); 
//						height = ((Chart)chart2).getHeight();
//					}else {
//						width = chart2.getWidth(); 
//						height = chart2.getHeight();
//					}
//					
//					BufferedImage image = new BufferedImage(width, height, 
//							BufferedImage.TYPE_INT_ARGB);
//
//					Graphics g2 = image.getGraphics();
//					chart2.paint(g2);
//					
////					System.out.println("saving ");   
////					
////					g2.setColor(Color.GREEN);
////					g2.drawRect(4, 4, width -2, height-2);
////					System.out.println("saving ");
//
//					try {
//						ImageIO.write(image, "PNG", new File("src\\main\\resources\\screenshots\\chart-image-" + i + ".png"));
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					System.out.println("saving ");
//					i++;
//				}
//			}
//		});
        
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
