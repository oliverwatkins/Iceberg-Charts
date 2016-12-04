package com.bluewalrus.chart.legend;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.bluewalrus.chart.Category;
import com.bluewalrus.chart.Chart;

public class AbstractLegend {

    Color legendBackgroundColor = new Color(243, 239, 239);

    public Font legendFont = new Font("Arial", Font.PLAIN, 10);

    protected ArrayList<Category> categories = new ArrayList<Category>();
    
    Chart chart;
    
    public AbstractLegend(Font legendFont, Chart chart) {
        this.chart = chart;
        this.legendFont = legendFont;
    }


	
}
