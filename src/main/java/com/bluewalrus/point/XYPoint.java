

package com.bluewalrus.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.renderer.XYFactor;

/**
 * 
 * @author Oliver Watkins
 */
public abstract class XYPoint {

    public int radiusOrWidthOfPointShape = 10; //default
    public Color color;
    public double transparancyFraction = 0.7;
    
    public XYPoint(Color color) {
		super();
		this.color = color;
	}
    
	public XYPoint(int width, Color color) {
		super();
		this.radiusOrWidthOfPointShape = width;
		this.color = color;
	}
	public XYPoint(int width, Color color, double transparancyFraction) {
		super();
		this.radiusOrWidthOfPointShape = width;
		this.color = color;
		this.transparancyFraction = transparancyFraction;
	}
	
	
	public abstract void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor);
    

}
