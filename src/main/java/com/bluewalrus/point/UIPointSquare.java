package com.bluewalrus.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.renderer.XYFactor;

public class UIPointSquare extends UIPointSimpleXY {

    private Rectangle rectangle;

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
        

        rectangle = new Rectangle((int) point.x - radiusOrWidthOfPointShape / 2,
                point.y - radiusOrWidthOfPointShape / 2,
                radiusOrWidthOfPointShape,
                radiusOrWidthOfPointShape
        );
        
        g.fill(rectangle);
    
        if (mouseIsOverPoint) {
        	
        	g.setColor(Color.GREEN);
        	g.fill(rectangle);
        	System.out.println("Draw11");
//        	g.draw(rectangle);
        	
        }else {
        	g.setColor(Color.PINK);
        	g.fill(rectangle);
        	System.out.println("Draw22");
        	
        }
        
//		if (b)
//			System.out.println("CONTAINS POINT!!!!!!!!!!!!!!!!!!!!!!!!!!");
        


        
//        g.fillRect((int) point.x - radiusOrWidthOfPointShape / 2,
//                point.y - radiusOrWidthOfPointShape / 2,
//                radiusOrWidthOfPointShape,
//                radiusOrWidthOfPointShape
//        );
    }

    boolean mouseIsOverPoint = false;
    
	@Override
	public boolean doesShapeContainPoint(Point point) {
		
		if (rectangle.contains(point)) {
			
			System.out.println("does 1");
			
			mouseIsOverPoint = true;
			return true;
		}else {
			
			System.out.println("does 2");

			
			mouseIsOverPoint = false;
			return false;
		}
	}

}
