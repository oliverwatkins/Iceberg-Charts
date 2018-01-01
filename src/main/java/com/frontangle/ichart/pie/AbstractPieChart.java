package com.frontangle.ichart.pie;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Chart;

public abstract class AbstractPieChart extends Chart  {

	
    ArrayList<Ellipse2D> circles;
    protected ArrayList<Segment> initialSegments;

	
    public Font textFontOnSlices = new Font("Arial", Font.BOLD, 14);
    public Color textColorOnSlices = Color.WHITE;

    Point centerPoint; //value is calculated from totalWidth
    
    public int initialWidth = 200;
    public int incrementWidth = 80;
    
    protected ArrayList<Ellipse2D> getCircleArray(int depth) {

        ArrayList<Ellipse2D> list = new ArrayList<Ellipse2D>();

        for (int i = 0; i <= depth; i++) {

            Ellipse2D circle = new Ellipse2D.Double();

            Point pLeftTopCorner = new Point(
                    (leftOffset + (widthChart / 2)) - (initialWidth + (incrementWidth * i)),
                    (topOffset + (heightChart / 2) - (initialWidth + (incrementWidth * i))));

            circle.setFrameFromCenter(centerPoint, pLeftTopCorner);

            list.add(circle);
        }

        return list;
    }
	protected void calculateCenterPoint() {
		this.centerPoint = new Point(
        		(widthChart / 2) + leftOffset, 
        		(heightChart / 2) + topOffset);
	}

	
    protected void paintTextOnSegment(Graphics2D g2d, Segment s) {
        
        g2d.setFont(textFontOnSlices);
        g2d.setColor(textColorOnSlices);

        if (s.midpoint != null) {
            g2d.drawString(s.name, (int) s.midpoint.getX(), (int) s.midpoint.getY());
        }

        if (s.children != null && !s.children.isEmpty()) {
            for (Segment segment : s.children) {
                paintTextOnSegment(g2d, segment);
            }
        }
    }
    

	protected void paintFirstSegments(Graphics2D g2d) {
		
		double startAngle = 0;

        /**
         * Draw the segments first
         */
        for (int i = 0; i < initialSegments.size(); i++) {

            Segment s = initialSegments.get(i);

            double angleOfThisSegment = (s.magnitude / 100) * 360;

            s.startAngle = startAngle;
            s.angle = angleOfThisSegment;

            paintSegment(g2d, i, s);

            startAngle += angleOfThisSegment;
        }
	}

    protected Point getMidpointOfSegment(Ellipse2D circleDraw,
            double startAngle,
            double angle) {
    	return getMidpointOfSegment(circleDraw, null, startAngle, angle);
    }

    /*
     * Retrieve the midpoint of a filled arc. The midpoint is considered in this
     * case the angular midpoint of the arc combined with the radial midpoint.
     *
     * @param circleDraw
     * @param circleSubtract
     * @param startAngle
     * @param angle
     * @return
     */
    protected Point getMidpointOfSegment(Ellipse2D circleDraw,
            Ellipse2D circleSubtract,
            double startAngle,
            double angle) {

        if (circleSubtract == null) { //if null, then center circle so there is no circleSubtract. We just create a small few pixel sized circle
            circleSubtract = new Ellipse2D.Double();
            circleSubtract.setFrameFromCenter(centerPoint, new Point(centerPoint.x - 2, centerPoint.y - 2));
        }

        double x = circleDraw.getX();
        double xs = circleSubtract.getX();
        double y = circleDraw.getY();
        double ys = circleSubtract.getY();
        double width = circleDraw.getWidth();
        double widthS = circleSubtract.getWidth();
        double radius = width / 2;
        double radiusS = widthS / 2;

        Ellipse2D ellipse = new Ellipse2D.Double();

        Point pLeftTopCorner = new Point((int) (x + (xs - x) / 2), (int) (y + (ys - y) / 2));

        double radiusIntersect = (int) (radiusS + ((radius - radiusS) / 2));

        ellipse.setFrameFromCenter(centerPoint, pLeftTopCorner);

        double totalAngle = startAngle + angle / 2;

        double cosAngle = Math.cos(Math.toRadians(totalAngle));
        double newXPos = cosAngle * radiusIntersect;

        double sinAngle = Math.sin(Math.toRadians(totalAngle));
        double newYPos = sinAngle * radiusIntersect;

        int xPos = (int) (newXPos + (widthChart / 2) + leftOffset);
        int yPos = (int) (-newYPos + (heightChart / 2) + topOffset);

        return new Point(xPos, yPos);
    }
    
    
    protected void paintSegment(Graphics2D g2d, int i, Segment segment) {

    	int level = segment.level;
    	
        /**
         * Do children. Their start angle is not zero
         */
        if (segment.children != null && !segment.children.isEmpty()) {

            double kidStartAngle = segment.startAngle;

            for (int j = 0; j < segment.children.size(); j++) {

                Segment kidSegment = segment.children.get(j);

                kidSegment.startAngle = kidStartAngle;

                //initial angle 
                double kidAngle = (kidSegment.magnitude / 100) * 360;

                //scale the kid magnitude 
                double kidAngleScaled = kidAngle * segment.getRelativeMagnitude();

                kidSegment.angle = kidAngleScaled;

                paintSegment(g2d, i, kidSegment);

                //adjust kid start angle
                kidStartAngle = kidStartAngle + (kidSegment.angle);
            }
        }
        
        Ellipse2D circleSubtract = null;
        Ellipse2D circleToDraw = circles.get(level);

        //apply scaling to angle.
        if (segment.parent == null) {//first level - no scaling needed 
            Double angle = (segment.magnitude / 100) * 360;
            segment.angle = angle;
        } else { //not first level - we need a circle to subtract
            circleSubtract = circles.get(level - 1);
        }
        /**
         * Draw with circle1
         */
        Shape arc2d = new Arc2D.Double(
                circleToDraw.getBounds(),
                segment.startAngle,
                segment.angle,
                Arc2D.PIE);
        

        Area a = new Area(arc2d);

        /**
         * Cut out with circle2
         */
        if (segment.parent != null) {
            a.subtract(new Area(circleSubtract));
        }

        //get mid point
        segment.midpoint = getMidpointOfSegment(circleToDraw, circleSubtract, segment.startAngle, segment.angle);

        segment.area = a;

        g2d.setColor(Color.BLACK);
        g2d.draw(a);
        g2d.setColor(segment.color);
        g2d.fill(a);
    }

}
