package com.bluewalrus.point;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.bluewalrus.chart.XYChart;

public abstract class UIPointAbstractBar extends UIPointComplexXY {

    int barWidth = 10;
    int barWidthPercent = 0;

    public UIPointAbstractBar(Color color, int size) {
        super(color, size);
    }

    public UIPointAbstractBar(Color color) {
        super(color);
    }
}
