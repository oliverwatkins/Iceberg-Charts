package com.bluewalrus.chart.axis;

import com.bluewalrus.bar.Interval;
import com.bluewalrus.chart.Chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class XAxis extends Axis {

    public Font xFont = new Font("Arial", Font.PLAIN, 12);

    /**
     * @param name axis name
     */
    public XAxis(String name) {
        super(name, AxisType.LINEAR_NUMERICAL);
    }

    /**
     * 
     * @param name axis name
     * @param type type
     */
    public XAxis(String name, AxisType type) {
        super(name, type);
    }

    public XAxis(Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, String name) {
        super(primaryIncrements, secondaryIncrements, tertiaryIncrements, name);
    }

    public XAxis(Double minValue, Double maxValue, Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, String name) {
        super(minValue, maxValue, primaryIncrements, secondaryIncrements, tertiaryIncrements, name);
    }

    public XAxis(Double minValue, Double maxValue, Interval t1, Interval t2, Interval t3, String name) {
        super(minValue, maxValue, t1, t2, t3, name);
    }

    
    public String getName() {
        return "X Axis";
    }
    
    
	protected void drawIntervalLabel(Interval interval, Graphics g, 
			Chart chart, int i, double incrementInPixel) {
    	
		Double increment = interval.getIncrement();
		
        double factor = getMultiplicationFactor(chart); 
        
        //to first increment
    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(increment, factor);
    	
    	double toFirst = getToFirstIntervalValueFromMin(increment);
    	
        String xLabel = "" + ((i * increment) + toFirst);
        
        FontMetrics fm = chart.getFontMetrics(axisCatFont);
        int widthStr = fm.stringWidth(xLabel);
//        int heightStr = fm.getHeight();

        double xPos = chart.leftOffset + (i * incrementInPixel) - (widthStr / 2) + toFirstInPixels;
        int yPos = chart.topOffset + chart.heightChart + tickLabelOffset;

        g.setFont(axisCatFont);

        g.drawString(xLabel, (int)xPos, yPos);
		
	}



	protected void drawIntervalTick(Interval interval, Graphics g, 
			Chart chart, int i, double incrementInPixel) {
		
		double factor = getMultiplicationFactor(chart); 
		
        //to first increment
    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval.getIncrement(), factor);
		
		Double increment = interval.getIncrement();
		
        double x1 = chart.leftOffset + (i * incrementInPixel) + toFirstInPixels;
//        int x2 = x1;
        int y1 = (chart.topOffset + chart.heightChart + marginOffset);
        int y2 = (chart.topOffset + chart.heightChart + marginOffset + interval.lineLength);

        g.drawLine((int)x1, y1, (int)x1, y2);
		
	}


    @Override
    public void drawGridLine(Interval interval, Graphics2D g, Chart chart) {

		double factor = getMultiplicationFactor(chart); 
    	
        //to first increment
    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval.getIncrement(), factor);

        if (type == AxisType.ENUMERATION) {
            return;
        }

        int incrementNo = (int) (maxValue / interval.getIncrement());
        
        double incrementInPixel = (double) (interval.getIncrement() * factor);

        g.setColor(interval.graphLine.color);

        for (int i = 0; i < incrementNo; i++) {
            double x1 = chart.leftOffset +  (i * incrementInPixel) + toFirstInPixels;
            int y1 = chart.topOffset + chart.heightChart;

            int y2 = chart.topOffset;

            interval.graphLine.drawLine(g, (int)x1, y1, (int)x1, y2);
        }
    }

    @Override
    public void drawLabel(Graphics g, Chart chart) {

        Graphics2D g2d = (Graphics2D) g;

        FontMetrics fmX = chart.getFontMetrics(xFont);
        int xAxisStringWidth = fmX.stringWidth(label);

        //X Label
        int xAxesLabelHeight = chart.bottomOffset - labelOffset;

        //x label        
        g2d.setFont(xFont);
        g2d.drawString(label, chart.widthChart / 2 + chart.leftOffset - xAxisStringWidth / 2, chart.topOffset + chart.heightChart + labelOffset + xAxesLabelHeight / 2);

    }

    @Override
    protected void drawYGridLineOnZero(Graphics2D g, Chart chart) {
        if (type == AxisType.ENUMERATION) {
            return;
        }
        throw new RuntimeException("TODO");

		// TODO Auto-generated method stub
    }

    @Override
    public void drawBorderLine(Graphics g, Chart chart) {

        int x1 = chart.leftOffset;
        int y1 = chart.topOffset + chart.heightChart + marginOffset;
        int x2 = chart.leftOffset + chart.widthChart;
        int y2 = y1;

    	g.setColor(axisColor);
        g.drawLine(x1, y1, x2, y2);
    }

	@Override
	protected double getMultiplicationFactor(Chart chart) {
    	return ((double) chart.widthChart / (double) (maxValue - minValue));

	}


}
