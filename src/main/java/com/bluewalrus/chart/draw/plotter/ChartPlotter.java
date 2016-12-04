package com.bluewalrus.chart.draw.plotter;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.bluewalrus.chart.ChartUtils;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.XYFactor;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointXY;
import com.bluewalrus.scaling.LogarithmicAxisScaling;
import com.bluewalrus.scaling.TimeSeriesAxisScaling;

/**
 * Plots chart data
 * 
 * @author Oliver Watkins
 *
 */
public class ChartPlotter {

	protected Point lastPoint;
	private XYChart chart;
	
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
		
		this.chart = chart;
        
        XYFactor xyFactor = getXYFactor(chart, xAxis, yAxis);
        
        xyFactor.xZeroOffsetInPixel = getXZeroOffsetInPixel(chart, xAxis);
		xyFactor.yZeroOffsetInPixel = getYZeroOffsetInPixel(chart, yAxis);

        int xShift = chart.leftOffset; //silly
        int yShift = chart.topOffset + chart.heightChart;

        for (XYDataSeries xYDataSeries : xYDataSerieses) {

//        	TODO replace with Nicks changes : CopyOnWriteArrayList
        	
        	ArrayList <DataPoint> dataPoints = xYDataSeries.dataPoints;

            if (dataPoints.size() == 0){
            	continue;
            }
            
            boolean firstRun = true;

            //TODO this will crash if only ONE point is used in a chart!!
            //only for bar
            int pixBtnFirst2Pts = calculateDistanceBetweenFirstTwoPoints(dataPoints.get(0), dataPoints.get(1), xShift, xyFactor);

            Point currentPoint = null;
            Point lastPoint = null;
            		
            for (DataPoint dataPoint : dataPoints) {
                if (firstRun) {
                	
                    firstRun = false;

//                    if (xYDataSeries.pointType != null) {
                    	currentPoint = drawPoint(g, xyFactor, xYDataSeries, dataPoint, chart, pixBtnFirst2Pts);
//                    }
                } else {

//                    if (xYDataSeries.pointType != null) {
                    	currentPoint = drawPoint(g, xyFactor, xYDataSeries, dataPoint, chart, pixBtnFirst2Pts);
//                    }
                    if (xYDataSeries.line != null) {
                        drawLine(g, lastPoint, currentPoint, xYDataSeries);
                    }
                }
                lastPoint = currentPoint;
            }
        }
    }
	


	protected Point drawPoint(Graphics2D g, 
    		XYFactor xyFactor,
            XYDataSeries xYDataSeries,
            DataPoint dataPoint, 
            XYChart chart, int pixBtnFirst2Pts) {
		
		int yShift = chart.topOffset + chart.heightChart;
		int xShift = chart.leftOffset;
		
		int x = 0;
		
		if (chart.xAxis.axisScaling instanceof TimeSeriesAxisScaling) {
			x = (int) ((dataPoint.xDate.getTime() * xyFactor.getxFactor()) + xShift + xyFactor.xZeroOffsetInPixel);
	        
		} else if (chart.xAxis.axisScaling instanceof LogarithmicAxisScaling) {
			double adjustedValue = convertLogValue(dataPoint, chart);

			x = (int)( xShift + (adjustedValue*chart.widthChart));
		}else {
	        x = (int) ((dataPoint.x * xyFactor.getxFactor()) + xShift + xyFactor.xZeroOffsetInPixel);
		}
		
        int y = (int) (yShift - (int) (dataPoint.y * xyFactor.getyFactor()) - xyFactor.yZeroOffsetInPixel);

        //hack TODO 
        if (xyFactor.getyFactor() * y > 200000) {
        	System.err.println("ERROR!!! xyFactor.yFactor * y > 200000");
        	System.err.println("Computer is probably going to crash now?");
        }
        if (xyFactor.getxFactor() * x > 200000) {
        	System.err.println("ERROR!!! xyFactor.xFactor * x > 200000....  xyFactor.xFactor - " + xyFactor.getxFactor() + " x - " + x);
        	System.err.println("Computer is probably going to crash now?");
        }

        UIPointXY pointType = xYDataSeries.pointType;

        if (dataPoint.uiPointXY == null && pointType != null) {
        	
            
            UIPointXY xyInstance = null;
            try {
            	xyInstance = pointType.createNewInstanceOfSelf();
    		} catch (CloneNotSupportedException e) {
    			e.printStackTrace();
    		}
            dataPoint.setPoinUI(xyInstance);
        }
        
        
        if (xYDataSeries.pointType != null) {
            //TODO I do not know why the clipping is happening in the point. It is probably better to clip here in this method
            //see draw line.
            dataPoint.uiPointXY.draw(g, new Point(x, y), lastPoint, dataPoint, xyFactor, chart, pixBtnFirst2Pts);
        	
        }

        	
        
        lastPoint = new Point(x,y);
        return lastPoint;
    }

	
	private void drawLine(Graphics2D g, Point lastPoint2, Point currentPoint, XYDataSeries xYDataSeries) {
        Line line = xYDataSeries.line;
		Shape cachedClip = ChartUtils.clipChart(g, chart);

		
		if (lastPoint2 != null && currentPoint != null) {
			//clip away everything that is not in the chart.
			line.drawLine(g, lastPoint2.x, lastPoint2.y, currentPoint.x, currentPoint.y);
		}
        
        g.setClip(cachedClip);
	}
	
	private double convertLogValue(DataPoint dataPoint, XYChart chart) {
		double logMax = Math.log10(chart.xAxis.getMaxValue());
		double logMin = Math.log10(chart.xAxis.getMinValue());
		
		double logDP = Math.log10(dataPoint.x);
		
		double v = (logDP - logMin)/(logMax - logMin);
		return v;
	}
	
	
    protected int calculateDistanceBetweenFirstTwoPoints(DataPoint dataPoint,
			DataPoint dataPoint2, int xShift, XYFactor xyFactor) {
    	
    	int x = (int) ((dataPoint.x * xyFactor.getxFactor()) + xShift + xyFactor.xZeroOffsetInPixel);
    	int x2 = (int) ((dataPoint2.x * xyFactor.getxFactor()) + xShift + xyFactor.xZeroOffsetInPixel);
    	
		return (x2 - x);
	}


	protected XYFactor getXYFactor(XYChart chart, XAxis xAxis, YAxis yAxis) {
		
		if (chart.heightChart < 0)
			throw new RuntimeException("Chart cannot have negative height");
		if (chart.widthChart < 0)
			throw new RuntimeException("Chart cannot have negative width");
		
    	double yMax = yAxis.axisScaling.getMaxValue();
    	double yMin = yAxis.axisScaling.getMinValue();
    	
    	double xFactor = -1;
    	
		if (xAxis.axisScaling instanceof TimeSeriesAxisScaling) {
	    	long xMax = ((TimeSeriesAxisScaling)xAxis.axisScaling).dateEnd.getTime();
	    	long xMin = ((TimeSeriesAxisScaling)xAxis.axisScaling).dateStart.getTime();
	    	
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

	 /**
	  * TODO this method is completely wrong
	  * @param chart
	  * @param yAxis
	  * @return
	  */
	protected double getYZeroOffsetInPixel(XYChart chart, YAxis yAxis) {
		
    	double yMax = yAxis.axisScaling.getMaxValue();
    	double yMin = yAxis.axisScaling.getMinValue();
    	
    	return (double) ((-yMin / (yMax - yMin)) * chart.heightChart);
	}

	
	/**
	 * I 
	 * DO
	 * NOT 
	 * UNDERSTAND
	 * THIS METHOD
	 * 
	 * TODO this method is completely wrong
	 * 
	 * Negative value : 
	 * 
	 * @param chart
	 * @param xAxis
	 * @return
	 */

	protected double getXZeroOffsetInPixel(XYChart chart, XAxis xAxis) {
		
		double offset = -1;
		
		if (xAxis.axisScaling instanceof TimeSeriesAxisScaling) {
	    	long xMax = ((TimeSeriesAxisScaling)xAxis.axisScaling).dateEnd.getTime();
	    	long xMin = ((TimeSeriesAxisScaling)xAxis.axisScaling).dateStart.getTime();
	    	
	    	long diffX = xMax - xMin;
	    	
	    	double v = (-xMin / (double) diffX);
	    	
	    	offset =  (double) (v * chart.widthChart);
	    	
		}else {

	    	double xMax = xAxis.axisScaling.getMaxValue();
	    	double xMin = xAxis.axisScaling.getMinValue();
	    	
	    	double diffX = xMax - xMin;
	    	
	    	double v = (double) ((-xMin / (double) diffX) * chart.widthChart);
	    	
	    	offset =   v;
		}
		
		return offset;
	}
    
}
