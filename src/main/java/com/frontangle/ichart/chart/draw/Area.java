package com.frontangle.ichart.chart.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

public class Area {

	public Color color = Color.RED;

	public Area(Color color) {
		this.color = color;
	}

	public void drawArea(Graphics2D g, ArrayList<Point> points) {

		Polygon p = new Polygon();
		
		Point firstPoint = points.get(0);
		
		Point lastPoint = points.get(points.size()-1);
		
//		p.addPoint(firstPoint.x, 400);
		
		for (int i = 0; i < points.size(); i++) {
			p.addPoint(points.get(i).x, points.get(i).y);
		}
		
//		p.addPoint(lastPoint.x, 0);
		

		g.setColor(color);
		g.fillPolygon(p);
	}

}
