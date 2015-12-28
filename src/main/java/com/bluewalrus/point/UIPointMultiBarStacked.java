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

public class UIPointMultiBarStacked extends UIPointAbstractMultiBar{

	XYChart chart; //Two way reference here :( Not good :(
	
	public UIPointMultiBarStacked(XYChart chart) {
		super(Color.BLACK);
		this.chart = chart;		
	}

	public void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor) {

		DataPointMultiBar dpX = (DataPointMultiBar)dataPoint;
	    
		Color colorToUse;

        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        double distance = 0;
        
        for (DataPointBar dpb : dpX.datapointBars) {
        	
        	
        	
            if (dpb.y > 0) { // greater than zero
                
            	x = point.x - (barWidth/2);
                
            	
            	double scaledYPoint = (dpb.y * xyFactor.yFactor);
                
            	System.out.println("scaledYPoint " + scaledYPoint);
            	System.out.println("distance " + distance);
            	
                y = (int)(chart.topOffset + chart.heightChart - scaledYPoint - distance);

                width = barWidth;
                height = (int)((dpb.y * xyFactor.yFactor));
                
                distance = distance + (dpb.y * xyFactor.yFactor);
                
                colorToUse = color;
            	
            }else { // less than zero
            	
//                x = point.x - (barWidth/2);
//                y = point.y + (int)( dpb.y * xyFactor.yFactor);
//                width = barWidth;
//                height = (int)((- dpb.y * xyFactor.yFactor)); 
//                
                colorToUse = color;
//                colorToUse = negativeColor;
            }

            if (dpb.color != null) {

            	colorToUse = dpb.color;
            }
            
            Color muchmuchdarker = colorToUse.darker(); 
            
            g.setColor(colorToUse);
            
            //bottom rect
            g.fillRect(x,
            		y,
            		width,
            		height); // - xyFactor.yZeroOffsetInPixel));
            
            g.setColor(muchmuchdarker);
            
            //bottom rect
            g.drawRect(x,
            		y,
            		width,
            		height);
            
            
		}
        
        
        
        if (dpX.name != null)
        	XAxisDraw.drawText(chart, dpX.name, g, point.x);
        
        XAxisDraw.drawTickLine(g, chart, point.x);

	}

	@Override
	public boolean doesShapeContainPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
