package com.bluewalrus.bar;

import java.awt.Color;

import com.bluewalrus.point.Bubble;
import com.bluewalrus.point.XYPoint;

public class Category {

	public XYPoint point;
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

	public Category(String name, XYPoint point, Line line) {

		if (point instanceof Bubble) {
			this.block = true;
		}

		
		this.name = name;
		
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
