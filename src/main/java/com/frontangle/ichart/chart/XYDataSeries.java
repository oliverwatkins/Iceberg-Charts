package com.frontangle.ichart.chart;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Area;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointBubble;
import com.frontangle.ichart.chart.draw.point.UIPointMultiBarSideBySide;
import com.frontangle.ichart.chart.draw.point.UIPointMultiBarStacked;
import com.frontangle.ichart.chart.draw.point.UIPointXY;

/**
 * TODO figure out how to implement generics. Not so easy
 *
 * @author Oliver Watkins
 *
 * @param <T> data point
 */
public class XYDataSeries<T extends DataPoint> implements Categorisable, Serializable {

	//data points
	public ArrayList<T> dataPoints;

	//describes the type of point UI.
    public UIPointXY pointType; 
    
    public Line line;
    
	public Area area;
    
    public String name;

    public XYDataSeriesType type;
    public Color seriesColor;

    public XYDataSeries(String name) {
        this.name = name;
    }

	public XYDataSeries(ArrayList<T> dataPoints, String name) {
		this.name = name;
		this.dataPoints = dataPoints;
	}
	
    public XYDataSeries(ArrayList<T> dataPoints, UIPointXY point, Line line, String name) {

        this(point, line, name);

        this.dataPoints = dataPoints;
    }

    public XYDataSeries(UIPointXY point, Line line, String name) {
    	
        if (point instanceof UIPointBubble) {
            type = XYDataSeriesType.BUBBLE;
            seriesColor = point.color;
        } else if (point instanceof UIPointMultiBarSideBySide) {
            type = XYDataSeriesType.MULTI_BAR;
        } else if (point instanceof UIPointMultiBarStacked) {
            type = XYDataSeriesType.MULTI_BAR;
            
        } else if (area != null) {
            type = XYDataSeriesType.AREA;
            seriesColor = area.color;
        } else {
            type = XYDataSeriesType.LINE;
        }
        this.pointType = point;
        this.line = line;
        this.name = name;
    }
    
	public void setArea(Area area) {
		type = XYDataSeriesType.AREA;
		this.area = area;
	}


	/**
     * Has line or point been set
     * 
     * @return has the style been set
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
