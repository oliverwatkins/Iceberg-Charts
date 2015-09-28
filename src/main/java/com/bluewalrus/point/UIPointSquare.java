package com.bluewalrus.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.renderer.XYFactor;

public class UIPointSquare extends UIPointSimpleXY {

    public UIPointSquare(Color color) {
        super(color);
    }

    public UIPointSquare(Color color, int size) {
        super(color, size, .7);
    }

    public UIPointSquare(Color color, int size, double transparancyFraction) {
        super(color, size, transparancyFraction);
    }

    public void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor) {

        int alpha = (int) (transparancyFraction * 255);
        Color c = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);

        g.setColor(c);
        

        Rectangle rec = new Rectangle((int) point.x - radiusOrWidthOfPointShape / 2,
                point.y - radiusOrWidthOfPointShape / 2,
                radiusOrWidthOfPointShape,
                radiusOrWidthOfPointShape
        );
        
        g.draw(rec);

        
//        g.fillRect((int) point.x - radiusOrWidthOfPointShape / 2,
//                point.y - radiusOrWidthOfPointShape / 2,
//                radiusOrWidthOfPointShape,
//                radiusOrWidthOfPointShape
//        );
    }

	@Override
	public boolean doesShapeContainPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public UIPointXY createNewInstanceOfSelf() throws CloneNotSupportedException {
//		return (UIPointXY)this.clone();
//	}

}
