package com.bluewalrus.chart;

import com.bluewalrus.bar.Interval;
import com.bluewalrus.bar.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * @copyright 2014
 * @author Oliver Watkins (www.blue-walrus.com)

 All Rights Reserved
 */
public class YAxis extends Axis {

    public Font yFont = new Font("Arial", Font.PLAIN, 12);
    public boolean rightSide = false;
	

    public YAxis(String name) {
        super(name, Axis.AxisType.STANDARD);
    }

    public YAxis(Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, String name) {
        super(primaryIncrements, secondaryIncrements, tertiaryIncrements, name);
    }

    public YAxis(Double minValue, Double maxValue, Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, String name) {
        super(minValue, maxValue, primaryIncrements, secondaryIncrements, tertiaryIncrements, name);
    }

    public YAxis(Double minValue, Double maxValue, Interval t1, Interval t2, Interval t3, String name) {
        super(minValue, maxValue, t1, t2, t3, name);
    }

    /**
     * If Y passes through ZERO then draw a line
     */
    public void drawYGridLineOnZero(Graphics2D g, Chart chart) {

        double factor = Utils.getFactor(chart.heightChart, maxValue, minValue);

        if (minValue < 0) {

            int fromTop = (int) (chart.heightChart + chart.topOffset + (minValue * factor));

            zeroLine.drawLine(g, chart.leftOffset, fromTop, chart.leftOffset + chart.widthChart, fromTop);
        }
    }

    public void drawGridLine(Interval tick, Graphics2D g, Chart chart) {

        int incrementNo = (int) ((maxValue - minValue) / tick.getIncrement());

        double factor = Utils.getFactor(chart.heightChart, maxValue, minValue);

        double incrementInPixel = (double) (tick.getIncrement() * factor);

        for (int i = 0; i < incrementNo; i++) {
            int x1 = chart.leftOffset;
            int y1 = chart.heightChart + chart.topOffset - (int) (i * incrementInPixel);
            int x2 = chart.leftOffset + chart.widthChart;
            int y2 = y1;

            tick.graphLine.drawLine(g, x1, y1, x2, y2);
        }
    }
    
    
    protected void drawTickAndLabels(Interval tick, Graphics g, Color c, int lineLength, Chart chart) {
    	
    	Double increment = tick.getIncrement();
    	
        int incrementNo = (int) ((maxValue - minValue) / increment);

        double factor = ((double) chart.heightChart / (double) (maxValue - minValue));

        double incrementInPixel = (double) (increment * factor);
        
        
//    	double toZeroShift = getToZeroShift(increment, maxValue, minValue, factor);
        
        g.setColor(c);
        
        //building from bottom to top
        for (int i = 0; i < incrementNo; i++) {
        	drawTick_new(tick.getIncrement(), g, c, lineLength, chart, i, incrementInPixel);
        	drawLabel_new(tick.getIncrement(), g, c, lineLength, chart, i, incrementInPixel);
        }
    }

    private void drawLabel_new(Double increment, Graphics g, Color c, int tickWidth, Chart chart, int i, double incrementInPixel) {
    	
    	FontMetrics fm = chart.getFontMetrics(axisCatFont);
    	 
    	double fromTop = chart.heightChart + chart.topOffset - (i * incrementInPixel);
    	
    	
        String yLabel = "" + ((i * increment) + minValue);
        int widthStr = fm.stringWidth(yLabel);
        int heightStr = fm.getHeight();
        g.setFont(axisCatFont);

        int x;
        int y;

        if (rightSide) {
            x = chart.widthChart + chart.leftOffset + (tickLabelOffset / 2 - widthStr / 2);
            y = (int)fromTop + (heightStr / 2);
        } else {

            x = (chart.leftOffset - tickLabelOffset) + (tickLabelOffset / 2 - widthStr / 2) - marginOffset;
            y = (int)fromTop + (heightStr / 2);
        }
        g.drawString(yLabel, x, y); 
		
	}

	private void drawTick_new(Double increment, Graphics g, Color c, int tickWidth, Chart chart, int i, double incrementInPixel) {
		
		
		
    	double toZeroShift = 0; //getToZeroShift(increment, maxValue, minValue, factor);

    	double fromTop = chart.heightChart + chart.topOffset - (i * incrementInPixel) + toZeroShift;
    	
        int x1;
        int x2;

        if (rightSide) {

            x1 = chart.leftOffset + chart.widthChart;
            x2 = chart.leftOffset + chart.widthChart + tickWidth;
        } else {

            x1 = chart.leftOffset - marginOffset;
            x2 = chart.leftOffset - marginOffset - tickWidth;
        }

        g.drawLine(x1, (int)fromTop, x2, (int)fromTop);
	}

//	/**
//     * Draw a tick lines
//     *
//     * @param heightChart
//     * @param increment
//     * @param g
//     * @param c
//     * @param tickWidth
//     */
//    protected void drawTick(Double increment, Graphics g, Color c, int tickWidth, Chart chart) {
//
//        int incrementNo = (int) ((maxValue - minValue) / increment);
//
//        double factor = ((double) chart.heightChart / (double) (maxValue - minValue));
//
//        double incrementInPixel = (double) (increment * factor);
//        
//        g.setColor(c);
//
//        for (int i = 0; i < incrementNo; i++) {
//        	
//        	
//        	double toZeroShift = 0; //getToZeroShift(increment, maxValue, minValue, factor);
//
//        	double fromTop = chart.heightChart + chart.topOffset - (i * incrementInPixel) + toZeroShift;
//        	
//            int x1;
//            int x2;
//
//            if (rightSide) {
//
//                x1 = chart.leftOffset + chart.widthChart;
//                x2 = chart.leftOffset + chart.widthChart + tickWidth;
//            } else {
//
//                x1 = chart.leftOffset - marginOffset;
//                x2 = chart.leftOffset - marginOffset - tickWidth;
//            }
//
//            g.drawLine(x1, (int)fromTop, x2, (int)fromTop);
//
//        }
//    }


    private double getToZeroShift(Double increment, double maxValue,
			double minValue, double factor) {
    	
    	double startValue = minValue;
    	
    	while (startValue % increment != 0) {
    		startValue++;
    	}
    	
		return (startValue - minValue) * factor;
	}

//	/**
//     * Number/Value next to the tick
//     */
//    public void drawIntervalLabels(Double increment, Graphics g, Color c, Chart chart) {
//
//        int incrementNo = (int) ((maxValue - minValue) / increment);
//
//        double factor = ((double) chart.heightChart / (double) (maxValue - minValue));
//
//        double incrementInPixel = (double) (increment * factor);
//        
//        
////    	double toZeroShift = getToZeroShift(increment, maxValue, minValue, factor);
//    	
//        
//        
//        g.setColor(c);
//
//        
//        
//        FontMetrics fm = chart.getFontMetrics(axisCatFont);
//
//        
//        //building from bottom to top
//        for (int i = 0; i < incrementNo; i++) {
//
//        	double fromTop = chart.heightChart + chart.topOffset - (i * incrementInPixel);
//        	
//        	
//            String yLabel = "" + ((i * increment) + minValue);
//            int widthStr = fm.stringWidth(yLabel);
//            int heightStr = fm.getHeight();
//            g.setFont(axisCatFont);
//
//            int x;
//            int y;
//
//            if (rightSide) {
//                x = chart.widthChart + chart.leftOffset + (tickLabelOffset / 2 - widthStr / 2);
//                y = (int)fromTop + (heightStr / 2);
//            } else {
//
//                x = (chart.leftOffset - tickLabelOffset) + (tickLabelOffset / 2 - widthStr / 2) - marginOffset;
//                y = (int)fromTop + (heightStr / 2);
//            }
//            g.drawString(yLabel, x, y); 
//        }
//    }
//    
    
    @Override
    public void drawBorderLine(Graphics g, Chart chart) {

        int x1 = chart.leftOffset - marginOffset;
        int y1 = chart.topOffset;
        int x2 = x1; //chart.leftOffset + chart.widthChart;
        int y2 = chart.topOffset + chart.heightChart;

        g.setColor(axisColor);
        g.drawLine(x1, y1, x2, y2);
    }

    public void drawLabel(Graphics g, Chart chart) {

        Graphics2D g2d = (Graphics2D) g;

        AffineTransform oldTransform = g2d.getTransform();

        FontMetrics fmY = chart.getFontMetrics(yFont);
        int yAxisStringWidth = fmY.stringWidth(label);
        int yAxisStringHeight = fmY.getHeight();

        g2d.setColor(Color.BLACK);

        g2d.rotate(Math.toRadians(270)); //rotates to above out of screen.

        int translateDown;
        //starts off being "topOffset" off, so subtract that first
        int translateLeft;
        if (rightSide) {

            translateDown = -(chart.topOffset + chart.heightChart / 2 + yAxisStringWidth / 2);

            translateLeft = chart.leftOffset + chart.widthChart + tickLabelOffset + labelOffset
                    - (labelOffset) / 2;

        } else {
            translateDown = -(chart.topOffset + chart.heightChart / 2 + yAxisStringWidth / 2);
            //starts off being "topOffset" off, so subtract that first
            translateLeft = (chart.leftOffset - tickLabelOffset - marginOffset) / 2 + yAxisStringHeight / 2;
        }

        //pull down, which is basically the left offset, topOffset, then middle it by 
        //usin chart height and using text height.
        g2d.translate(translateDown, translateLeft);

        g2d.setFont(yFont);

        g2d.drawString(label, 0, 0);

        //reset
        g2d.setTransform(oldTransform);
    }
    
    public String getName() {
        return "Y Axis";
    }


}
