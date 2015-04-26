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

    public Interval tick1;
    public Interval tick2;
    public Interval tick3;

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
            this.tick1 = new Interval(8, primaryIncrements);
        }
        if (secondaryIncrements != null) {
            this.tick2 = new Interval(4, secondaryIncrements);
        }
        if (tertiaryIncrements != null) {
            this.tick3 = new Interval(2, tertiaryIncrements);
        }
    }

    Axis(Double minValue, Double maxValue, Interval t1, Interval t2, Interval t3, String name) {
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.label = name;

        this.tick1 = t1;
        this.tick2 = t2;
        this.tick3 = t3;
    }

    public void drawAllTickLabels(Graphics g, Chart chart) {
        if (this.tick1 != null && this.tick1.increment != 0) {
            drawTickLabels(this.tick1.increment, g, Color.BLACK, chart);
        }
        if (this.tick2 != null && this.tick2.increment != 0) {
            drawTickLabels(this.tick2.increment, g, Color.BLACK, chart);
        }
        if (this.tick3 != null && this.tick3.increment != 0) {
            drawTickLabels(this.tick3.increment, g, Color.BLACK, chart);
        }
    }

    public void drawTicks(Graphics g, Chart chart) {
        if (this.tick1 != null && this.tick1.increment != 0) {
            drawTick(this.tick1.increment, g, Color.BLACK, this.tick1.lineLength, chart);
        }
        if (this.tick2 != null && this.tick2.increment != 0) {
            drawTick(this.tick2.increment, g, Color.BLACK, this.tick2.lineLength, chart);
        }
        if (this.tick3 != null && this.tick3.increment != 0) {
            drawTick(this.tick3.increment, g, Color.BLACK, this.tick3.lineLength, chart);
        }
    }

    public abstract void drawBorderLine(Graphics g, Chart chart);

    public abstract void drawLabel(Graphics g, Chart chart);

    protected abstract void drawYGridLineOnZero(Graphics2D g, Chart chart);

    protected abstract void drawTick(Double increment, Graphics g, Color c, int tickWidth, Chart chart);

    public abstract void drawTickLabels(Double increment, Graphics g, Color c, Chart chart);

    public abstract void drawGridLine(Interval tick, Graphics2D g, Chart chart);

}
