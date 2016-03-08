package com.bluewalrus.datapoint;

import java.awt.Color;


public class DataPointBar extends DataPoint {

    public Color color; //TODO get rid of this
    
    public ValueType valueType;
    
	public DataPointBar(String xName, double y, Color color) {
		super(-9999, y); 
		this.color = color;
		
		valueType = ValueType.X_ENUMARABLE;
		
		this.name = xName;
	}
	
    public DataPointBar(double x, double y, String name) {
        super(x, y);
        this.name = name;
    }

    public DataPointBar(int x, int y, Color color2, String string) {
        super(x, y);
        this.name = string;
        this.color = color2;
    }
    
    
	
	public DataPointBar(double x, double y, Color color) {
		super(x, y);
		this.color = color;
		valueType = ValueType.NUMERICAL;

	}
    
    public DataPointBar(double x, double y) {
        super(x, y);
    }




}
