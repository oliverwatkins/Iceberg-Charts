package com.bluewalrus.chart.plotter;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.renderer.XYFactor;

public abstract class AbstractPlotter {

    protected abstract void drawLinesOrPoints(Graphics2D g, XYChart chart, YAxis yAxis, XAxis xAxis,
            ArrayList<? extends XYDataSeries> xYDataSerieses);

    
    protected abstract void drawLine(Graphics2D g, XYFactor xyFactor,
            int xShift, int yShift, DataPoint lastPoint,
            XYDataSeries xYDataSeries, DataPoint dataPoint);
    
    
    protected abstract void drawPoint(Graphics2D g, 
    		XYFactor xyFactor,
            int xShift,
            int yShift,
            XYDataSeries xYDataSeries,
            DataPoint dataPoint, 
            XYChart chart);
            
    
}
