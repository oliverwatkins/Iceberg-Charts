package com.frontangle.ichart.chart.draw.point;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.frontangle.ichart.chart.XYChart;

public abstract class UIPointAbstractBar extends UIPointComplexXY {

	/**
	 * @deprecated note this should be deprecated. Use % instead. This is only used in UIPointMBSideBySide. Should somehow
	 * be refactored only into that class, or the UIPoint should use %.
	 */
   protected int barWidthInPixels = 15; 

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
