package com.bluewalrus.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.renderer.XYFactor;

public abstract class UIPointXY implements Serializable, Cloneable {

    public int radiusOrWidthOfPointShape = 10; //default
    public Color color;
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

	public abstract boolean doesShapeContainPoint(Point point);



}
