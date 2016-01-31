package com.bluewalrus.main;

import java.awt.BorderLayout;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.main.test.TestDataTimeSeries;

public class SingleChartDemo extends JFrame {

    public SingleChartDemo() throws ParseException {
    	
        JTabbedPane tabbedPaneBar = new JTabbedPane();
  
        
//        Chart chart2 = TestDataBar.getTestData_Bar2Y();

        
        Chart chart2 = TestDataTimeSeries.getTestData_TimeSeries2();
        
        
//        Chart chart2 = TestDataLineScatter.getTestData_SomeKindOfXY();

//        tabbedPaneBar.addTab("1", chart);

        tabbedPaneBar.addTab("2", chart2);
        
        getContentPane().add(tabbedPaneBar);
        
        setSize(1300, 800);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws Exception {
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
