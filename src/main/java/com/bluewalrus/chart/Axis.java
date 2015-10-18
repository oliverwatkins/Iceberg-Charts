/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluewalrus.chart;

import com.bluewalrus.bar.Interval;
import com.bluewalrus.bar.Line;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

import com.bluewalrus.chart.Chart;

/**
 * Abstract Axis. Sub types are X and Y. Possible Z Axis in future
 *
 * @author Oliver Watkins
 */
public abstract class Axis implements Serializable{

    public AxisType type;

    public Color axisColor = Color.BLACK;

    public enum AxisType {

        BLANK, STANDARD
    }

    public int tickLabelOffset = 40; //TODO this should be seperate for each Axis. X and Y have slightly different spacing.
    public int labelOffset = 40;
    public int marginOffset = 6;

    public Line zeroLine = new Line(Color.GRAY, false, 1);

    public Font axisCatFont = new Font("Arial", Font.BOLD, 12);
    public String label;

    public double maxValue = 100;
    public double minValue = 0;

    public Interval interval1 = new Interval(0,0.0);
    public Interval interval2 = new Interval(0,0.0);
    public Interval interval3 = new Interval(0,0.0);

    /**
     * Default constructor
     *
     * @param name
     * @param type
     */
    Axis(String name, AxisType type) {
        this(0.0, 100.0, 50.0, 10.0, 5.0, name);

        this.type = type;
    }

    Axis(Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, String name) {
        this(0.0, 100.0, primaryIncrements, secondaryIncrements, tertiaryIncrements, name);
    }

    Axis(Double minValue, Double maxValue, Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, String name) {

        this.maxValue = maxValue;
        this.minValue = minValue;

        this.label = name;

        if (primaryIncrements != null) {
            this.interval1 = new Interval(8, primaryIncrements);
        }
        if (secondaryIncrements != null) {
            this.interval2 = new Interval(4, secondaryIncrements);
        }
        if (tertiaryIncrements != null) {
            this.interval3 = new Interval(2, tertiaryIncrements);
        }
    }

    /**
     * 
     * @param minValue minimum axis value 
     * @param maxValue maximum axis value
     * @param interval1
     * @param interval2
     * @param interval3
     * @param name 
     */
    Axis(Double minValue, Double maxValue, Interval interval1, Interval interval2, Interval interval3, String name) {
        
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.label = name;

        if (interval1 == null) {
            this.interval1 = new Interval(0,0.0);
            this.interval1.setActive(false);
        } else {
            this.interval1 = interval1;
        }
        if (interval2 == null) {
            this.interval2 = new Interval(0,0.0);
            this.interval2.setActive(false);
        }else {
            this.interval2 = interval2;
        }
        if (interval3 == null) {
            this.interval3 = new Interval(0,0.0);
            this.interval3.setActive(false);
        }else {
            this.interval3 = interval3;
        }
    }
//    protected void drawTickAndLabels(Interval tick, Graphics g, Color c, int tickWidth, Chart chart) {

    public void drawTicksAndLabels(Graphics g, Chart chart) {
    	

    	System.out.println("interval1.toString() " + interval1.toString());
    	
    	
    	Double i = interval1.getIncrement();
    	if (i != 0) {
    		
    		
//    		@Override
//    		public String toString() {
//    			return "Interval [active=" + active + ", graphLine=" + graphLine
//    					+ ", lineLength=" + lineLength + ", increment=" + increment
//    					+ "]";
//    		}
    		
    		System.out.println("interval is " + i);
    	}

    	
        if (this.interval1.isValid() && this.interval1.isActive()) {
            drawTickAndLabels(this.interval1, g, this.axisColor, this.interval1.lineLength, chart);
//            drawTicksAndLabels(g, chart);
        	
//        	System.out.println("i = " + i);
        }
        if (this.interval2.isValid() && this.interval2.isActive()) {
            drawTickAndLabels(this.interval2, g, this.axisColor, this.interval2.lineLength, chart);
//            drawTick(this.interval2.getIncrement(), g, this.axisColor, this.interval2.lineLength, chart);
        }
        if (this.interval3.isValid() && this.interval3.isActive()) {
            drawTickAndLabels(this.interval3, g, this.axisColor, this.interval3.lineLength, chart);
//            drawTick(this.interval3.getIncrement(), g, this.axisColor, this.interval3.lineLength, chart);
        }
    }
    
    


//	/**
//     * 
//     * @param g
//     * @deprecated
//     * @param chart 
//     */
//    public void drawIntervals(Graphics g, Chart chart) {
//        if (this.interval1.isValid() && this.interval1.isActive()) {
//            drawTick(this.interval1.getIncrement(), g, this.axisColor, this.interval1.lineLength, chart);
//        }
//        if (this.interval2.isValid() && this.interval2.isActive()) {
//            drawTick(this.interval2.getIncrement(), g, this.axisColor, this.interval2.lineLength, chart);
//        }
//        if (this.interval3.isValid() && this.interval3.isActive()) {
//            drawTick(this.interval3.getIncrement(), g, this.axisColor, this.interval3.lineLength, chart);
//        }
//    }
//    /**
//     * 
//     * @param g
//     * @param chart 
//     * @deprecated
//     */
//    public void drawAllIntervalLabels(Graphics g, Chart chart) {
//        if (this.interval1.isValid() && this.interval1.isActive()) {
//            drawIntervalLabels(this.interval1.getIncrement(), g, Color.BLACK, chart);
//        }
//        if (this.interval2.isValid() && this.interval2.isActive()) {
//            drawIntervalLabels(this.interval2.getIncrement(), g, Color.BLACK, chart);
//        }
//        if (this.interval3.isValid() && this.interval3.isActive()) {
//            drawIntervalLabels(this.interval3.getIncrement(), g, Color.BLACK, chart);
//        }
//    }


    /**
     * Borderline is the OUTER line
     * @param g
     * @param chart
     */
    public abstract void drawBorderLine(Graphics g, Chart chart);

    public abstract void drawLabel(Graphics g, Chart chart);

    protected abstract void drawYGridLineOnZero(Graphics2D g, Chart chart);

    /**
     * Draw ticks and tick line
     * @param increment
     * @param g
     * @param c
     * @param tickWidth
     * @param chart
     */
//    @Deprecated
//    protected abstract void drawTick(Double increment, Graphics g, Color c, int tickWidth, Chart chart);

//    @Deprecated
//    public abstract void drawIntervalLabels(Double increment, Graphics g, Color c, Chart chart);
//    
//    
    protected abstract void drawTickAndLabels(Interval interval12, Graphics g, Color axisColor2, int lineLength, Chart chart);
    
//    protected abstract void drawTickAndLabel(Double increment, Graphics g, Color c, int tickWidth, Chart chart);


    public abstract void drawGridLine(Interval tick, Graphics2D g, Chart chart);
    
    public abstract String getName();

}
