/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.main.test.pie;
import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.main.test.ChartTester;
import com.bluewalrus.pie.Segment;
import com.bluewalrus.pie.SimpleIndicatorPieChart;

public class TestDataPie_IndicatorSimple extends ChartTester {



	public SimpleIndicatorPieChart getChart() {

        ArrayList<Segment> values = new ArrayList<Segment>();
        values.add(new Segment(15, Color.RED));
        values.add(new Segment(52, Color.BLUE));
        values.add(new Segment(33, Color.GREEN));
    	
    	SimpleIndicatorPieChart pieChart = new SimpleIndicatorPieChart(34, values, "Simple Indicator");
        
        return pieChart;
    }

}
