package com.bluewalrus.chart;

import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.bar.Bar;
import com.bluewalrus.bar.MultiBar;
import com.bluewalrus.bar.XAxis;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.bar.YAxis;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.point.BarPoint;

public class BarChart extends XYChart{ 

	public BarChart(XAxis xAxis, YAxis yAxis, ArrayList<Bar> bars) {
		this(xAxis,yAxis, bars, 10);
	}

	public BarChart(XAxis xAxis, YAxis yAxis, ArrayList<Bar> bars, int barWidth) {
		this(xAxis, yAxis, bars, new BarPoint(Color.PINK, Color.YELLOW, null, barWidth));
	}
	
	public BarChart(XAxis xAxis, YAxis yAxis, ArrayList<Bar> bars, BarPoint barPoint) {
		super(xAxis, yAxis);
		barPoint.chart = this;
		
		data = new ArrayList<XYDataSeries>();
		
        ArrayList<DataPointBar> dataPoints = new ArrayList<DataPointBar>();
		
        double xRange = (double)(this.xAxis.maxValue - this.xAxis.minValue);
        
//        space out
        //distance between points
        double pointDistance = (double) ( xRange / (bars.size() + 1));
        
        int i=1;
		for (Bar bar : bars) {
	        dataPoints.add(new DataPointBar((int)(pointDistance*i), (int)bar.value, bar.color, bar.name));
	        i++;
		}
		
        XYDataSeries<DataPointBar> series = new XYDataSeries<DataPointBar>(
        		dataPoints, 
        		barPoint,
        		null,
        		"");
        
        data.add(series);
	}
}
