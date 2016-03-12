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
    
    

    
    public void fillAreaY(Graphics2D g, 
    		int yPos, 
            double lengthDimension, 
            Chart chart, 
            int incrementNo) {
    	
    	
    	int xPos = chart.leftOffset; 
    	
		lengthDimension = lengthDimension +1; //The GridFills were always out by one pixel.. ie 1 pixel white line, so need to add a pixel here.

    	Color colorCached = g.getColor();
    	
    	if (gridFill.gradiant) {
			GradientPaint redtowhite2 = new GradientPaint(xPos,0,gridFill.color1, (int)(xPos + lengthDimension), 0,gridFill.color2);
			g.setPaint(redtowhite2);
    	}else {
        	if((incrementNo%2)==0) {
        		g.setColor(gridFill.color1);
        	}else {
        		g.setColor(gridFill.color2);
        	}
    	}

    	Rectangle fillRect = new Rectangle(new Rectangle(xPos, yPos, chart.widthChart, (int)lengthDimension));
    	
		if ((yPos) > (chart.topOffset + chart.heightChart)) {
			
			//skip
			
		} else if ((yPos + lengthDimension) > (chart.topOffset + chart.heightChart)) {
			System.out.println("CLIP END");
			
			//clip off anything that is shown passed the chart right edge
			Shape clip = g.getClip();
			
			g.clip(new Rectangle(chart.leftOffset, yPos, (int)chart.widthChart, 
					(int)(yPos + lengthDimension) - (chart.heightChart + chart.topOffset)));
			
			g.fill (fillRect);

			g.setClip(clip);
			
		}else if (yPos < chart.topOffset) {
			
			//clip off anything that is shown passed the chart top edge
			Shape clip = g.getClip();
			
			g.clip(new Rectangle(chart.leftOffset, chart.topOffset, (int)chart.widthChart, (int)lengthDimension));
			
			g.fill (fillRect);

			g.setClip(clip);
			
		}else {
			g.fill (new Rectangle(xPos, yPos, chart.widthChart, (int)lengthDimension));
		}
		g.setColor(colorCached);
	}

    public void fillAreaX(Graphics2D g, 
    		int xPos,
            double lengthDimension, //increment in pixels
            Chart chart, 
            int incrementNo) {
    	
//    	incrementNo
    	
    	lengthDimension = lengthDimension +1; //The GridFills were always out by one pixel.. ie 1 pixel white line, so need to add a pixel here.

    	Color colorCached = g.getColor();
    	
    	if (gridFill.gradiant) {
			GradientPaint redtowhite2 = new GradientPaint(xPos,0,gridFill.color1, (int)(xPos + lengthDimension), 0,gridFill.color2);
			g.setPaint(redtowhite2);
    	}else {
        	if((incrementNo%2)==0) {
        		g.setColor(gridFill.color1);
        	}else {
        		g.setColor(gridFill.color2);
        	}
    	}

    	Rectangle fillRect = new Rectangle(xPos, chart.topOffset, (int)lengthDimension, (int)chart.heightChart);
    	
		if ((xPos) > (chart.leftOffset + chart.widthChart)) {
			
			//skip
		} else if ((xPos + lengthDimension) > (chart.leftOffset + chart.widthChart)) {
			
	    	System.out.println("CLIP RIGHT " + xPos + " to pos " + lengthDimension);
			
			//clip off anything that is shown passed the chart right edge
			Shape clip = g.getClip();
			
			g.setClip(fillRect);
			
			int width2 = chart.leftOffset + chart.widthChart - xPos;
			
			g.clip(new Rectangle(xPos, chart.topOffset, width2, (int)chart.heightChart));
			
			g.fill (fillRect);

			g.setClip(clip);
			
		}else if (xPos < chart.leftOffset) {
			
	    	System.out.println("CLIP LEFT " + xPos + " to pos " + lengthDimension);

			//clip off anything that is shown passed the chart left edge
			Shape clip = g.getClip();
			
			g.setClip(fillRect);
			
			g.clip(new Rectangle(chart.leftOffset, chart.topOffset, (int)lengthDimension, (int)chart.heightChart));
			
			g.fill (fillRect);

			g.setClip(clip);
			
		}else {
			g.fill (fillRect);
		}
		g.setColor(colorCached);
	}
}
