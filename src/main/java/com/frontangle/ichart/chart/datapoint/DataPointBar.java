package com.frontangle.ichart.chart.datapoint;

import java.awt.Color;


public class DataPointBar extends DataPoint {

    public Color color;
    
     //Enumerable version
	public DataPointBar(String xName, double y, Color color) {
		super(-9999, y); 
		this.color = color;
		
		valueType = ValueType.X_ENUMARABLE;
		
		this.name = xName;
	}
    
     //Enumerable version
	public DataPointBar(String xName, double y) {
		super(-9999, y); 
		this.color = Color.BLACK;
		
		valueType = ValueType.X_ENUMARABLE;
		
		this.name = xName;
	}

    public DataPointBar(int x, int y, Color color2, String string) {
        super(x, y);
        this.name = string;
        this.color = color2;
    }
}
