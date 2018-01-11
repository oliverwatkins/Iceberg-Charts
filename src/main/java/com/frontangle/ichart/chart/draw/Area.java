package com.frontangle.ichart.chart.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

public class Area {

	public Color color = Color.RED;

	public void drawArea(Graphics2D g, ArrayList<Point> points) {

		Polygon p = new Polygon();
		for (int i = 0; i < points.size(); i++) {
			p.addPoint(points.get(i).x, points.get(i).y);
		}

		g.setColor(color);
		g.fillPolygon(p);
	}

}
