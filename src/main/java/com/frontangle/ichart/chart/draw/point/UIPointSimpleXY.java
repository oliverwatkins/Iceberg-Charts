package com.frontangle.ichart.chart.draw.point;

import java.awt.Color;

public abstract class UIPointSimpleXY extends UIPointXY{

	
    public UIPointSimpleXY(Color color) {
		super(color);
	}
    
    public UIPointSimpleXY(Color color, int size, double transparancyFraction) {
		super(size, color, transparancyFraction);
	}
}
