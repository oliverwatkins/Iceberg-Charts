package com.bluewalrus.chart;

import java.util.ArrayList;

import com.bluewalrus.bar.XYDataSeries;

/**
 * Same as LineChart. Add more later
 *
 * @author Oliver Watkins
 */
public class ScatterChart extends XYChart { // extends LineChart{

    public ScatterChart(ArrayList<XYDataSeries> listOfSeries, YAxis yAxis,
            XAxis xAxis) {
        super(listOfSeries, yAxis, xAxis);
    }

}
