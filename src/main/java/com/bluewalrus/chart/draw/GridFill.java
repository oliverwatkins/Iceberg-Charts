package com.bluewalrus.chart.draw;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.ChartUtils;

public class GridFill{
	
	public Color color1;
	public Color color2;
	
	public boolean gradiant;

	public GridFill(Color color1, Color color2, boolean gradiant) {
		this.color1 = color1;
		this.color2 = color2;
		this.gradiant = gradiant;
	}
	
    /**
     * move to UTILS classes!!!
     * 
     * fill Y (TODO similar to fill X. May be able to refactor more)
     * 
     * @param g
     * @param yPos
     * @param lengthDimension
     * @param chart
     * @param incrementNo
     */
	
    @Deprecated
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
    	
		fillOutRectangle(g, chart, colorCached, fillRect);
	}



    /**
     * FILL X
     * 
     * @param g
     * @param xPos
     * @param lengthDimension
     * @param chart
     * @param incrementNo
     */
    public void fillAreaX(Graphics2D g, 
    		double xPos,
            double lengthDimension, //increment in pixels
            Chart chart, 
            int incrementNo) {
    	
    	lengthDimension = lengthDimension +1; //The GridFills were always out by one pixel.. ie 1 pixel white line, so need to add a pixel here.

    	Color colorCached = g.getColor();
    	
    	
    	if (gradiant) {
			GradientPaint redtowhite2 = new GradientPaint((int)xPos,0, color1, (int)(xPos + lengthDimension), 0, color2);
			g.setPaint(redtowhite2);
    	}else {
        	if((incrementNo%2)==0) {
        		g.setColor(color1);
        	}else {
        		g.setColor(color2);
        	}
    	}

    	Rectangle fillRect = new Rectangle((int)xPos, chart.topOffset, (int)lengthDimension, (int)chart.heightChart);
    	
		fillOutRectangle(g, chart, colorCached, fillRect);
	}
    
    /**
     * Put a clip on the chart boundary so that the fill stays within bounds, then fill the rectangle.
     * 
     * @param g
     * @param chart
     * @param colorCached
     * @param fillRect
     */
	private void fillOutRectangle(Graphics2D g, Chart chart, Color colorCached,
			Rectangle fillRect) {
		
		Shape clip = ChartUtils.clipChart(g, chart);
		
		g.fill (fillRect);

		g.setClip(clip);
		
		g.setColor(colorCached);
	}
}