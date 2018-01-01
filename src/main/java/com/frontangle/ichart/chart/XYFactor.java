package com.frontangle.ichart.chart;


//Convert real values to pixels.

public class XYFactor {
	
	/**
	 * Converting factor. Basically equal to :
	 *
	 *  (1 / xMax - xMin) * chart.widthChart
	 * 
	 */
	private double xFactor; //x value into pixels converting factor

	private double yFactor; //y value into pixels converting factor
	
	public double yZeroOffsetInPixel = 10;
	
	/**
	 * An offset value that is used if the Min value is not zero. This value should be 
	 * negative as it is used to push the point to the left (for x). If a point has a value 1, but the x-min
	 * value is 10, then the point should be pulled to the left (and out of sight). Same goes for y.
	 * 
	 * It is equal to negative of (xMin / (xMax - xMin)) * chart.widthChart
	 * 
	 * Which is equal to the minimum value multiplied by the pixel converting factor. (which makes sense)
	 * 
	 */
	
	public double xZeroOffsetInPixel = 200; //what is this???
	
	public XYFactor(double xFactor, double yFactor) {
		
		if (xFactor < 0)
			throw new IllegalArgumentException("cannot be negative");

		this.xFactor = xFactor;
		this.yFactor = yFactor;
	}
	
	public double getxFactor() {
		return xFactor;
	}

	public void setxFactor(double xFactor) {
		this.xFactor = xFactor;
	}

	public double getyFactor() {
		return yFactor;
	}

	public void setyFactor(double yFactor) {
		this.yFactor = yFactor;
	}
}
