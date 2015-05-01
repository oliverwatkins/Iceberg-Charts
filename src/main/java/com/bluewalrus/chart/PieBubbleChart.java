package com.bluewalrus.chart;

import java.util.ArrayList;

import com.bluewalrus.bar.XAxis;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.bar.YAxis;
import com.bluewalrus.datapoint.DataPointPieChart;

/**
 * Shows pies as bubbles in a bubble chart.
 * 
 * Magnitude needs to be multiplied by a factor
 * 
 * 
 * @author Oliver Watkins
 */
public class PieBubbleChart extends XYChart {

    public PieBubbleChart(ArrayList<XYDataSeries> listOfSeries, YAxis yAxis,
            XAxis xAxis, double multipleMagnitudeBy) {

        super(listOfSeries, yAxis, xAxis);

        for (XYDataSeries xyDataSeries : listOfSeries) {

            ArrayList<DataPointPieChart> al = xyDataSeries.dataPoints;

            for (DataPointPieChart dp : al) {

                System.out.println("dp.magnitude before " + dp.magnitude);
                dp.magnitude = dp.magnitude * multipleMagnitudeBy;
                System.out.println("dp.magnitude after " + dp.magnitude);
            }
        }
    }

}
