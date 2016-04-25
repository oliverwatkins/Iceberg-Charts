package com.bluewalrus.chart.draw.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYFactor;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.chart.datapoint.DataPointMultiBar;
import com.bluewalrus.chart.draw.XAxisDrawUtil;


/**
 * Note can only be enumerable
 * 
 * @author Oliver Watkins
 *
 */
public class UIPointMultiBarStacked extends UIPointAbstractMultiBar{

	XYChart chart; //Two way reference here :( Not good :(
	private int height;
	private int width;
	private int y;
	private Color muchmuchdarker;
	private Color colorToUse;
	private int startDrawLeft;
	
	public UIPointMultiBarStacked(XYChart chart) {
		super(Color.BLACK);
		this.chart = chart;		
	}

	public void draw(Graphics2D g, Point point, Point lastPoint, 
			DataPoint dataPoint, XYFactor xyFactor, XYChart chart, int pixBtnFirst2Pts) {

		DataPointMultiBar dpX = (DataPointMultiBar)dataPoint;
	    
		int leftPosition = 0;
        y = 0;
        width = 0;
        height = 0;

        double distance = 0;
        
    	leftPosition = (int) dataPoint.x;
    	
    	/**
    	 * Draw each of the (multi) bars
    	 */
        for (DataPointBar dataPointBar : dpX.datapointBars) {
        	
        	startDrawLeft = leftPosition  - (pointDiffWidth/2);
        	
        	/**
        	 * Calculate rectangle dimensions.
        	 */
            if (dataPointBar.y > 0) { // greater than zero
            	
            	double scaledYPoint = (dataPointBar.y * xyFactor.yFactor);
            	
                y = (int)(chart.topOffset + chart.heightChart - scaledYPoint - distance);

                width = pointDiffWidth;
                height = (int)((dataPointBar.y * xyFactor.yFactor));
                
                distance = distance + (dataPointBar.y * xyFactor.yFactor);
                
                colorToUse = color;
            	
            }else { // less than zero
            	//??
//                x = point.x - (barWidth/2);
//                y = point.y + (int)( dpb.y * xyFactor.yFactor);
//                width = barWidth;
//                height = (int)((- dpb.y * xyFactor.yFactor)); 
//                
                colorToUse = color;
//                colorToUse = negativeColor;
            }

            
            /**
             * Draw rectangle here
             */
            if (dataPointBar.color != null) {

            	colorToUse = dataPointBar.color;
            }
            
            muchmuchdarker = colorToUse.darker(); 
            
            clipAndDrawPoint(g, chart);
            
//            g.setColor(colorToUse);
//            
//            //bottom rect
//            g.fillRect(startDrawLeft,
//            		y,
//            		width,
//            		height);
//            
//            g.setColor(muchmuchdarker);
//            
//            //bottom rect
//            g.drawRect(startDrawLeft,
//            		y,
//            		width,
//            		height);
            
		}
	}
	
	@Override
	public void drawPoint(Graphics2D g) {
		
        g.setColor(colorToUse);
        
        //bottom rect
        g.fillRect(startDrawLeft,
        		y,
        		width,
        		height);
        
        g.setColor(muchmuchdarker);
        
        //bottom rect
        g.drawRect(startDrawLeft,
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
