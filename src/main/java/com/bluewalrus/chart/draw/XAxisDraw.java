package com.bluewalrus.chart.draw;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bluewalrus.bar.Interval;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.Axis;
import com.bluewalrus.chart.axis.Axis.AxisType;
import com.bluewalrus.chart.axis.XAxis;

/**
 * Does all drawing for XAxis
 * 
 * @author Oliver Watkins
 *
 */

public class XAxisDraw {

	
	public static void drawIntervalTick(Interval interval, Graphics g, Chart chart,
			double fromLeft, Axis axis) {
		
		int y1 = (chart.topOffset + chart.heightChart + axis.marginOffset);
        int y2 = (chart.topOffset + chart.heightChart + axis.marginOffset + interval.lineLength);

        g.drawLine((int)fromLeft, y1, (int)fromLeft, y2);
	} 
	
	/**
	 * TODO for bar charts now only!!!!
	 * 
	 * @param g
	 * @param chart
	 * @param x
	 */
    public static void drawTickLine_BAR(Graphics g, XYChart chart, int x) {
    	
//        g.drawLine((int) (x),
//                (int) (chart.topOffset + chart.xAxis.marginOffset + chart.heightChart),
//                (int) (x),
//                (int) (chart.topOffset + chart.xAxis.marginOffset + chart.heightChart + 2));
    }
    
    
    
	
	public static void drawGridLine(Interval interval, Graphics2D g, Chart chart,
			double fromLeft) {
		int y1 = chart.topOffset + chart.heightChart;

		int y2 = chart.topOffset;

		interval.graphLine.drawLine(g, (int)fromLeft, y1, (int)fromLeft, y2);
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
	
	
	public static void drawYGridLineOnZero(Graphics2D g, Chart chart, int fromTop, Axis axis) {
		
        if (axis.type == AxisType.ENUMERATION) {
            return;
        }
        throw new RuntimeException("TODO");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

    
    
    
	public static void drawXLabel(Graphics g, Chart chart, double fromLeft,
			String xLabel, Axis axis) {
		
        FontMetrics fm = chart.getFontMetrics(axis.axisCatFont);
        int widthStr = fm.stringWidth(xLabel);
        		
		fromLeft = fromLeft - (widthStr / 2); //move back half text length
        
        int yPos = chart.topOffset + chart.heightChart + axis.tickLabelOffset;

        g.setFont(axis.axisCatFont);

        g.drawString(xLabel, (int)fromLeft, yPos);
	}
    
    
    /**
     * Draw label text. For enum bar charts at the moment only
     * @param chart
     * @param name
     * @param g
     * @param x
     */
    public static void drawText_BAR(XYChart chart, String name,
            Graphics g, int x) {

        FontMetrics fm = chart.getFontMetrics(chart.xAxis.axisCatFont);
        int widthStr = fm.stringWidth(name);
        int heightStr = fm.getHeight();

        g.setFont(chart.xAxis.axisCatFont);
        g.setColor(Color.BLACK);

        int xPosition = x - (widthStr / 2);
        int yPosition = chart.topOffset + chart.xAxis.marginOffset + chart.heightChart + chart.xAxis.labelOffset - heightStr / 2;

        //draw tick
        g.drawString(name, xPosition, yPosition);
    }
    
    
    
}
