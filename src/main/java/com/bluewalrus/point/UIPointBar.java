package com.bluewalrus.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.renderer.XYFactor;

public class UIPointBar extends UIPointAbstractBar {

    boolean doBorder = true;
    Color negativeColor;
    
	private int x;
	private int y;
	private double width;
	private double height;
	
	
	private Color muchmuchdarker;
	private Color colorToUse;


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
        this.pointDiffWidth = barWidth;
    }

    public void draw(Graphics2D g, 
    		Point point, Point lastPoint, DataPoint dataPoint, XYFactor xyFactor, XYChart chart, int pixBtnFirst2Pts) {

    	
    	double barWidth = 10; //px
    	
    	barWidthPercent = 100;
    	
    	if (lastPoint != null) {
        	pointDiffWidth = (point.x - lastPoint.x);
    	}
    	
    	if (barWidthPercent != 0) {
    		barWidth = ((barWidthPercent * (double)pointDiffWidth)/ 100.0); 
    	}else {
    		barWidth = 50; //default
    	}

    	
		System.out.println("1 bar width = " + barWidth);

    	
    	barWidth = pixBtnFirst2Pts;
    	
		System.out.println("2 bar width = " + barWidth);

    	
//    	barWidth = 100;
    	
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
            height = (int) ((yPos * xyFactor.yFactor));

            colorToUse = color;

        } else { // less than zero

            x = (int)(point.x - (barWidth / 2));
            y = point.y + (int) (yPos * xyFactor.yFactor);
            width = barWidth;
            height = (int) ((-yPos * xyFactor.yFactor));

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
                (int)height); // - xyFactor.yZeroOffsetInPixel));

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
