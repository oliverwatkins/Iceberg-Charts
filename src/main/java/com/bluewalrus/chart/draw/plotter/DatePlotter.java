package com.bluewalrus.chart.draw.plotter;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.point.UIPointXY;
import com.bluewalrus.renderer.XYFactor;
import com.bluewalrus.scaling.TimeSeriesAxisScalingX;

public class DatePlotter extends AbstractPlotter {

	private Point lastPoint;
	
	protected XYFactor getXYFactor(XYChart chart, XAxis xAxis, YAxis yAxis) {
		
    	long xMax = ((TimeSeriesAxisScalingX)xAxis.axisDraw).dateEnd.getTime();
    	long xMin = ((TimeSeriesAxisScalingX)xAxis.axisDraw).dateStart.getTime();
    	
    	double yMax = yAxis.axisDraw.getMaxValue();
    	double yMin = yAxis.axisDraw.getMinValue();
    	
    	double diffX = xMax - xMin;
    	double diffY = yMax - yMin;

        double xFactor = ((double) chart.widthChart / diffX);
        double yfactor = ((double) chart.heightChart / diffY);

        return new XYFactor(xFactor, yfactor);
	}
	
	
	protected double getYZeroOffsetInPixel(XYChart chart, YAxis yAxis) {
    	double yMax = yAxis.axisDraw.getMaxValue();
    	double yMin = yAxis.axisDraw.getMinValue();
    	
    	return (double) ((-yMin / (yMax - yMin)) * chart.heightChart);
	}


	protected double getXZeroOffsetInPixel(XYChart chart, XAxis xAxis) {
		
    	long xMax = ((TimeSeriesAxisScalingX)xAxis.axisDraw).dateEnd.getTime();
    	long xMin = ((TimeSeriesAxisScalingX)xAxis.axisDraw).dateStart.getTime();
    	
    	double diffX = xMax - xMin;
		return (double) ((-xMin / (double) diffX) * chart.widthChart);
	}
}
