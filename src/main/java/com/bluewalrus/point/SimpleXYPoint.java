package com.bluewalrus.point;

import java.awt.Color;

public abstract class SimpleXYPoint extends XYPoint{

	
    public SimpleXYPoint(Color color) {
		super(color);
	}
    
    public SimpleXYPoint(Color color, int size, double transparancyFraction) {
		super(size, color, transparancyFraction);
	}
}
