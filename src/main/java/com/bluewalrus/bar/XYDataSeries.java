/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.bar;

import com.bluewalrus.point.Bubble;
import com.bluewalrus.point.XYPoint;
import com.bluewalrus.datapoint.DataPoint;

import java.awt.Color;
import java.util.ArrayList;

/**
 * TODO figure out how to implement generics. Not so easy
 * 
 * @author Oliver Watkins
 *
 * @param <T>
 */

public class XYDataSeries<T extends DataPoint> implements Categorisable{
	
	public ArrayList<T> dataPoints;
	
	public XYPoint point;
	public Line line;
	public String name;
    
	public XYDataSeriesType type;
	public Color seriesColor;

	public XYDataSeries(String name) {
		this.name = name;
	}
    
	public XYDataSeries(ArrayList<T> dataPoints, XYPoint point, Line line, String name) {

		this(point, line, name);
		
		this.dataPoints = dataPoints;
	}
	
    public XYDataSeries(XYPoint point, Line line, String name) {
		
		if (point instanceof Bubble) {
			type = XYDataSeriesType.BUBBLE;
			seriesColor = point.color;
		}else {
			type = XYDataSeriesType.LINE;
		}
		
		this.point = point;
		this.line = line;
		this.name = name;
	}
	
}
