package com.bluewalrus.chart.draw.point;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.bluewalrus.chart.XYChart;

public abstract class UIPointAbstractBar extends UIPointComplexXY {

	/**
	 * @deprecated note this should be deprecated. Use % instead
	 */
    int barWidthInPixels = 15; 
    double barWidthPercent = 0;
    
    public UIPointAbstractBar() {
        super();
    }

    public UIPointAbstractBar(Color color, int size) {
        super(color, size);
    }

    public UIPointAbstractBar(Color color) {
        super(color);
    }
}
