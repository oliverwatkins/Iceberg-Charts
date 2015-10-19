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

    public void drawTicksAndLabels(Graphics g, Chart chart) {

    	
        if (this.interval1.isValid() && this.interval1.isActive()) {
            drawIntervalTickAndLabels(this.interval1, g, chart, true);
        }
        if (this.interval2.isValid() && this.interval2.isActive()) {
            drawIntervalTickAndLabels(this.interval2, g, chart, false);
        }
        if (this.interval3.isValid() && this.interval3.isActive()) {
            drawIntervalTickAndLabels(this.interval3, g, chart, false);
        }
    }
    
    /**
     * Draw the intervals ticks and labels :
     * 
     *10--|
     *    |
     *   -|
     *    |
     *20--|
     *    |
     * 
     * 
     * @param interval
     * @param g
     * @param chart
     * @param showLabel
     */
    protected abstract void drawIntervalTickAndLabels(Interval interval, Graphics g, Chart chart, boolean showLabel);
    
	protected abstract void drawIntervalTick(Interval interval, Graphics g, 
			Chart chart, int i, int incrementInPixel);
		
    protected abstract void drawIntervalLabel(Interval interval, Graphics g, 
			Chart chart, int i, int incrementInPixel);
    	
    /**
     * Borderline is the OUTER line
     * @param g
     * @param chart
     */
    public abstract void drawBorderLine(Graphics g, Chart chart);

    public abstract void drawLabel(Graphics g, Chart chart);

    protected abstract void drawYGridLineOnZero(Graphics2D g, Chart chart);

    
    public abstract void drawGridLine(Interval tick, Graphics2D g, Chart chart);
    
    public abstract String getName();

}
