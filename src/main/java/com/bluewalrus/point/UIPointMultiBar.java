package com.bluewalrus.point;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.draw.XAxisDraw;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.datapoint.DataPointMultiBar;
import com.bluewalrus.renderer.XYFactor;

public class UIPointMultiBar extends UIPointAbstractMultiBar{

	XYChart chart; //Two way reference here :( Not good :(
	
	public UIPointMultiBar(XYChart chart) {
		super(Color.BLACK); //unimportant, never used.
		this.chart = chart;		
	}

	public void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor) {

		DataPointMultiBar dpX = (DataPointMultiBar)dataPoint;
	    
		Color colorToUse;

        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        int shift = 0;
        
        int totalWidthOfBars = dpX.datapointBars.size() * barWidth;
        
        for (DataPointBar dpb : dpX.datapointBars) {
        	
            if (dpb.y > 0) { // greater than zero
                x = point.x - (totalWidthOfBars/2);
                y = chart.topOffset + chart.heightChart - (int)(dpb.y * xyFactor.yFactor);
                width = barWidth;
                height = (int)((dpb.y * xyFactor.yFactor));
                
                colorToUse = color;
            	
            }else { // less than zero
            	
                x = point.x - (totalWidthOfBars/2);
                y = point.y + (int)( dpb.y * xyFactor.yFactor);
                width = barWidth;
                height = (int)((- dpb.y * xyFactor.yFactor)); 
                
                colorToUse = color;
//                colorToUse = negativeColor;
            }

            if (dpb.color != null) {

            	colorToUse = dpb.color;
            }
            
            Color muchmuchdarker = colorToUse.darker(); 
            
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
            
            
            shift = shift+barWidth;
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
