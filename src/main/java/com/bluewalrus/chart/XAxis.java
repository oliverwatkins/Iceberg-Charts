package com.bluewalrus.chart;

import com.bluewalrus.bar.Interval;
import com.bluewalrus.chart.Chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class XAxis extends Axis {

    public Font xFont = new Font("Arial", Font.PLAIN, 12);

    public XAxis(String name) {
        super(name, AxisType.STANDARD);
    }

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
    
    
	protected void drawIntervalTickAndLabels(Interval interval, Graphics g,
			 Chart chart, boolean showLabel) {
		
		Double increment = interval.getIncrement();

        if (type == AxisType.BLANK) {
            return;
        }
        int incrementNo = (int) ((maxValue - minValue) / increment);

        double xScalingFactor = ((double) chart.widthChart / (double) (maxValue - minValue));

        int incrementInPixel = (int) (increment * xScalingFactor);

        g.setColor(this.axisColor);
        
        for (int i = 0; i < incrementNo; i++) {
        	drawIntervalTick(interval, g, chart, i, incrementInPixel);
        	
        	if (showLabel)
        		drawIntervalLabel(interval, g, chart, i, incrementInPixel);
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
    
    
	protected void drawIntervalLabel(Interval interval, Graphics g, 
			Chart chart, int i, int incrementInPixel) {
    	
		Double increment = interval.getIncrement();
    	
        String xLabel = "" + ((i * increment) + minValue);
        FontMetrics fm = chart.getFontMetrics(axisCatFont);
        int widthStr = fm.stringWidth(xLabel);
//        int heightStr = fm.getHeight();

        int xPos = chart.leftOffset + (i * incrementInPixel) - (widthStr / 2);
        int yPos = chart.topOffset + chart.heightChart + tickLabelOffset;

        g.setFont(axisCatFont);

        g.drawString(xLabel, xPos, yPos);
		
	}

	protected void drawIntervalTick(Interval interval, Graphics g, 
			Chart chart, int i, int incrementInPixel) {
		
		Double increment = interval.getIncrement();
		
        int x1 = chart.leftOffset + (int) (i * incrementInPixel);
        int x2 = x1;
        int y1 = (chart.topOffset + chart.heightChart + marginOffset);
        int y2 = (chart.topOffset + chart.heightChart + marginOffset + interval.lineLength);

        g.drawLine(x1, y1, x2, y2);
		
	}


    @Override
    public void drawGridLine(Interval tick, Graphics2D g, Chart chart) {

        if (type == AxisType.BLANK) {
            return;
        }

        int incrementNo = (int) (maxValue / tick.getIncrement());
        double factor = ((double) chart.widthChart / (double) maxValue);
        double incrementInPixel = (double) (tick.getIncrement() * factor);

        g.setColor(tick.graphLine.color);

        for (int i = 0; i < incrementNo; i++) {
            int x1 = chart.leftOffset + (int) (i * incrementInPixel);
            int y1 = chart.topOffset + chart.heightChart;

            int x2 = chart.leftOffset + (int) (i * incrementInPixel);
            int y2 = chart.topOffset;

            tick.graphLine.drawLine(g, x1, y1, x2, y2);
        }
    }



    @Override
    protected void drawYGridLineOnZero(Graphics2D g, Chart chart) {
        if (type == AxisType.BLANK) {
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


}
