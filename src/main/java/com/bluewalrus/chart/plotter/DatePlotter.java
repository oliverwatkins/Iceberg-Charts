package com.bluewalrus.chart.plotter;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.draw.TimeSeriesAxisDrawX;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.point.UIPointXY;
import com.bluewalrus.renderer.XYFactor;

public class DatePlotter {

	
    public static void drawLinesOrPoints(Graphics2D g, XYChart chart, YAxis yAxis, XAxis xAxis,
            ArrayList<? extends XYDataSeries> xYDataSerieses) {
    	
    	
    	
    	long xMax = ((TimeSeriesAxisDrawX)xAxis.axisDraw).dateEnd.getTime();
    	long xMin = ((TimeSeriesAxisDrawX)xAxis.axisDraw).dateStart.getTime();
    	
    	double yMax = yAxis.axisDraw.maxValue;
    	double yMin = yAxis.axisDraw.minValue;
    	

        double xFactor = ((double) chart.widthChart / (double) (xMax - xMin));
        double yfactor = ((double) chart.heightChart / (double) (yMax - yMin));

        XYFactor xyFactor = new XYFactor(xFactor, yfactor);
        xyFactor.xZeroOffsetInPixel = (double) ((-xMin / (xMax - xMin)) * chart.widthChart);
        xyFactor.yZeroOffsetInPixel = (double) ((-yMin / (yMax - yMin)) * chart.heightChart);

        int xShift = chart.leftOffset;
        int yShift = chart.topOffset + chart.heightChart;

        DataPoint lastPoint = null; 

        for (XYDataSeries xYDataSeries : xYDataSerieses) {

            ArrayList<DataPoint> dataPoints = xYDataSeries.dataPoints;

            boolean firstRun = true;

            for (DataPoint dataPoint : dataPoints) {
                if (firstRun) {
                    firstRun = false;
                    lastPoint = dataPoint;

                    if (xYDataSeries.pointType != null) {

                        drawPoint_Date(g, xyFactor, xShift, yShift,
                                xYDataSeries, dataPoint, chart);
                    }

                } else {

                    if (xYDataSeries.line != null) {

                    	LineRenderer.drawLine(g, xyFactor, xShift, yShift,
                                lastPoint, xYDataSeries, dataPoint);
                    }
                    if (xYDataSeries.pointType != null) {

                    	LineRenderer.drawPoint(g, xyFactor, xShift, yShift,
                                xYDataSeries, dataPoint, chart);
                    }
                    lastPoint = dataPoint;
                }
            }
        }
        
        
    	
    }
    
    
    static void drawPoint_Date(Graphics2D g, 
    		XYFactor xyFactor,
            int xShift,
            int yShift,
            XYDataSeries xYDataSeries,
            DataPoint dataPoint, 
            XYChart chart) {

        int x = (int) ((dataPoint.xDate.getTime() * xyFactor.xFactor) + xShift + xyFactor.xZeroOffsetInPixel);
        int y = (int) (yShift - (int) (dataPoint.y * xyFactor.yFactor) - xyFactor.yZeroOffsetInPixel);

        //hack TODO 
        if (xyFactor.yFactor * y > 200000) {
            return;
        }
        if (xyFactor.xFactor * x > 200000) {
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
        dataPoint.uiPointXY.draw(g, new Point(x, y), dataPoint, xyFactor);
    }
}
