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
        
        System.out.println("Name=" + pieChartDataPoint.name);
        System.out.println("pieChartDataPoint.magnitude=" + pieChartDataPoint.magnitude);
        System.out.println("M=" + magnitude);
        System.out.println("xyFactor.xFactor=" + xyFactor.xFactor);
        System.out.println("xyFactor.yFactor=" + xyFactor.yFactor);
        
        
        

        g.setColor(color);

        Paint gp = g.getPaint();

        int x = (int) (point.x - (magnitude / 2));
        int y = (int) (point.y - (magnitude / 2));

        drawPieChart(pieChartDataPoint, g, new Point(x,y), magnitude);

        g.setPaint(gp);

        g.setColor(Color.BLACK);
        g.drawString("blah " + pieChartDataPoint.name, x, y);

//        Utils.outlineText(g, "hi there", x, y);
    }

    private void drawPieChart(DataPointPieChart pieChartDataPoint, Graphics2D g2d, Point point, double magnitude) {

        int startAngle = 0;
        ArrayList<Segment> values = pieChartDataPoint.pievalues;
        
        int width = (int)magnitude;

        System.out.println("");
        System.out.println("");

        System.out.println("name = " + pieChartDataPoint.name);
        
        for (int i = 0; i < values.size(); i++) {

            Segment s = values.get(i);

            Double angleOfThisSegment = (s.magnitude / 100) * 360;

//            s.startAngle = startAngle;
//            s.angle = angleOfThisSegment; // * Math.PI/180;
            
            System.out.println("startAngle = " + startAngle);
            System.out.println("angleOfThisSegment = " + angleOfThisSegment);

            g2d.setColor(s.color);
            
            g2d.fillArc(point.x, point.y, width, width, startAngle, angleOfThisSegment.intValue());

            startAngle += angleOfThisSegment;
        }
        
        
//        int lastPoint = -270;
//
//        for (int i = 0; i < values.size(); i++) {
//                g2d.setColor(colors.get(i));
//
//                Double val = values.get(i);
//                Double angle = (val / 100) * 360;
//
//                g2d.fillArc(0, 0, width, width, lastPoint, -angle.intValue());
//                System.out.println("fill arc " + lastPoint + " "
//                                + -angle.intValue());
//
//                lastPoint = lastPoint + -angle.intValue();
//        }
    }


}
