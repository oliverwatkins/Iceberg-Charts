package com.bluewalrus.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.renderer.XYFactor;

public class UIPointCircle extends UIPointSimpleXY {

    public UIPointCircle(Color color) {
        super(color);
    }

    public UIPointCircle(Color color, int size) {
        super(color, size, .7);
    }

    public UIPointCircle(Color color, int size, double transparancyFraction) {
        super(color, size, transparancyFraction);
    }

    public void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor) {

        int alpha = (int) (transparancyFraction * 255);
        Color c = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);

        g.setColor(c);

        g.fillOval((int) point.x - radiusOrWidthOfPointShape / 2,
                point.y - radiusOrWidthOfPointShape / 2,
                radiusOrWidthOfPointShape,
                radiusOrWidthOfPointShape
        );
    }

	@Override
	public boolean doesShapeContainPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

}
