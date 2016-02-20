package com.bluewalrus.chart.draw;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.AbstractInterval;
import com.bluewalrus.chart.axis.Axis;
import com.bluewalrus.chart.axis.XAxis;

/**
 * Tick, GridLine, Label, XLabel
 * 
 * @author Oliver Watkins
 *
 */

public class XAxisDrawUtil {

	
	public static void drawIntervalTick(AbstractInterval interval, Graphics g, Chart chart,
			double fromLeft, Axis axis) {
		
		int y1 = (chart.topOffset + chart.heightChart + axis.marginOffset);
        int y2 = (chart.topOffset + chart.heightChart + axis.marginOffset + interval.lineLength);

        g.drawLine((int)fromLeft, y1, (int)fromLeft, y2);
	} 
    
    
	
	public static void drawGridLine(AbstractInterval interval, Graphics2D g, Chart chart,
			double fromLeft) {
		
		int y1 = chart.topOffset + chart.heightChart;
		int y2 = chart.topOffset;

		interval.graphLine.drawLine(g, (int)fromLeft, y1, (int)fromLeft, y2);
	}
	
	/**
	 * TODO grid fill is out by one pixel when compared to grid lines.
	 * 
	 * @param interval
	 * @param g
	 * @param chart
	 * @param fromLeft
	 * @param incrementNo
	 * @param incrementInPixels
	 */
	public static void drawGridFill(AbstractInterval interval, Graphics2D g,
			XYChart chart, double fromLeft, int incrementNo, double incrementInPixels) {
		
		int y1 = chart.topOffset + chart.heightChart;
		int y2 = chart.topOffset;
		
		int height = y1 - y2;
		
		
		interval.graphLine.fillArea(g, (int)fromLeft, y2, incrementInPixels, height, chart);
		
	}
	
	/**
	 * 
	 * @param chart
	 * @param axis
	 * @param g2d
	 */
	public static void drawLabel(Chart chart, XAxis axis, Graphics2D g2d) {

        FontMetrics fmX = chart.getFontMetrics(axis.font);
        int xAxisStringWidth = fmX.stringWidth(axis.labelText);

        //X Label
        int xAxesLabelHeight = chart.bottomOffset - axis.labelOffset;

        //x label        
        g2d.setFont(axis.font);
        g2d.drawString(axis.labelText, chart.widthChart / 2 + chart.leftOffset - xAxisStringWidth / 2, 
        		chart.topOffset + chart.heightChart + axis.labelOffset + xAxesLabelHeight / 2);

	}
    
    
	public static void drawXLabel(Graphics g, Chart chart, double fromLeft,
			String xLabel, Axis axis, int intervalLevel) {
		
        FontMetrics fm = chart.getFontMetrics(axis.axisCatFont);
        int widthStr = fm.stringWidth(xLabel);
        		
		fromLeft = fromLeft - (widthStr / 2); //move back half text length
        
        int yPos = chart.topOffset + chart.heightChart + axis.tickLabelOffset;

        if (intervalLevel == 1) {
        	
        }else if (intervalLevel == 2) {
        	yPos = yPos -5;
        }else if (intervalLevel == 3) {
        	yPos = yPos -10;
        }
        
        g.setFont(axis.axisCatFont);
        g.drawString(xLabel, (int)fromLeft, yPos);
	}


	public static void drawLeftFill(AbstractInterval interval, Graphics2D g,
			XYChart chart, double toFirstInPixels, double fromLeft, double incrementInPixel) {
		
		int y1 = chart.topOffset + chart.heightChart;
		int y2 = chart.topOffset;
		
		int height = y1 - y2;
		
		int xPos = (chart.leftOffset + (int)toFirstInPixels) - (int)incrementInPixel;
		
		interval.graphLine.fillArea(g, xPos, y2, toFirstInPixels, height, chart);
	}



	public static void drawRightFill(AbstractInterval interval, Graphics2D g,
			XYChart chart, double toFirstInPixels, double fromLeft, int incrementNo, double incrementInPixel) {
		
		
		int y1 = chart.topOffset + chart.heightChart;
		int y2 = chart.topOffset;
		
		int height = y1 - y2;
		
		int leftPoint = chart.leftOffset + (int)toFirstInPixels + ((int)incrementInPixel * (incrementNo));
		
		interval.graphLine.fillArea(g, leftPoint, y2, incrementInPixel, height, chart);
		
	}

}
