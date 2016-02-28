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
    	
    	Color colorCached = g.getColor();
    	
//    	System.out.println("color Chached = " + colorCached);
    	
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
    
    public void fillArea(Graphics2D g, int x, int y,
            double width, int height, Chart chart, int incrementNo) {
    	
    	Color colorCached = g.getColor();
    	
//    	System.out.println("color Chached = " + colorCached);
    	
    	if (gridFill.gradiant) {
			GradientPaint redtowhite2 = new GradientPaint(x,0,gridFill.color1, (int)(x+width), 0,gridFill.color2);
			g.setPaint(redtowhite2);
    	}else {
        	if((incrementNo%2)==0) {
        		g.setColor(gridFill.color1);
        	}else {
        		g.setColor(gridFill.color2);
        	}
        	g.fillRect(x, y, (int)width, height); //(x1, y1, x2, y2)
    	}
		
		if ((x + width) > (chart.leftOffset + chart.widthChart)) {
			
			//clip off anything that is shown passed the chart right edge
			Shape clip = g.getClip();
			
			g.setClip(new Rectangle(x, y, (int)width, height));
			
			int width2 = chart.leftOffset + chart.widthChart - x;
			
			g.clip(new Rectangle(x, y, width2, height));
			
			g.fill (new Rectangle(x, y, (int)width, height));

			g.setClip(clip);
			
		}else if (x < chart.leftOffset) {

			//clip off anything that is shown passed the chart left edge
			Shape clip = g.getClip();
			
			g.setClip(new Rectangle(x, y, (int)width, height));
			
			g.clip(new Rectangle(chart.leftOffset, y, (int)width, height));
			
			g.fill (new Rectangle(x, y, (int)width, height));

			g.setClip(clip);
			
		}else {
			g.fill (new Rectangle(x, y, (int)width, height));
		}
		g.setColor(colorCached);
    }
}
