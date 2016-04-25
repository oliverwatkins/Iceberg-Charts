package com.bluewalrus.chart;

import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointBubble;
import com.bluewalrus.chart.draw.point.UIPointXY;

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



	public ArrayList<? extends DataPoint> dataPoints;

    public UIPointXY pointType; //describes the type of point UI.
    
    public Line line;
    public String name;

    public XYDataSeriesType type;
    public Color seriesColor;

    public XYDataSeries(String name) {
        this.name = name;
    }

	public XYDataSeries(ArrayList<DataPoint> dataPoints, String name) {
		this.name = name;
		this.dataPoints = dataPoints;
	}
    
    public XYDataSeries(ArrayList<? extends DataPoint> dataPoints, UIPointXY point, Line line, String name) {

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

        this.pointType = point;
        this.line = line;
        this.name = name;
    }
    
    
    /**
     * Has line or point been set
     * 
     * @return
     */
    public boolean hasStyleBeenSet() {
    	if (pointType != null || line != null) {
    		return true;
    	}
    	return false;
    }
    
    @Override
	public String toString() {
		return "XYDataSeries [dataPoints=" + dataPoints + ", pointType=" + pointType + ", line=" + line + ", name="
				+ name + ", type=" + type + ", seriesColor=" + seriesColor + "]";
	}



}
