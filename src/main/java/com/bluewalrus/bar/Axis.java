/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluewalrus.bar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bluewalrus.chart.Chart;

/**
 * Abstract Axis. Sub types are X and Y. Possible Z Axis in future
 *
 * @author Oliver Watkins
 */
public abstract class Axis {

    public AxisType type;

    public enum AxisType {

        BLANK, STANDARD
    }

    public int tickLabelOffset = 40;
    public int labelOffset = 40;
    public int marginOffset = 6;

    public Line zeroLine = new Line(Color.GRAY, false, 1);

    public Font axisCatFont = new Font("Arial", Font.BOLD, 12);
    public String label;

    public double maxValue = 100;
    public double minValue = 0;

    public Interval interval1;
    public Interval interval2;
    public Interval interval3;

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

        this.interval1 = interval1;
        this.interval2 = interval2;
        this.interval3 = interval3;
    }
    
    /**
     * 
     * @param g
     * @param chart 
     */
    public void drawIntervals(Graphics g, Chart chart) {
        if (this.interval1 != null && this.interval1.increment != 0) {
            drawTick(this.interval1.increment, g, Color.BLACK, this.interval1.lineLength, chart);
        }
        if (this.interval2 != null && this.interval2.increment != 0) {
            drawTick(this.interval2.increment, g, Color.BLACK, this.interval2.lineLength, chart);
        }
        if (this.interval3 != null && this.interval3.increment != 0) {
            drawTick(this.interval3.increment, g, Color.BLACK, this.interval3.lineLength, chart);
        }
    }
    /**
     * 
     * @param g
     * @param chart 
     */
    public void drawAllIntervalLabels(Graphics g, Chart chart) {
        if (this.interval1 != null && this.interval1.increment != 0) {
            drawIntervalLabels(this.interval1.increment, g, Color.BLACK, chart);
        }
        if (this.interval2 != null && this.interval2.increment != 0) {
            drawIntervalLabels(this.interval2.increment, g, Color.BLACK, chart);
        }
        if (this.interval3 != null && this.interval3.increment != 0) {
            drawIntervalLabels(this.interval3.increment, g, Color.BLACK, chart);
        }
    }



    public abstract void drawBorderLine(Graphics g, Chart chart);

    public abstract void drawLabel(Graphics g, Chart chart);

    protected abstract void drawYGridLineOnZero(Graphics2D g, Chart chart);

    protected abstract void drawTick(Double increment, Graphics g, Color c, int tickWidth, Chart chart);

    public abstract void drawIntervalLabels(Double increment, Graphics g, Color c, Chart chart);

    public abstract void drawGridLine(Interval tick, Graphics2D g, Chart chart);
}
