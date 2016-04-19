package com.bluewalrus.chart.draw.plotter;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.point.UIPointXY;
import com.bluewalrus.renderer.XYFactor;
import com.bluewalrus.scaling.TimeSeriesAxisScalingX;

/**
 * 
 * Line OR POINT renderer. Sort of a utilities class.
 * 
 * @author Oliver Watkins
 *
 */
public class NumericalPlotter extends AbstractPlotter {

	protected double getYZeroOffsetInPixel(XYChart chart, YAxis yAxis) {
    	double yMax = yAxis.axisDraw.getMaxValue();
    	double yMin = yAxis.axisDraw.getMinValue();
    	
    	return (double) ((-yMin / (yMax - yMin)) * chart.heightChart);
	}

	protected double getXZeroOffsetInPixel(XYChart chart, XAxis xAxis) {

    	double xMax = xAxis.axisDraw.getMaxValue();
    	double xMin = xAxis.axisDraw.getMinValue();
    	
    	double diffX = xMax - xMin;
		return (double) ((-xMin / (double) diffX) * chart.widthChart);
	}

	protected XYFactor getXYFactor(XYChart chart, XAxis xAxis, YAxis yAxis) {
		
    	double xMax = xAxis.axisDraw.getMaxValue();
    	double xMin = xAxis.axisDraw.getMinValue();
    	
    	double yMax = yAxis.axisDraw.getMaxValue();
    	double yMin = yAxis.axisDraw.getMinValue();
    	
    	double diffX = xMax - xMin;
    	double diffY = yMax - yMin;

        double xFactor = ((double) chart.widthChart / diffX);
        double yfactor = ((double) chart.heightChart / diffY);

        return new XYFactor(xFactor, yfactor);
	}
}
