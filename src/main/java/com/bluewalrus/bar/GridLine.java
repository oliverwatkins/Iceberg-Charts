package com.bluewalrus.bar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.io.Serializable;

import com.bluewalrus.chart.Chart;

public class GridLine implements Serializable{

    public Color color;
    public boolean dashed = false;
    public int thickness = 1;
    public GridFill gridFill;

    public GridLine(Color color) {
        this.color = color;
    }

    public GridLine(Color color, boolean dashed) {
        this.color = color;
        this.dashed = dashed;
    }
    
    public GridLine(Color color, boolean dashed, int thickness) {
        this.color = color;
        this.dashed = dashed;
        this.thickness = thickness;
    }

    public GridLine(Color color, boolean dashed, int thickness, GridFill gridFill) {
        this.color = color;
        this.dashed = dashed;
        this.thickness = thickness;
        this.gridFill = gridFill;
    }

    public void drawLine(Graphics2D g, int x1, int y1,
            int x2, int y2) {
    	
    	if (thickness == 0)
    		return;
    	
    	System.out.println("Drawing a LINE at pos " + x1 + " to pos ");

    	
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
    
    public void fillArea(Graphics2D g, int xPos, int yPos,
            double width, int height, Chart chart, int incrementNo) {
    	
    	System.out.println("Drawing a GRID FILL at pos " + xPos + " to pos " + width);
    	
    	

    	width = width +1; //The GridFills were always out by one pixel.. ie 1 pixel white line, so need to add a pixel here.

    	Color colorCached = g.getColor();
    	
    	if (gridFill.gradiant) {
			GradientPaint redtowhite2 = new GradientPaint(xPos,0,gridFill.color1, (int)(xPos+width), 0,gridFill.color2);
			g.setPaint(redtowhite2);
    	}else {
        	if((incrementNo%2)==0) {
        		g.setColor(gridFill.color1);
        	}else {
        		g.setColor(gridFill.color2);
        	}
    	}
		
		if ((xPos + width) > (chart.leftOffset + chart.widthChart)) {
			
			//clip off anything that is shown passed the chart right edge
			Shape clip = g.getClip();
			
			g.setClip(new Rectangle(xPos, yPos, (int)width, height));
			
			int width2 = chart.leftOffset + chart.widthChart - xPos;
			
			g.clip(new Rectangle(xPos, yPos, width2, height));
			
			g.fill (new Rectangle(xPos, yPos, (int)width, height));

			g.setClip(clip);
			
		}else if (xPos < chart.leftOffset) {

			//clip off anything that is shown passed the chart left edge
			Shape clip = g.getClip();
			
			g.setClip(new Rectangle(xPos, yPos, (int)width, height));
			
			g.clip(new Rectangle(chart.leftOffset, yPos, (int)width, height));
			
			g.fill (new Rectangle(xPos, yPos, (int)width, height));

			g.setClip(clip);
			
		}else {
			g.fill (new Rectangle(xPos, yPos, (int)width, height));
		}
		g.setColor(colorCached);
    }
}
