package com.bluewalrus.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.renderer.XYFactor;

public class UIPointTriangle extends UIPointSimpleXY{

	
    public UIPointTriangle(Color color) {
		super(color);
	}
    public UIPointTriangle(Color color, int size) {
		super(color, size, 1);
	}
    public UIPointTriangle(Color color, int size, double transparancyFraction) {
		super(color, size, transparancyFraction);
	}
    


	@Override
	public void draw(Graphics2D g, Point point, DataPoint dataPoint,
			XYFactor xyFactor) {
		
		
		int alpha = (int)(transparancyFraction * 255);
		Color c = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
		
    	g.setColor(c);
    	
    	int w = radiusOrWidthOfPointShape/2;
		
    	
    	int[] xPoints = new int[]{point.x - w, point.x, point.x + w};
    	int[] yPoints = new int[]{point.y + w, point.y - w, point.y + w};
    	int numberPoints = 3;
    	
    	g.fillPolygon(xPoints, yPoints, numberPoints);
		
	}

}
