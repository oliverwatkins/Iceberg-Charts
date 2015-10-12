package com.bluewalrus.renderer;


//Convert real values to pixels.

public class XYFactor {
	public double xFactor; 
	public double yFactor;
	
	public double yZeroOffsetInPixel = 10;
	public double xZeroOffsetInPixel = 200;
	
	public XYFactor(double xFactor, double yFactor) {
		this.xFactor = xFactor;
		this.yFactor = yFactor;
	}
}
