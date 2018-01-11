package com.frontangle.ichart.chart.draw.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.DataPointBar;
import com.frontangle.ichart.chart.datapoint.DataPointMultiBar;

/**
 * Note can only be enumerable
 * 
 * @author Oliver Watkins
 *
 */
public class UIPointMultiBarStacked extends UIPointAbstractMultiBar{

	private int height;
	private int width;
	private int y;
	private Color colorToUse;
	private int startDrawLeft;
	
	public UIPointMultiBarStacked() {
		super(Color.BLACK);
	}

	public UIPointMultiBarStacked(int i) {
		super(Color.BLACK);
		
		this.barWidthPercent = i;
	}
	
	double barWidth = -123; //px

	public void draw(Graphics2D g, Point point, DataPoint dataPoint, 
			XYFactor xyFactor, XYChart chart, int pixBtnFirst2Pts) {

		DataPointMultiBar dpX = (DataPointMultiBar)dataPoint;
		
    	if (barWidthPercent != 0) {
    		barWidth = ((barWidthPercent * (double)pixBtnFirst2Pts)/ 100.0); 
    	}else {
    		barWidth = 50; //default
    	}
	    
        y = 0;
        width = 0;
        height = 0;

        double distance = 0;
        
        int leftPosition = (int)(dataPoint.x * xyFactor.getxFactor()) + chart.leftOffset;
    	
    	/**
    	 * Draw each of the (multi) bars
    	 */
        for (DataPointBar dataPointBar : dpX.bars) {
        	startDrawLeft = leftPosition  - ((int)barWidth/2);
        	
        	/**
        	 * Calculate rectangle dimensions.
        	 */
            if (dataPointBar.y > 0) { // greater than zero
            	
            	double scaledYPoint = (dataPointBar.y * xyFactor.getyFactor());
            	
                y = (int)(chart.topOffset + chart.heightChart - scaledYPoint - distance);

                width = (int)barWidth;
                height = (int)((dataPointBar.y * xyFactor.getyFactor()));
                
                distance = distance + (dataPointBar.y * xyFactor.getyFactor());
                
                colorToUse = color;
            	
            }else { // less than zero
                colorToUse = color;
            }
            
            /**
             * Draw rectangle here
             */
            if (dataPointBar.color != null) {
            	colorToUse = dataPointBar.color;
            }
            
            clipAndDrawPoint(g, chart);
		}
	}
	
	@Override
	public void drawPoint(Graphics2D g) {
        g.setColor(colorToUse);
        
        //bottom rect
        g.fillRect(startDrawLeft,
        		y,
        		(int)barWidth, //width
        		height+1); //add one pixel so we don't get a white line. It's wrong doing this.. in a way :(
	}
	
	@Override
	public boolean doesShapeContainPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

}
