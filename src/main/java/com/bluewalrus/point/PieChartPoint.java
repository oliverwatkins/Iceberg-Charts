/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluewalrus.point;

import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointPieChart;
import com.bluewalrus.pie.Segment;
import com.bluewalrus.renderer.XYFactor;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 *
 * @author oliver
 */
public class PieChartPoint extends ComplexXYPoint {

    public PieChartPoint(Color color) {
        super(color);
    }

    boolean scaleOnX = false;

    public void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor) {

        DataPointPieChart pieChartDataPoint = (DataPointPieChart) dataPoint;

        double magnitude = 0;

        if (scaleOnX) {
            magnitude = pieChartDataPoint.magnitude * xyFactor.xFactor;
        } else {
            magnitude = pieChartDataPoint.magnitude * xyFactor.yFactor;
        }

        magnitude = 60;

        g.setColor(color);

        Paint gp = g.getPaint();

        Color c = new Color(color.getRed(), color.getGreen(), color.getBlue(), 100);
        Color c2 = new Color(Color.RED.getRed(), Color.RED.getGreen(), Color.RED.getBlue(), 100);

        Color[] colors = {c, c2};

        float[] dist = {.3f, .7f};

        int x = (int) (point.x - (magnitude / 2));
        int y = (int) (point.y - (magnitude / 2));

        RadialGradientPaint rgp = new RadialGradientPaint(
                new Point(point.x, point.y),
                (int) magnitude,
                dist,
                colors);

        g.setPaint(rgp);

        drawPieChart(pieChartDataPoint, g);

//        g.fillRect(x, y, (int) magnitude, (int) magnitude );
        g.setPaint(gp);

        g.setColor(Color.BLACK);
        g.drawString("blah " + pieChartDataPoint.name, x, y);

//        Utils.outlineText(g, "hi there", x, y);
    }

    private void drawPieChart(DataPointPieChart pieChartDataPoint, Graphics2D g2d) {

        double startAngle = 0;
        ArrayList<Segment> initialSegments = pieChartDataPoint.pievalues;
        /**
         * Draw the segments first
         */
        for (int i = 0; i < initialSegments.size(); i++) {

            Segment s = initialSegments.get(i);

            double angleOfThisSegment = (s.magnitude / 100) * 360;

            s.startAngle = startAngle;
            s.angle = angleOfThisSegment;

            g2d.fillArc((int)pieChartDataPoint.x, (int)pieChartDataPoint.y, 60, 60, (int)startAngle, (int)-s.angle);
//            paintSegment(g2d, i, s);

            startAngle += angleOfThisSegment;
        }

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void paintSegment(Graphics2D g2d, int i, Segment segment) {

//        int level = segment.level;

//        /**
//         * Do children. Their start angle is not zero
//         */
//        if (segment.children != null && !segment.children.isEmpty()) {
//
//            double kidStartAngle = segment.startAngle;
//
//            for (int j = 0; j < segment.children.size(); j++) {
//
//                Segment kidSegment = segment.children.get(j);
//
//                kidSegment.startAngle = kidStartAngle;
//
//                //initial angle 
//                double kidAngle = (kidSegment.magnitude / 100) * 360;
//
//                //scale the kid magnitude 
//                double kidAngleScaled = kidAngle * segment.getRelativeMagnitude();
//
//                kidSegment.angle = kidAngleScaled;
//
//                paintSegment(g2d, i, kidSegment);
//
//                //adjust kid start angle
//                kidStartAngle = kidStartAngle + (kidSegment.angle);
//            }
//        }
//
//        Ellipse2D circleSubtract = null;
//        Ellipse2D circleToDraw = circles.get(level);
//
//        //apply scaling to angle.
//        if (segment.parent == null) {//first level - no scaling needed 
//            Double angle = (segment.magnitude / 100) * 360;
//            segment.angle = angle;
//        } else { //not first level - we need a circle to subtract
//            circleSubtract = circles.get(level - 1);
//        }
//        /**
//         * Draw with circle1
//         */
//        Shape arc2d = new Arc2D.Double(
//                circleToDraw.getBounds(),
//                segment.startAngle,
//                segment.angle,
//                Arc2D.PIE);
//
//        Area a = new Area(arc2d);
//
//        /**
//         * Cut out with circle2
//         */
//        if (segment.parent != null) {
//            a.subtract(new Area(circleSubtract));
//        }
//
//        //get mid point
//        segment.midpoint = getMidpointOfSegment(circleToDraw, circleSubtract, segment.startAngle, segment.angle);
//
//        segment.area = a;
//
//        g2d.setColor(Color.BLACK);
//        g2d.draw(a);
//        g2d.setColor(segment.color);
//        g2d.fill(a);
    }
}
