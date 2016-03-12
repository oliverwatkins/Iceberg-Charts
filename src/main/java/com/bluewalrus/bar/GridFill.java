package com.bluewalrus.bar;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import com.bluewalrus.chart.Chart;

public class GridFill{
	
	public Color color1;
	public Color color2;
	
	public boolean gradiant;

	public GridFill(Color color1, Color color2, boolean gradiant) {
		this.color1 = color1;
		this.color2 = color2;
		this.gradiant = gradiant;
	}
	
	
    
    public void fillAreaY(Graphics2D g, 
    		int yPos, 
            double lengthDimension, 
            Chart chart, 
            int incrementNo) {
    	
    	
    	int xPos = chart.leftOffset; 
    	
		lengthDimension = lengthDimension +1; //The GridFills were always out by one pixel.. ie 1 pixel white line, so need to add a pixel here.

    	Color colorCached = g.getColor();
    	
    	if (gradiant) {
			GradientPaint redtowhite2 = new GradientPaint(xPos,0, color1, (int)(xPos + lengthDimension), 0, color2);
			g.setPaint(redtowhite2);
    	}else {
        	if((incrementNo%2)==0) {
        		g.setColor(color1);
        	}else {
        		g.setColor(color2);
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
    	
    	if (gradiant) {
			GradientPaint redtowhite2 = new GradientPaint(xPos,0, color1, (int)(xPos + lengthDimension), 0, color2);
			g.setPaint(redtowhite2);
    	}else {
        	if((incrementNo%2)==0) {
        		g.setColor(color1);
        	}else {
        		g.setColor(color2);
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