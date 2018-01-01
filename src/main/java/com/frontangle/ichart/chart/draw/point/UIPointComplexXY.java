package com.frontangle.ichart.chart.draw.point;

import java.awt.Color;

public abstract class UIPointComplexXY extends UIPointXY {

    public UIPointComplexXY() {
        super();
    }

    public UIPointComplexXY(Color color) {
        super(color);
    }

    public UIPointComplexXY(Color color, int size) {
        super(size, color);
    }

}
