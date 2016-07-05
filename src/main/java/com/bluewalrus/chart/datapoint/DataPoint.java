package com.bluewalrus.chart.datapoint;

import java.io.Serializable;
import java.util.Date;

import com.bluewalrus.chart.draw.point.UIPointXY;

/**
 * Super type for boxplots, bar, multibar, piechart, bubble etc. But used individually for
 * things like scatter chart
 * 
 */
public class DataPoint implements Serializable{

	//the types of X
    public double x;
    public Date xDate;
    
    
    public double y;

    public String name; //may have a name for enumeration
    
    public ValueType valueType;

    
    public UIPointXY uiPointXY;

	public DataPoint() {
	}
    /**
     * Y point only. The X value is determined by an equally spaced bar chart
     *
     * @param y
     */
    public DataPoint(double y) {
        this.y = y;
    }

    public DataPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public DataPoint(Date date, double y) {
		valueType = ValueType.X_TIME;
        this.xDate = date;
        this.y = y;
    }
	public DataPoint(String xString, double y) {
		
		valueType = ValueType.X_ENUMARABLE;
        this.y = y;
        this.name = xString;
	}
    

	public void setPoinUI(UIPointXY uiPointXY) {
		this.uiPointXY = uiPointXY;
	}

	@Override
	public String toString() {
		return "DataPoint [x=" + x + ", xDate=" + xDate + ", y=" + y
				+ ", name=" + name + ", uiPointXY=" + uiPointXY + "]";
	}

}
