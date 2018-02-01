/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.frontangle.ichart.pie;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Area;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Categorisable;

public class Segment implements Categorisable{
    
    public double startAngle;//starting angle (calculated)
    public double angle; // from magnitude (calculated)
    
    public Color color; //color (supplied by user)
    int level; //level 0 = first level pie chart (supplied by user)
    Segment parent; //parent segment (supplied by user)
    public Double magnitude; //percent (supplied by user)
    public String name; //name of segment (supplied by user)
    
    Area area; //geometric shaped (calculated)
    Point midpoint; //a calculated midpoint that determines where the text should be drawn (calculated)

    

    /**
     * Nameless slice
     * 
     * @param magnitude magnitude of segment
     * @param color color
     */
    public Segment(double magnitude, Color color) {
        this.magnitude = magnitude;
        this.name = "";
        this.color = color;
    }

    
    public Segment(double magnitude, String name, Color color) {
        this.magnitude = magnitude;
        this.name = name;
        this.color = color;
    }

    
    public Segment(int level, Segment parent, double magnitude, String name, Color color) {
        this.level = level;
        this.parent = parent;
        this.magnitude = magnitude;
        this.name = name;
        this.color = color;
    }
    
    public Segment(int level, Segment parent, double magnitude, String name) {
        this.level = level;
        this.parent = parent;
        this.magnitude = magnitude;
        this.name = name;
	}



	public ArrayList<Segment> children = new ArrayList();

    double getRelativeMagnitude() {
        
        if (parent == null)
            return magnitude/100;
        else {
            
            double d = parent.getRelativeMagnitude() * magnitude/100;

            return d;
        }
    }
    
}
