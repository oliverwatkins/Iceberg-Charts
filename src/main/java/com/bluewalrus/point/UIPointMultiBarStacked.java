package com.bluewalrus.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.draw.XAxisDraw;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.datapoint.DataPointMultiBar;
import com.bluewalrus.renderer.XYFactor;


/**
 * Note can only be enumerable
 * 
 * @author Oliver Watkins
 *
 */
public class UIPointMultiBarStacked extends UIPointAbstractMultiBar{

	XYChart chart; //Two way reference here :( Not good :(
	
	public UIPointMultiBarStacked(XYChart chart) {
		super(Color.BLACK);
		this.chart = chart;		
	}

	public void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor) {

		DataPointMultiBar dpX = (DataPointMultiBar)dataPoint;
	    
		Color colorToUse;

        int leftPosition = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        double distance = 0;
        
    	leftPosition = point.x;
        
    	/**
    	 * Draw each of the (multi) bars
    	 */
        for (DataPointBar dataPointBar : dpX.datapointBars) {

        	
        	int startDrawLeft = leftPosition  - (barWidth/2);
        	
        	
        	
        	/**
        	 * Calculate rectangle dimensions.
        	 */
            if (dataPointBar.y > 0) { // greater than zero
            	
            	double scaledYPoint = (dataPointBar.y * xyFactor.yFactor);
            	
                y = (int)(chart.topOffset + chart.heightChart - scaledYPoint - distance);

                width = barWidth;
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
            
            Color muchmuchdarker = colorToUse.darker(); 
            
            g.setColor(colorToUse);
            
            //bottom rect
            g.fillRect(startDrawLeft,
            		y,
            		width,
            		height); // - xyFactor.yZeroOffsetInPixel));
            
            g.setColor(muchmuchdarker);
            
            //bottom rect
            g.drawRect(startDrawLeft,
            		y,
            		width,
            		height);
            
		}
        
        
        
        
        XAxisDraw.drawXLabel(g, chart, leftPosition, dpX.name, chart.xAxis);
        XAxisDraw.drawIntervalTick(chart.xAxis.interval1, g, chart, leftPosition, chart.xAxis);
	}

	@Override
	public boolean doesShapeContainPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
