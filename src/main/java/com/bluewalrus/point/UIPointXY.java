package com.bluewalrus.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.renderer.XYFactor;

/**
 * Base class for all X/Y UI points. 
 * 
 * @author Oliver Watkins
 */
public abstract class UIPointXY implements Serializable, Cloneable {

    public int radiusOrWidthOfPointShape = 10; //default
    public Color color; //default color
    public double transparancyFraction = 0.7;

    public UIPointXY() {
        super();
    }
    
    public UIPointXY(Color color) {
        super();
        this.color = color;
    }

    public UIPointXY(int width, Color color) {
        super();
        this.radiusOrWidthOfPointShape = width;
        this.color = color;
    }

    public UIPointXY(int width, Color color, double transparancyFraction) {
        super();
        this.radiusOrWidthOfPointShape = width;
        this.color = color;
        this.transparancyFraction = transparancyFraction;
    }

	
	public UIPointXY createNewInstanceOfSelf() throws CloneNotSupportedException {
		return (UIPointXY)this.clone();
	}

	
	
    public abstract void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor);

    /**
     * Checks if the point is contained within the shape of the Point. For example
     * looks at whether the X,Y lie within the bounds of a rectangle if the point
     * is a square.
     * 
     * TODO how should this work if the point is linear, like the whiskers of a box plot?
     * TODO should this be optional to implement?
     * 
     * @param point
     * @return boolean
     */
	public abstract boolean doesShapeContainPoint(Point point);



}
