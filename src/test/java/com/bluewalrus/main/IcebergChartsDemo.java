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
import com.bluewalrus.main.test.TestDataBubble_1_guns;
import com.bluewalrus.main.test.TestDataBubble_2_series;
import com.bluewalrus.main.test.TestDataPieBubble;
import com.bluewalrus.main.test.TestDataTimeSeries;
import com.bluewalrus.main.test.TestDataXY_Boxplot;
import com.bluewalrus.main.test.TestDataXY_LineExamples;
import com.bluewalrus.main.test.TestDataXY_Math;
import com.bluewalrus.main.test.TestDataXY_Scatter;
import com.bluewalrus.main.test.TestDataXY_Simple;
import com.bluewalrus.main.test.TestDataXY_Simple_Series;
import com.bluewalrus.main.test.bar.BarsTester;
import com.bluewalrus.main.test.bar.TestDataBar_1_Simple;
import com.bluewalrus.main.test.bar.TestDataBar_2_Simple_negative;
import com.bluewalrus.main.test.bar.TestDataBar_3_Simple_fixed_axis;
import com.bluewalrus.main.test.bar.TestDataBar_4_GradientColor;
import com.bluewalrus.main.test.bar.TestDataBar_5_PosNegColor;
import com.bluewalrus.main.test.fractions.FractionTester;
import com.bluewalrus.main.test.fractions.TestDataXY_Fractions;
import com.bluewalrus.main.test.fractions.TestDataXY_Fractions2;
import com.bluewalrus.main.test.fractions.TestDataXY_Fractions3_2decimal;
import com.bluewalrus.main.test.fractions.TestDataXY_Fractions4;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_Gradiant;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_GraphPaper;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_TimeSeries;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_alternateGridFillX;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_alternateGridFillY;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_gridSimple;
import com.bluewalrus.main.test.pie.TestDataPie_IndicatorMany;
import com.bluewalrus.main.test.pie.TestDataPie_IndicatorSimple;
import com.bluewalrus.main.test.pie.TestDataPie_Multi;
import com.bluewalrus.main.test.pie.TestDataPie_SimplePie;
import com.bluewalrus.main.test.xyy.TestDataBar_2Y;
import com.bluewalrus.main.test.xyy.TestDataBar_2Y_2;

public class IcebergChartsDemo extends JFrame {

	/**
	 * 
	 * @throws Exception
	 */
    public IcebergChartsDemo() throws Exception {

    	final ArrayList<JComponent> charts = new ArrayList<JComponent>();
    	
        JTabbedPane tabbedPane = new JTabbedPane();
    	
        JTabbedPane tabbedPaneBar = new JTabbedPane();
        JTabbedPane tabbedPaneLine = new JTabbedPane();
        JTabbedPane tabbedPanePie = new JTabbedPane();
        JTabbedPane tabbedPaneGridFills = new JTabbedPane();
        JTabbedPane tabbedPaneTime = new JTabbedPane();
        JTabbedPane tabbedPaneXYY = new JTabbedPane();
        
        
        tabbedPane.add("General XY Charts", tabbedPaneLine);
        tabbedPane.add("Time Axis", tabbedPaneTime);
        tabbedPane.add("Bar Charts", tabbedPaneBar);
        tabbedPane.add("Pie Charts", tabbedPanePie);
        tabbedPane.add("Grids And Fills", tabbedPaneGridFills);
        tabbedPane.add("XYY", tabbedPaneXYY);

        JPanel p = null;
        JComponent chart = null;
        
        /**
         * XY
         */
        
        p = createTabbedPane(tabbedPaneLine, "XY Simple");
        chart = new TestDataXY_Simple().getChart();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneLine, "XY Chart");
        chart = new TestDataXY_Simple_Series().getChart();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneLine, "Scatter chart");
        chart = new TestDataXY_Scatter().getChart();
        charts.add(chart);
        p.add(chart);

        p = createTabbedPane(tabbedPaneLine, "Bubble");
        chart = new TestDataBubble_2_series().getChart();
        charts.add(chart);
        p.add(chart);
        

        p = createTabbedPane(tabbedPaneLine, "Bubble2");
        chart = new TestDataBubble_1_guns().getChart();
        charts.add(chart);
        p.add(chart);

        p = createTabbedPane(tabbedPaneLine, "Pie Bubble");
        chart = new TestDataPieBubble().getChart();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneLine, "Box Plot");
        chart = new TestDataXY_Boxplot().getChart();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPaneLine, "Math");
        chart = new TestDataXY_Math().getChart();
        charts.add(chart);
        p.add(chart);
        
        
        
        p = createTabbedPane(tabbedPaneLine, "Lines");
        chart = new TestDataXY_LineExamples().getChart();
        charts.add(chart);
        p.add(chart);
 
        /**
         * TIME
         */
        
        p = createTabbedPane(tabbedPaneTime, "Time Series 1 ");
        chart = new TestDataTimeSeries().getChart();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneTime, "Time Series 2 ");
        chart = new TestDataTimeSeries().getChart();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneTime, "Grids Time Series");
        chart = new TestDataGrids_TimeSeries().getChart();
        charts.add(chart);
        p.add(chart);
        
        

        /**
         * GRIDS
         */

        p = createTabbedPane(tabbedPaneGridFills, "Grids Graph Paper");
        chart = new TestDataGrids_gridSimple().getChart();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPaneGridFills, "Grids Graph Paper");
        chart = new TestDataGrids_GraphPaper().getChart();
        charts.add(chart);
        p.add(chart);

        
        p = createTabbedPane(tabbedPaneGridFills, "Grid Alternate X Fill");
        chart = new TestDataGrids_alternateGridFillX().getChart();
        charts.add(chart);
        p.add(chart);

        p = createTabbedPane(tabbedPaneGridFills, "Grid Alternate Y Fill");
        chart = new TestDataGrids_alternateGridFillY().getChart();
        charts.add(chart);
        p.add(chart);
        
        p = createTabbedPane(tabbedPaneGridFills, "Gradiant Fill");
        chart = new TestDataGrids_Gradiant().getChart();
        charts.add(chart);
        p.add(chart);
        
        
        
        /***
         * BARS
         */
        
        tabbedPane.add("Bar Charts2", new BarsTester().createPanel());
        

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
