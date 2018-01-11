package com.frontangle.ichart.chart.draw.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.DataPointBar;

public class UIPointBar extends UIPointAbstractBar {

	
    boolean doBorder = false;
    Color negativeColor;
    
	private int x;
	private int y;
	private double width;
	private double height;
	
	private Color muchmuchdarker; //by default the border is a darker version of the bar color.
	private Color colorToUse;

    public UIPointBar() {
    	super();
	}
    
    public UIPointBar(Color color) {
        super(color);
    }
    public UIPointBar(Color color, int barWidthPercent) {
        super(color);
        this.barWidthPercent = barWidthPercent;
        
    }

    public UIPointBar(Color color, Color negativeColor, int barWidth) {
        super(color);
        this.negativeColor = negativeColor;
        this.barWidthInPixels = barWidth;
    }


	public void draw(Graphics2D g, 
    		Point point, DataPoint dataPoint, XYFactor xyFactor, XYChart chart, int pixBtnFirst2Pts) {

    	
    	double barWidth = -123; //px
    	
    	if (barWidthPercent != 0) {
    		barWidth = ((barWidthPercent * (double)pixBtnFirst2Pts)/ 100.0); 
    	}else {
    		barWidth = 50; //default
    	}
    	
    	Color dataPointColor = null;
    	
    	double yPos = -999;
    	
    	if (dataPoint instanceof DataPointBar) {
            DataPointBar dp = (DataPointBar) dataPoint;
            yPos = dp.y;
            dataPointColor = dp.color; 	
    	}else {
            DataPoint dp = (DataPoint) dataPoint;
            yPos = dp.y;
    	}
        
        x = 0;
        y = 0;
        width = 0;
        height = 0;

        if (yPos > 0) { // greater than zero
            x = (int)(point.x - (barWidth / 2));
            y = point.y;
            width = barWidth;
            height = (int) ((yPos * xyFactor.getyFactor()));

            colorToUse = color;

        } else { // less than zero

            x = (int)(point.x - (barWidth / 2));
            y = point.y + (int) (yPos * xyFactor.getyFactor());
            width = barWidth;
            height = (int) ((-yPos * xyFactor.getyFactor()));

            colorToUse = negativeColor;
        }

        if (dataPointColor != null) {
            colorToUse = dataPointColor;
        }

        muchmuchdarker = colorToUse.darker();
        
        this.clipAndDrawPoint(g, chart);
    }
    
	@Override
	public void drawPoint(Graphics2D g) {

		g.setColor(colorToUse);

        //bottom rect
        g.fillRect(x,
                y,
                (int)width,
                (int)height); 

        g.setColor(muchmuchdarker);

        /**
         * Draw a border
         */
        if (doBorder) {
            //bottom rect
            g.drawRect(x,
                    y,
                    (int)width,
                    (int)height);

        }
	}

	@Override
	public boolean doesShapeContainPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}
}
