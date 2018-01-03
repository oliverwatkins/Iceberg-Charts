package com.frontangle.ichart.main;

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

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.main.test.bar.BarsTester;
import com.frontangle.ichart.main.test.bubble.BubbleTester;
import com.frontangle.ichart.main.test.fractions.FractionTester;
import com.frontangle.ichart.main.test.gridlinefill.GridLineTester;
import com.frontangle.ichart.main.test.logarithmic.LogTester;
import com.frontangle.ichart.main.test.math.MathTester;
import com.frontangle.ichart.main.test.math.TestDataXY_Math;
import com.frontangle.ichart.main.test.multibar.MultiBarTester;
import com.frontangle.ichart.main.test.pie.TestDataPie_IndicatorMany;
import com.frontangle.ichart.main.test.pie.TestDataPie_IndicatorSimple;
import com.frontangle.ichart.main.test.pie.TestDataPie_Multi;
import com.frontangle.ichart.main.test.pie.TestDataPie_SimplePie;
import com.frontangle.ichart.main.test.stacked.StackedTester;
import com.frontangle.ichart.main.test.timeseries.TimeSeriesTester;
import com.frontangle.ichart.main.test.xy.TestDataXY_Boxplot;
import com.frontangle.ichart.main.test.xy.TestDataXY_LineExamples;
import com.frontangle.ichart.main.test.xy.TestDataXY_Scatter;
import com.frontangle.ichart.main.test.xy.TestDataXY_Simple;
import com.frontangle.ichart.main.test.xy.TestDataXY_Simple_Series;
import com.frontangle.ichart.main.test.xy.XYTester;
import com.frontangle.ichart.main.test.xyy.TestDataBar_2Y;
import com.frontangle.ichart.main.test.xyy.TestDataBar_2Y_2;

public class ChartShowcase extends JFrame {

	/**
	 * 
	 * @throws Exception
	 */
    public ChartShowcase() throws Exception {

    	final ArrayList<JComponent> charts = new ArrayList<JComponent>();
    	
        JTabbedPane tabbedPane = new JTabbedPane();
        JTabbedPane tabbedPanePie = new JTabbedPane();
        JTabbedPane tabbedPaneXYY = new JTabbedPane();

        JPanel p = null;
        JComponent chart = null;

        /**
         * XY
         */
        tabbedPane.add("XY", new XYTester().createPanel());
        
        /**
         * TIME
         */
        tabbedPane.add("Time Series", new TimeSeriesTester().createPanel());
        /**
         * GRIDS
         */
        tabbedPane.add("Grid Line Fill", new GridLineTester().createPanel());
        /***
         * BARS
         */
        tabbedPane.add("Bar Charts", new BarsTester().createPanel());
        /***
         * MATH
         */
        tabbedPane.add("Maths", new MathTester().createPanel());
        /***
         * STACKED
         */
        tabbedPane.add("Stacked", new StackedTester().createPanel());
        /***
         * MULTI BAR
         */
        tabbedPane.add("Multi Bar", new MultiBarTester().createPanel());
        /***
         * BUBBLE
         */
        tabbedPane.add("Bubble", new BubbleTester().createPanel());
        /***
         * Log
         */
        tabbedPane.add("Logarithimc", new LogTester().createPanel());
        
        tabbedPane.add("Pie Charts", tabbedPanePie);
        
        tabbedPane.add("XYY", tabbedPaneXYY);
        
        /**
         * PIE
         */
        p = createTabbedPane(tabbedPanePie, "Multi Level Pie");
        chart = new TestDataPie_Multi().getChart();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPanePie, "Simple Pie");
        chart = new TestDataPie_SimplePie().getChart();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPanePie, "Simple Indicator");
        chart = new TestDataPie_IndicatorSimple().getChart();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPanePie, "MultiLevel Indicator");
        chart = new TestDataPie_IndicatorMany().getChart();
        charts.add(chart);
        p.add(chart);
        
        
        /**
         * Fractions and Negatives
         */
        
        tabbedPane.add("Fractions/Decimals and Negatives", new FractionTester().createPanel());
        

        /**
         * XYY Charts
         */
        p = createTabbedPane(tabbedPaneXYY, "XYY ");
        chart = new TestDataBar_2Y().getChart();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneXYY, "XYY 2");
        chart = new TestDataBar_2Y_2().getChart();
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
