package com.frontangle.ichart.chart.draw.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.DataPointBar;
import com.frontangle.ichart.chart.datapoint.DataPointMultiBar;

public class UIPointMultiBarSideBySide extends UIPointAbstractMultiBar{

	private int x;
	private int y;
	private int width;
	private int height;
	private int shift;
	private Color colorToUse;
	private Color muchmuchdarker;
	
	public UIPointMultiBarSideBySide() {
		super(Color.BLACK); //unimportant, never used.
	}

	public UIPointMultiBarSideBySide(int totalBarWidthPercent) {
		super(Color.BLACK); //unimportant, never used.
		barWidthPercent = totalBarWidthPercent;
	}

	public void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor, XYChart chart, int pixBtnFirst2Pts) {

		DataPointMultiBar multiBarDataPoint = (DataPointMultiBar)dataPoint;
	    
		x = 0;
        y = 0;
        width = 0;
        height = 0;

        shift = 0;
        
        int totalWidthOfBars = multiBarDataPoint.bars.size() * barWidthInPixels;
        
    	/**
    	 * Draw each of the (multi) bars
    	 */
        for (DataPointBar dpb : multiBarDataPoint.bars) {
            if (dpb.y > 0) { // greater than zero
                x = point.x - (totalWidthOfBars/2);
                y = chart.topOffset + chart.heightChart - (int)(dpb.y * xyFactor.getyFactor());
                width = barWidthInPixels;
                height = (int)((dpb.y * xyFactor.getyFactor()));
                
                colorToUse = color;
            	
            }else { // less than zero
                x = point.x - (totalWidthOfBars/2);
                y = point.y + (int)( dpb.y * xyFactor.getyFactor());
                width = barWidthInPixels;
                height = (int)((- dpb.y * xyFactor.getyFactor())); 
                
                colorToUse = color;
            }

            if (dpb.color != null) {
            	colorToUse = dpb.color;
            }
            muchmuchdarker = colorToUse.darker(); 
            
    		clipAndDrawPoint(g, chart);
            
            shift = shift+barWidthInPixels;
		}
	}

	
	@Override
	public void drawPoint(Graphics2D g) {
        g.setColor(colorToUse);
        
        //bottom rect
        g.fillRect(x + shift,
        		y,
        		width,
        		height);
        
        g.setColor(muchmuchdarker);
	}
	

	@Override
	public boolean doesShapeContainPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

}
