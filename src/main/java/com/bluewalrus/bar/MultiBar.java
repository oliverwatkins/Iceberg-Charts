/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.bar;

import java.util.ArrayList;

public class MultiBar {
    
	public MultiBarMode mode;
	
    public String name;
    public ArrayList<Bar> bars;
    
    public MultiBar(ArrayList<Bar> bars, String name, MultiBarMode mode) {
        this.bars = bars;
        this.name = name;
        this.mode = mode;
    }
    
    public enum MultiBarMode {
    	STACK_ON_TOP, SIDE_BY_SIDE
    }
}


