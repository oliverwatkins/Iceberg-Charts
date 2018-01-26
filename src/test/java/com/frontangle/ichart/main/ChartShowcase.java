package com.frontangle.ichart.main;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.frontangle.ichart.main.test.area.AreaTester;
import com.frontangle.ichart.main.test.bar.BarsTester;
import com.frontangle.ichart.main.test.bubble.BubbleTester;
import com.frontangle.ichart.main.test.fractions.FractionTester;
import com.frontangle.ichart.main.test.gridlinefill.GridLineTester;
import com.frontangle.ichart.main.test.logarithmic.LogTester;
import com.frontangle.ichart.main.test.math.MathTester;
import com.frontangle.ichart.main.test.multibar.MultiBarTester;
import com.frontangle.ichart.main.test.pie.PieTester;
import com.frontangle.ichart.main.test.pie.TestDataPie_IndicatorMany;
import com.frontangle.ichart.main.test.pie.TestDataPie_IndicatorSimple;
import com.frontangle.ichart.main.test.pie.TestDataPie_Multi;
import com.frontangle.ichart.main.test.pie.TestDataPie_SimplePie;
import com.frontangle.ichart.main.test.stacked.StackedTester;
import com.frontangle.ichart.main.test.timeseries.TimeSeriesTester;
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
         * AREA
         */
        tabbedPane.add("Area Charts", new AreaTester().createPanel());
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
        
        /***
         * Pie
         */
        tabbedPane.add("Pie Charts", new PieTester().createPanel());
        
        
//        tabbedPane.add("Pie Charts", tabbedPanePie);
        
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
        chart = new TestDataPie_IndicatorMany().getChartPanel();
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
        
        getContentPane().add(tabbedPane);

        setSize(1300, 800);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws Exception {
//        IcebergChartsDemo frame = new IcebergChartsDemo();
    	ChartShowcase frame = new ChartShowcase();
        frame.setVisible(true);
    }

    private JPanel createTabbedPane(JTabbedPane tabbedPane, String string) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        tabbedPane.add(string, panel);
        return panel;
    }

}
