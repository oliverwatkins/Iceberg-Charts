package com.frontangle.ichart.chart.datapoint;

import java.io.Serializable;
import java.util.Date;

import com.frontangle.ichart.chart.draw.point.UIPointXY;

/**
 * Super type for boxplots, bar, multibar, piechart, bubble etc. But used
 * individually for things like scatter chart
 * 
 * TODO class holds three types for the X value. Maybe this should be refactored somehow? Generics? Super/SubTypes? Or
 * is it fine the way it is.
 */
public class DataPoint implements Serializable {

	// the types of X
	public double x;
	public Date xDate;
	public String name; // enumeration 
	
	// y is only double for now
	public double y;

	public ValueType valueType;

	public UIPointXY uiPointXY;

	public DataPoint() {
	}

	public DataPoint(double x, double y) {
		valueType = ValueType.X_NUMERICAL;
		this.x = x;
		this.y = y;
	}

	public DataPoint(Date dateX, double y) {
		valueType = ValueType.X_TIME;
		this.xDate = dateX;
		this.y = y;
	}

	public DataPoint(String enumerationValueX, double y) {
		valueType = ValueType.X_ENUMARABLE;
		this.name = enumerationValueX;
		this.y = y;
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
