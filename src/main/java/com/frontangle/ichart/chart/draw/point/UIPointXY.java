package com.frontangle.ichart.chart.draw.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

import com.frontangle.ichart.chart.ChartUtils;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;

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
	
	
	protected void clipAndDrawPoint(Graphics2D g, XYChart chart) {
		if (chart != null) { 
			
			Shape cachedClip = ChartUtils.clipChart(g, chart);

			drawPoint(g);
			
			g.setClip(cachedClip);
			
		}else { //in legend
			drawPoint(g);
		}
	}
	
    public abstract void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor, XYChart chart, int pixBtnFirst2Pts);

    public abstract void drawPoint(Graphics2D g);
    
    
    /**
     * Checks if the point is contained within the shape of the Point. For example
     * looks at whether the X,Y lie within the bounds of a rectangle if the point
     * is a square.
     * 
     * TODO how should this work if the point is linear, like the whiskers of a box plot?
     * TODO should this be optional to implement?
     * 
     * @param point ui point
     * @return boolean shape contains point
     */
	public abstract boolean doesShapeContainPoint(Point point);

}
