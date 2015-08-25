/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.bar;

import java.io.Serializable;

/**
 * Describes a graphical interval along an axis. 
 * 
 * @author Oliver Watkins
 */
public class Interval implements Serializable{

    public Line graphLine;

    /**
     * length in pixels of the 'Tick'. 
     * Usually about 5 pixels hanging to the side of axis
     */
    
    public int lineLength; 
    
    
    /**
     * Spacing of interval. If increment = 10.0, then the tick will appear
     * at 0,10,20,30 etc.
     */
    public Double increment; 

    public Interval(int lineLength, Double increment) {
        this.lineLength = lineLength;
        this.increment = increment;
    }
    
    public Interval(int lineLength, Double increment, Line graphLine) {
        this.lineLength = lineLength;
        this.increment = increment;
        this.graphLine = graphLine;
    }
}
