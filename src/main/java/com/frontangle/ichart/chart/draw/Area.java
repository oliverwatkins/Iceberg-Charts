package com.frontangle.ichart.chart.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import com.frontangle.ichart.chart.draw.Area.AreaType;

/**
 * Area config object
 * 
 * @author Oliver Watkins
 *
 */
public class Area {

	public enum AreaType {
		STACKED, OVERLAP
	}

	public AreaType type = Area.AreaType.OVERLAP; //default is overlap
	
	
	public Color color = Color.RED;

	public Area(Color color) {
		this.color = color;
	}

	public Area(Color color, AreaType type) {
		this(color);
		this.type = type;
	}

}
