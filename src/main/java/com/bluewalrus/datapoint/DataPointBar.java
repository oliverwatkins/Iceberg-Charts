package com.bluewalrus.datapoint;

import java.awt.Color;


public class DataPointBar extends DataPoint {

    public String xName;

    public Color color;
    
    public ValueType valueType;

    
	public DataPointBar(String xName, double y, Color color) {
		super(-9999, y); //
		this.color = color;
		
		valueType = ValueType.X_ENUMARABLE;
		
		this.xName = xName;
	}
	
	public DataPointBar(double x, double y, Color color) {
		super(x, y);
		this.color = color;
		valueType = ValueType.NUMERICAL;

	}
    
    public DataPointBar(double x, double y) {
        super(x, y);
    }

    public DataPointBar(double x, double y, String name) {
        super(x, y);
        this.xName = name;
    }

    public DataPointBar(int i, int j, Color color2, String string) {
        super(i, j);
        this.xName = string;
        this.color = color2;
    }


}
