package com.bluewalrus.chart.draw.plotter;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.XYFactor;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointXY;
import com.bluewalrus.scaling.TimeSeriesAxisScalingX;
import com.bluewalrus.scaling.TimeSeriesAxisScalingY;

/**
 * Plots chart data
 * 
 * @author Oliver Watkins
 *
 */
public class ChartPlotter {

	protected Point lastPoint;
	
	/**
	 * Entry point
	 * @param g
	 * @param chart
	 * @param yAxis
	 * @param xAxis
	 * @param xYDataSerieses
	 */
	public void plotData(Graphics2D g, XYChart chart, YAxis yAxis, XAxis xAxis,
            ArrayList<? extends XYDataSeries> xYDataSerieses) {
        
        XYFactor xyFactor = getXYFactor(chart, xAxis, yAxis);
        
        xyFactor.xZeroOffsetInPixel = getXZeroOffsetInPixel(chart, xAxis);
		xyFactor.yZeroOffsetInPixel = getYZeroOffsetInPixel(chart, yAxis);

        int xShift = chart.leftOffset;
        int yShift = chart.topOffset + chart.heightChart;

        DataPoint lastPoint = null; 

        for (XYDataSeries xYDataSeries : xYDataSerieses) {

            ArrayList<DataPoint> dataPoints = xYDataSeries.dataPoints;

            boolean firstRun = true;
            
            //only bar
            int pixBtnFirst2Pts = calculateDistanceBetweenFirstTwoPoints(dataPoints.get(0), dataPoints.get(1), xShift, xyFactor);

            for (DataPoint dataPoint : dataPoints) {
                if (firstRun) {
                    firstRun = false;
                    lastPoint = dataPoint;

                    if (xYDataSeries.pointType != null) {

                        drawPoint(g, xyFactor, xShift, yShift,
                                xYDataSeries, dataPoint, chart, pixBtnFirst2Pts);
                    }

                } else {

                    if (xYDataSeries.line != null) {

                        drawLine(g, xyFactor, xShift, yShift,
                                lastPoint, xYDataSeries, dataPoint);
                    }
                    if (xYDataSeries.pointType != null) {

                        drawPoint(g, xyFactor, xShift, yShift,
                                xYDataSeries, dataPoint, chart, pixBtnFirst2Pts);
                    }
                    lastPoint = dataPoint;
                }
            }
        }
    }
	
	protected void drawPoint(Graphics2D g, 
    		XYFactor xyFactor,
            int xShift,
            int yShift,
            XYDataSeries xYDataSeries,
            DataPoint dataPoint, 
            XYChart chart, int pixBtnFirst2Pts) {
		
        int x = (int) ((dataPoint.x * xyFactor.xFactor) + xShift + xyFactor.xZeroOffsetInPixel);
        int y = (int) (yShift - (int) (dataPoint.y * xyFactor.yFactor) - xyFactor.yZeroOffsetInPixel);

        //hack TODO 
        if (xyFactor.yFactor * y > 200000) {
        	System.err.println("ERROR!!! xyFactor.yFactor * y > 200000");
            return;
        }
        if (xyFactor.xFactor * x > 200000) {
        	System.err.println("ERROR!!! xyFactor.xFactor * x > 200000");
            return;
        }
        
        if (dataPoint.uiPointXY == null) {
            UIPointXY pointType = xYDataSeries.pointType;
            
            UIPointXY xyInstance = null;
            try {
            	xyInstance = pointType.createNewInstanceOfSelf();
    		} catch (CloneNotSupportedException e) {
    			e.printStackTrace();
    		}
            dataPoint.setPoinUI(xyInstance);
        }
        dataPoint.uiPointXY.draw(g, new Point(x, y), lastPoint, dataPoint, xyFactor, chart, pixBtnFirst2Pts);
        
        lastPoint = new Point(x,y);
    }
	
	protected void drawLine(Graphics2D g, XYFactor xyFactor,
            int xShift, int yShift, DataPoint lastPoint,
            XYDataSeries xYDataSeries, DataPoint dataPoint) {

        Line line = xYDataSeries.line;

        int adjustedX1 = (int) ((lastPoint.x * xyFactor.xFactor) + xShift + xyFactor.xZeroOffsetInPixel);
        int adjustedY1 = (int) (yShift - (int) (lastPoint.y * xyFactor.yFactor) - xyFactor.yZeroOffsetInPixel);

        int adjustedX2 = (int) ((dataPoint.x * xyFactor.xFactor) + xShift + xyFactor.xZeroOffsetInPixel);
        int adjustedY2 = (int) (yShift - (int) (dataPoint.y * xyFactor.yFactor) - xyFactor.yZeroOffsetInPixel);

        //hack
        if (xyFactor.yFactor * adjustedY2 > 200000) {
            return;
        }
        if (xyFactor.xFactor * adjustedX2 > 200000) {
            return;
        }

        line.drawLine(g, adjustedX1, adjustedY1, adjustedX2, adjustedY2);
    }
	
    
    protected int calculateDistanceBetweenFirstTwoPoints(DataPoint dataPoint,
			DataPoint dataPoint2, int xShift, XYFactor xyFactor) {
    	
    	
    	int x = (int) ((dataPoint.x * xyFactor.xFactor) + xShift + xyFactor.xZeroOffsetInPixel);
    	int x2 = (int) ((dataPoint2.x * xyFactor.xFactor) + xShift + xyFactor.xZeroOffsetInPixel);
    	
		return (x2 - x);
	}


	protected XYFactor getXYFactor(XYChart chart, XAxis xAxis, YAxis yAxis) {
		
    	double yMax = yAxis.axisScaling.getMaxValue();
    	double yMin = yAxis.axisScaling.getMinValue();
    	
    	double xFactor = -1;
    	
		if (xAxis.axisScaling instanceof TimeSeriesAxisScalingX) {
	    	long xMax = ((TimeSeriesAxisScalingX)xAxis.axisScaling).dateEnd.getTime();
	    	long xMin = ((TimeSeriesAxisScalingX)xAxis.axisScaling).dateStart.getTime();
	    	
	    	double diffX = xMax - xMin;
	    	
	    	xFactor = ((double) chart.widthChart / diffX);
	    	
		}else {
	    	double xMax = xAxis.axisScaling.getMaxValue();
	    	double xMin = xAxis.axisScaling.getMinValue();
	    	
	    	double diffX = xMax - xMin;
	    	
	    	xFactor = ((double) chart.widthChart / diffX);
		}

    	double diffY = yMax - yMin;

        double yfactor = ((double) chart.heightChart / diffY);

        return new XYFactor(xFactor, yfactor);
	}



	
	protected double getYZeroOffsetInPixel(XYChart chart, YAxis yAxis) {
		
		if (yAxis.axisScaling instanceof TimeSeriesAxisScalingY) {
			throw new RuntimeException("TODO");

			
		}else {
	    	double yMax = yAxis.axisScaling.getMaxValue();
	    	double yMin = yAxis.axisScaling.getMinValue();
	    	
	    	return (double) ((-yMin / (yMax - yMin)) * chart.heightChart);
		}
	}

	
	/**
	 * 
	 * @param chart
	 * @param xAxis
	 * @return
	 */

	protected double getXZeroOffsetInPixel(XYChart chart, XAxis xAxis) {
		
		if (xAxis.axisScaling instanceof TimeSeriesAxisScalingX) {
	    	long xMax = ((TimeSeriesAxisScalingX)xAxis.axisScaling).dateEnd.getTime();
	    	long xMin = ((TimeSeriesAxisScalingX)xAxis.axisScaling).dateStart.getTime();
	    	
	    	double diffX = xMax - xMin;
	    	
			return (double) ((-xMin / (double) diffX) * chart.widthChart);
	    	
		}else {

	    	double xMax = xAxis.axisScaling.getMaxValue();
	    	double xMin = xAxis.axisScaling.getMinValue();
	    	
	    	double diffX = xMax - xMin;
			return (double) ((-xMin / (double) diffX) * chart.widthChart);
		}
	}
    
}
