package com.bluewalrus.chart.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.Orientation;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.XYChart;

public abstract class EnumerationAxisDraw extends AxisDraw{

	//enum has always 0 to 100. It is arbitrary.
	public double maxValue = 100; 
	public double minValue = 0; 
	
	protected EnumerationAxisDraw(Orientation orientation) {
		super(orientation);
		
		maxValue = 100; 
		minValue = 0; 
	}
	
	
}
