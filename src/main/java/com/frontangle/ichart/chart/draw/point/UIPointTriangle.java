package com.frontangle.ichart.chart.draw.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

import com.frontangle.ichart.chart.ChartUtils;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;

public class UIPointTriangle extends UIPointSimpleXY{

	
    private int[] xPoints;
	private int[] yPoints;
	private int numberPoints;

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
	public void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor,
			XYChart chart, int pixBtnFirst2Pts) {
		
		int alpha = (int)(transparancyFraction * 255);
		Color c = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
		
    	g.setColor(c);
    	
    	int w = radiusOrWidthOfPointShape/2;
    	
    	xPoints = new int[]{point.x - w, point.x, point.x + w};
    	yPoints = new int[]{point.y + w, point.y - w, point.y + w};
    	numberPoints = 3;
    	
    	clipAndDrawPoint(g, chart);
	}
	
	@Override
	public void drawPoint(Graphics2D g) {
		
		g.fillPolygon(xPoints, yPoints, numberPoints);
	}
	
	@Override
	public boolean doesShapeContainPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}


}
