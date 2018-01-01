package com.frontangle.ichart.chart.draw;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.ChartUtils;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.axis.AbstractInterval;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.axis.YAxis;

/**
 * Do all the drawing for the YAxis
 * 
 * @author Oliver Watkins
 */
public class YAxisDrawUtil {
	
	
	 //Draw the label (vertically)
	public static void drawLabel(Chart chart, YAxis axis, Graphics2D g2d) {
		AffineTransform oldTransform = g2d.getTransform();

        FontMetrics fmY = chart.getFontMetrics(axis.font);
        int yAxisStringWidth = fmY.stringWidth(axis.labelText);
        int yAxisStringHeight = fmY.getHeight();

        g2d.setColor(Color.BLACK);

        g2d.rotate(Math.toRadians(270)); //rotates to above out of screen.

        int translateDown;
        
        //starts off being "topOffset" off, so subtract that first
        int translateLeft;
        if (axis.rightSide) {

            translateDown = -(chart.topOffset + chart.heightChart / 2 + yAxisStringWidth / 2);

            translateLeft = chart.leftOffset + chart.widthChart + axis.tickLabelOffset + axis.labelOffset
                    - (axis.labelOffset) / 2;

        } else {
            translateDown = -(chart.topOffset + chart.heightChart / 2 + yAxisStringWidth / 2);
            //starts off being "topOffset" off, so subtract that first
            translateLeft = (chart.leftOffset - axis.tickLabelOffset - axis.marginOffset) / 2 + yAxisStringHeight / 2;
        }

        //pull down, which is basically the left offset, topOffset, then middle it by 
        //usin chart height and using text height.
        g2d.translate(translateDown, translateLeft);

        g2d.setFont(axis.font);

        g2d.drawString(axis.labelText, 0, 0);

        //reset
        g2d.setTransform(oldTransform);
	}
	
	
	 //Draw the interval tick
	public static void drawIntervalTick(AbstractInterval interval, Graphics g, Chart chart,
			double fromTop, YAxis axis) {
		
		if (interval.styling == null) {
			return;
		}
		
		int lineLength = interval.styling.lineLength;
    	
        int x1;
        int x2;

        if (axis.rightSide) {

            x1 = chart.leftOffset + chart.widthChart;
            x2 = chart.leftOffset + chart.widthChart + lineLength;
        } else {

            x1 = chart.leftOffset - axis.marginOffset;
            x2 = chart.leftOffset - axis.marginOffset - lineLength;
        }

        g.setColor(axis.axisColor);
        g.drawLine(x1, (int)fromTop, x2, (int)fromTop);
	}
	
	
	 //Draw a label on the yAxis for an incrememnt eg (10, 20 ,30)
	public static void drawYIntervalLabel(Graphics2D g, XYChart chart, double fromTop, String yLabel, YAxis axis) {
		
		FontMetrics fm = chart.getFontMetrics(axis.axisCatFont);
        int widthStr = fm.stringWidth(yLabel);
        
        int heightStr = fm.getHeight();
        g.setFont(axis.axisCatFont);
        g.setColor(axis.axisColor);

        int x; //TODO should position itself exactly between the label space, and the tick space.
        
        int y; //left baseline of text.
        
        axis.tickLabelOffset = 60; //TODO autogenerate this value.Based on top tick length

        if (axis.rightSide) {
            x = chart.widthChart + chart.leftOffset + (axis.tickLabelOffset / 2 - widthStr / 2);
            y = (int)fromTop + (heightStr / 2);
        } else {

            x = (chart.leftOffset - axis.tickLabelOffset) + (axis.tickLabelOffset / 2 - widthStr / 2) - axis.marginOffset;
            y = (int)fromTop + (heightStr / 2);
        }
        
        g.drawString(yLabel, x, y - fm.getDescent());
	}
	
	 //Draw Y Grid Line
	public static void drawGridLine(AbstractInterval interval, Graphics2D g, Chart chart, double fromTop) {
		
		int x1 = chart.leftOffset;
		int x2 = chart.leftOffset + chart.widthChart;
		
		Shape clip = ChartUtils.clipChart(g, chart);
		
		interval.styling.graphLine.drawLine(g, x1, (int)fromTop, x2, (int)fromTop);
		g.setClip(clip);
	}
}
