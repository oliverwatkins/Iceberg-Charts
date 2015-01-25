package com.bluewalrus.chart;


import java.util.ArrayList;

import com.bluewalrus.bar.XAxis;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.bar.YAxis;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointWithMagnitude;
import com.bluewalrus.point.Bubble;



/**
 * Same as LineChart. Add more later
 * 
 * @author Oliver Watkins
 */

public class BubbleChart extends XYChart{ // LineChart{

	public BubbleChart(ArrayList<XYDataSeries> listOfSeries, YAxis yAxis,
			XAxis xAxis, double multipleMagnitudeBy) {
		super(listOfSeries, yAxis, xAxis);
		
		
		for (XYDataSeries xyDataSeries : listOfSeries) {
			
			ArrayList<DataPointWithMagnitude> al = xyDataSeries.dataPoints;
			
			for (DataPointWithMagnitude dp : al) {
				
				System.out.println("dp.magnitude before " + dp.magnitude);
				dp.magnitude = dp.magnitude*multipleMagnitudeBy;
				System.out.println("dp.magnitude after " + dp.magnitude);
			}
		}
	}

}