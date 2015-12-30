package com.bluewalrus.datapoint;

import java.util.ArrayList;

public class DataPointMultiBar extends DataPoint {

    public ArrayList<DataPointBar> datapointBars;

    /**
     * Note y is arbitrary!!
     * @param x
     * @param y  -arbitrary
     */
    public DataPointMultiBar(double x, double y) {

        super(x, y);
    }

    public DataPointMultiBar(double x, double y, ArrayList<DataPointBar> datapointBars) {

        super(x, y);
        this.datapointBars = datapointBars;
    }

}
