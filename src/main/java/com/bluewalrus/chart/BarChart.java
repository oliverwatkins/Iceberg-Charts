package com.bluewalrus.chart;

import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.draw.EnumerationAxisDrawX;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.point.UIPointBar;

/**
 * 
 * @author Oliver Watkins
 *
 */
public class BarChart extends XYChart {

	
    public BarChart(XAxis xAxis, YAxis yAxis, ArrayList<DataPointBar> listOfSeries) {
        this(xAxis, yAxis, listOfSeries, 10);
    }

    public BarChart(XAxis xAxis, YAxis yAxis, ArrayList<DataPointBar> dataPointList, int barWidth) {
        this(xAxis, yAxis, dataPointList, new UIPointBar(Color.PINK, Color.YELLOW, null, barWidth));
    }

    public BarChart(XAxis xAxis, YAxis yAxis, ArrayList<DataPointBar> dataPointList, UIPointBar barPoint) {
        super(xAxis, yAxis);

        
    	double xMax = xAxis.axisDraw.maxValue;
    	double xMin = xAxis.axisDraw.minValue;
    	
        
        XYDataSeries<DataPointBar> d = new XYDataSeries<DataPointBar>("");
        d.dataPoints = dataPointList;
        
		this.data.add(d);

		//get the first set for now
		ArrayList<DataPointBar> dataPoints = dataPointList;
		
		
        /**
         * Special case for enumeration :
         * 
         * Massage data
         */
        if (xAxis.axisDraw instanceof EnumerationAxisDrawX){
		
			ArrayList<DataPointBar> bars = dataPointList;

            double xRange = (double) (xMax - xMin);

            //distance between points (bars)
            double pointDistance = (double) (xRange / (bars.size() + 1));

            validityCheck(bars);
        	
        	dataPoints = new ArrayList<DataPointBar>();
        	
            int i = 1;
            for (DataPointBar bar : bars) {
                dataPoints.add(new DataPointBar((int) (pointDistance * i), (int) bar.y, bar.color, bar.name));
                i++;
            }
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
		if (firstElem.name != null) {
			//enumerable
			for (DataPointBar dataPointBar : bars) {
				if (firstElem.name == null) {
					throw new RuntimeException("Error : All data points need to be either enumarable or numerical. Some data points have an xName and others do not");
				}
			}
		}
		
		if (firstElem.name == null) {
			//numerical
			for (DataPointBar dataPointBar : bars) {
				if (firstElem.name != null) {
					throw new RuntimeException("Error : All data points need to be either enumarable or numerical. Some data points have an xName and others do not");
				}
			}
		}
	}




}