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
import com.bluewalrus.main.test.TestDataBubble;
import com.bluewalrus.main.test.TestDataLineScatter;
import com.bluewalrus.main.test.TestDataPie;
import com.bluewalrus.main.test.TestDataPieBubble;

public class IcebergChartsDemo extends JFrame {

    public IcebergChartsDemo() {

    	final ArrayList<JComponent> charts = new ArrayList<JComponent>();
    	
        JTabbedPane tabbedPane = new JTabbedPane();
    	
        JTabbedPane tabbedPaneBar = new JTabbedPane();
        JTabbedPane tabbedPaneLine = new JTabbedPane();
        JTabbedPane tabbedPanePie = new JTabbedPane();
        
        JPanel p = null;
        JComponent chart = null;
        

        
        
        p = createTabbedPane(tabbedPaneBar, "Bar - postitive negative");
        chart = TestDataBar.getTestData_BarNegativeAndPositive();
        charts.add(chart);
        p.add(chart);
        
//        setSize(1300, 800);
//        
//        if(true)
//        	return;
        
        p = createTabbedPane(tabbedPaneBar, "Bar - Can be XY");
        chart = TestDataBar.getTestData_BarCanBeXY();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Thin Lines");
        chart = TestDataBar.getTestData_thinLines();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "Bar - 2 Y Axes");
        chart = TestDataBar.getTestData_Bar2Y();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneBar, "MultiBar - side by side");
        chart = TestDataBar.getTestData_MultiBar_SideBySide();
        charts.add(chart);
        p.add(chart);
        

        p = createTabbedPane(tabbedPaneBar, "MultiBar - stacked");
        chart = TestDataBar.getTestData_MultiBar_Stacked();
        charts.add(chart);
        p.add(chart);
        
        
        p = createTabbedPane(tabbedPaneBar, "Font fun");
        chart = TestDataBar.getTestData_FontFun();
        charts.add(chart);
        p.add(chart);
        
        
        
        ///////////////
        
        
        p = createTabbedPane(tabbedPaneLine, "XY Chart");
        chart = TestDataLineScatter.getTestData_SomeKindOfXY();
        charts.add(chart);
        p.add(chart);

        
        
        
        p = createTabbedPane(tabbedPanePie, "Multi Level Pie");
        chart = TestDataPie.getTestData_multiLevelPie();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPanePie, "Simple Pie");
        chart = TestDataPie.getTestData_SimplePie();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPanePie, "Simple Indicator");
        chart = TestDataPie.getTestData_SimpleIndicator();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPanePie, "MultiLevel Indicator");
        chart = TestDataPie.getTestData_MultiIndicator();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneLine, "Scatter chart");
        chart = TestDataLineScatter.getTestData_Scatter();
        charts.add(chart);
        p.add(chart);

        p = createTabbedPane(tabbedPaneLine, "Bubble");
        chart = TestDataBubble.getTestData_Bubble();
        charts.add(chart);
        p.add(chart);
        

        p = createTabbedPane(tabbedPaneLine, "Bubble2");
        chart = TestDataBubble.getTestData_Bubble2();
        charts.add(chart);
        p.add(chart);

        p = createTabbedPane(tabbedPaneLine, "Pie Bubble");
        chart = TestDataPieBubble.getTestData_Bubble2();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneLine, "Box Plot");
        chart = TestDataLineScatter.getTestData_BoxPlot();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPaneLine, "Math");
        chart = TestDataLineScatter.getTestData_Math();
        charts.add(chart);
        p.add(chart);
        
        
        
        p = createTabbedPane(tabbedPaneLine, "Lines");
        chart = TestDataLineScatter.getTestData_LineExamples();
        charts.add(chart);
        p.add(chart);
        
        



        tabbedPane.add("Scatter Line Charts", tabbedPaneLine);
        tabbedPane.add("Bar Charts", tabbedPaneBar);
        tabbedPane.add("Pie Charts", tabbedPanePie);
        
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

    public static void main(String[] args) {
        IcebergChartsDemo frame = new IcebergChartsDemo();
        frame.setVisible(true);
    }

    private JPanel createTabbedPane(JTabbedPane tabbedPane, String string) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        tabbedPane.add(string, panel);
        return panel;
    }

}
