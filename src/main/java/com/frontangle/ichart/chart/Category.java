package com.frontangle.ichart.chart;

import java.awt.Color;

import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointBubble;
import com.frontangle.ichart.chart.draw.point.UIPointXY;

/**
 * @author Oliver Watkins
 */
public class Category {

    public UIPointXY point;
    public Line line;
    public String name;

    public Color color;
    public Color lineColor;
    public Color pointColor;

    public boolean block;

    public Category(String name, Color color) {
        this.block = true;
        this.name = name;
        this.color = color;
    }

    public Category(String name, UIPointXY point, Line line) {

        this.name = name;

        /**
         * If XY chart is bubble then the point shape is just a filled in square
         */
        if (point instanceof UIPointBubble) {
            this.block = true;
        }

        if (point != null) {
            this.point = point;
            this.pointColor = point.color;
        }
        if (line != null) {
            this.line = line;
            this.lineColor = line.color;
        }
    }
}
