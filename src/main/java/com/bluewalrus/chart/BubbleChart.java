package com.bluewalrus.chart;

import java.util.ArrayList;

import com.bluewalrus.bar.XAxis;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.bar.YAxis;
import com.bluewalrus.datapoint.DataPointWithMagnitude;

/**
 * Bubble chart. Magnitudes of all the elements need to be multiplied by a
 * factor.
 *
 * @author Oliver Watkins
 */
public class BubbleChart extends XYChart { 

    public BubbleChart(ArrayList<XYDataSeries> listOfSeries, YAxis yAxis,
            XAxis xAxis, double multipleMagnitudeBy) {

        super(listOfSeries, yAxis, xAxis);

        for (XYDataSeries xyDataSeries : listOfSeries) {

            ArrayList<DataPointWithMagnitude> al = xyDataSeries.dataPoints;

            for (DataPointWithMagnitude dp : al) {
                dp.magnitude = dp.magnitude * multipleMagnitudeBy;
            }
        }
    }

}
