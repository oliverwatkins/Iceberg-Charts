package com.bluewalrus.datapoint;

import java.util.ArrayList;

import com.bluewalrus.bar.Bar;


/**
 * A set of bars.
 * 
 * @author Oliver Watkins
 */
public class MultiBar {

    public MultiBarMode mode;

    public String name;
    public ArrayList<DataPointBar> bars;

    public MultiBar(ArrayList<DataPointBar> bars, String name, MultiBarMode mode) {
        this.bars = bars;
        this.name = name;
        this.mode = mode;
    }

    public enum MultiBarMode {
        STACK_ON_TOP, SIDE_BY_SIDE
    }
}
