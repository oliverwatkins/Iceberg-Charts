package com.bluewalrus.chart.draw.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYFactor;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.chart.datapoint.MultiBar;

public class UIPointMultiBar extends UIPointAbstractMultiBar{

//	XYChart chart; //Two way reference here :( Not good :( TODO use singleton reference to chart?
	private int x;
	private int y;
	private int width;
	private int height;
	private int shift;
	private Color colorToUse;
	private Color muchmuchdarker;
	
	public UIPointMultiBar(XYChart chart) {
		super(Color.BLACK); //unimportant, never used.
//		this.chart = chart;		
	}

	public void draw(Graphics2D g, Point point, Point lastPoint, DataPoint dataPoint, XYFactor xyFactor, XYChart chart, int pixBtnFirst2Pts) {

		MultiBar dpX = (MultiBar)dataPoint;
	    
		x = 0;
        y = 0;
        width = 0;
        height = 0;

        shift = 0;
        
        int totalWidthOfBars = dpX.bars.size() * pointDiffWidth;
        
        point.x = (int) dataPoint.x;
        
        
//        g.drawRect((int)(point.x * xyFactor.xFactor) + chart.leftOffset, 100, 5, 5);
        
        
        point.x = (int)(point.x * xyFactor.xFactor) + chart.leftOffset;
    	/**
    	 * Draw each of the (multi) bars
    	 */
        for (DataPointBar dpb : dpX.bars) {
        	
            if (dpb.y > 0) { // greater than zero
                x = point.x - (totalWidthOfBars/2);
                y = chart.topOffset + chart.heightChart - (int)(dpb.y * xyFactor.yFactor);
                width = pointDiffWidth;
                height = (int)((dpb.y * xyFactor.yFactor));
                
                colorToUse = color;
            	
            }else { // less than zero
            	
                x = point.x - (totalWidthOfBars/2);
                y = point.y + (int)( dpb.y * xyFactor.yFactor);
                width = pointDiffWidth;
                height = (int)((- dpb.y * xyFactor.yFactor)); 
                
                colorToUse = color;
//                colorToUse = negativeColor;
            }

            if (dpb.color != null) {

            	colorToUse = dpb.color;
            }
            
            muchmuchdarker = colorToUse.darker(); 
            
            
    		clipAndDrawPoint(g, chart);
            
            
            shift = shift+pointDiffWidth;
		}
	}


	
	@Override
	public void drawPoint(Graphics2D g) {
		
		
        g.setColor(colorToUse);
        
        //bottom rect
        g.fillRect(x + shift,
        		y,
        		width,
        		height); // - xyFactor.yZeroOffsetInPixel));
        
        g.setColor(muchmuchdarker);
        
        //bottom rect
        g.drawRect(x + shift,
        		y,
        		width,
        		height);
		
	}
	
	
	

	@Override
	public boolean doesShapeContainPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}


}
