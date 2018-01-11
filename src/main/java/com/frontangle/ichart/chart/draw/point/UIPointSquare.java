package com.frontangle.ichart.chart.draw.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;

/**
 * UI for a square shape
 * 
 * @author Oliver Watkins
 */
public class UIPointSquare extends UIPointSimpleXY {

	//shape that represents the point
    private Rectangle rectangle;
    
    boolean mouseIsOverPoint = false;
    
	public UIPointSquare(Color color) {
        super(color);
    }

    public UIPointSquare(Color color, int size) {
        super(color, size, .7);
    }

    public UIPointSquare(Color color, int size, double transparancyFraction) {
        super(color, size, transparancyFraction);
    }

    public void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor, XYChart chart, int pixBtnFirst2Pts) {

        int alpha = (int) (transparancyFraction * 255);
        Color c = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);

        g.setColor(c);

        rectangle = new Rectangle((int) point.x - radiusOrWidthOfPointShape / 2,
                point.y - radiusOrWidthOfPointShape / 2,
                radiusOrWidthOfPointShape,
                radiusOrWidthOfPointShape
        );
        
        clipAndDrawPoint(g, chart);
    }
    
    
    
	@Override
	public void drawPoint(Graphics2D g) {
		
        g.fill(rectangle);
        
        if (mouseIsOverPoint) {
        	g.setColor(Color.ORANGE);
        	g.fill(rectangle);
        }else {
        	g.setColor(color);
        	g.fill(rectangle);
        }
	}
	
	

    
	@Override
	public boolean doesShapeContainPoint(Point point) {
		
		if (rectangle.contains(point)) {
			mouseIsOverPoint = true;
			return true;
		}else {
			mouseIsOverPoint = false;
			return false;
		}
	}



}
