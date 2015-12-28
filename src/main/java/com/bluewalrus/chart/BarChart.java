package com.bluewalrus.chart;

import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.point.UIPointBar;

/**
 * 
 * @author Oliver Watkins
 *
 */
public class BarChart extends XYChart {

    public BarChart(XAxis xAxis, YAxis yAxis, ArrayList<DataPointBar> bars) {
        this(xAxis, yAxis, bars, 10);
    }

    public BarChart(XAxis xAxis, YAxis yAxis, ArrayList<DataPointBar> bars, int barWidth) {
        this(xAxis, yAxis, bars, new UIPointBar(Color.PINK, Color.YELLOW, null, barWidth));
    }

    public BarChart(XAxis xAxis, YAxis yAxis, ArrayList<DataPointBar> bars, UIPointBar barPoint) {
        super(xAxis, yAxis);

        data = new ArrayList<XYDataSeries>();

        ArrayList<DataPointBar> dataPoints = new ArrayList<DataPointBar>();

        double xRange = (double) (this.xAxis.maxValue - this.xAxis.minValue);

//        space out
        //distance between points
        double pointDistance = (double) (xRange / (bars.size() + 1));

        validityCheck(bars);
        
        //massages the data.
        
        if (bars.get(0).xName != null) {
            int i = 1;
            for (DataPointBar bar : bars) {
                dataPoints.add(new DataPointBar((int) (pointDistance * i), (int) bar.y, bar.color, bar.xName));
                i++;
            }
        }else {
        	
        	dataPoints = bars;
        
        	//nothing
        }
        
        

        XYDataSeries<DataPointBar> series = new XYDataSeries<DataPointBar>(
                dataPoints,
                barPoint,
                null,
                "");

        data.add(series);
    }

	private void validityCheck(ArrayList<DataPointBar> bars) {
		
		DataPointBar firstElem = bars.get(0);
		if (firstElem.xName != null) {
			//enumerable
			for (DataPointBar dataPointBar : bars) {
				if (firstElem.xName == null) {
					throw new RuntimeException("Error : All data points need to be either enumarable or numerical. Some data points have an xName and others do not");
				}
			}
		}
		
		if (firstElem.xName == null) {
			//numerical
			for (DataPointBar dataPointBar : bars) {
				if (firstElem.xName != null) {
					throw new RuntimeException("Error : All data points need to be either enumarable or numerical. Some data points have an xName and others do not");
				}
			}
		}
	}




}