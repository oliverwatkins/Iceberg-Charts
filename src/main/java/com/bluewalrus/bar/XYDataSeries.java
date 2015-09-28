package com.bluewalrus.bar;

import com.bluewalrus.point.UIPointBubble;
import com.bluewalrus.point.UIPointXY;
import com.bluewalrus.datapoint.DataPoint;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * TODO figure out how to implement generics. Not so easy
 *
 * @author Oliver Watkins
 *
 * @param <T>
 */
public class XYDataSeries<T extends DataPoint> implements Categorisable, Serializable {

    public ArrayList<T> dataPoints;

    public UIPointXY point;
    public Line line;
    public String name;

    public XYDataSeriesType type;
    public Color seriesColor;

    public XYDataSeries(String name) {
        this.name = name;
    }

    public XYDataSeries(ArrayList<T> dataPoints, UIPointXY point, Line line, String name) {

        this(point, line, name);

        this.dataPoints = dataPoints;
    }

    public XYDataSeries(UIPointXY point, Line line, String name) {

        if (point instanceof UIPointBubble) {
            type = XYDataSeriesType.BUBBLE;
            seriesColor = point.color;
        } else {
            type = XYDataSeriesType.LINE;
        }

        this.point = point;
        this.line = line;
        this.name = name;
    }

}
