/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluewalrus.datapoint;


public class DataPoint {

	
	/**
	 * Y point only. The X value is determined by an equally spaced bar chart
	 * @param y
	 */
    public DataPoint(double y) {
        this.y = y;
    }
	
    public DataPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double x;
    public double y;
    
	public String name; //may have a name

}
