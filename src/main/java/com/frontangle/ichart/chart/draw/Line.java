package com.frontangle.ichart.chart.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.io.Serializable;

import com.frontangle.ichart.chart.Chart;

/**
 * Defines a line used in a <code>XYChart</code>
 * 
 * @author Oliver Watkins
 */
public class Line implements Serializable{

    public Color color;
    public boolean dashed = false;
    public int thickness = 1;

    public Line(Color color) {
        this.color = color;
    }

    public Line(Color color, boolean dashed) {
        this.color = color;
        this.dashed = dashed;
    }
    
    public Line(Color color, boolean dashed, int thickness) {
        this.color = color;
        this.dashed = dashed;
        this.thickness = thickness;
    }

    public void drawLine(Graphics2D g, int x1, int y1,
            int x2, int y2) {
    	
    	if (thickness == 0)
    		return;
    	
    	Color colorCached = g.getColor();
    	
        g.setColor(color);

        Stroke oldStroke = g.getStroke();

        float[] dashBit = {2f, 0f}; //normal, no dashing

        if (dashed) {
            dashBit = new float[]{2f, 2f};
        }

        BasicStroke dashed = new BasicStroke(
                thickness,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f,
                dashBit,
                0.0f);

        g.setStroke(dashed);

        g.drawLine(x1, y1, x2, y2);

        g.setStroke(oldStroke);
        
        g.setColor(colorCached);
    }
}
