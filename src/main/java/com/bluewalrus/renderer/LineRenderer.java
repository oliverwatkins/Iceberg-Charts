package com.bluewalrus.renderer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.point.UIPointXY;

/**
 * 
 * Line OR POINT renderer. Sort of a utilities class.
 * 
 * TODO refactor. this should belong in the chart class.
 *
 * @author Oliver Watkins
 *
 */
public class LineRenderer {

	

//    public static void drawLinesOrPointsXXY(Graphics2D g, Chart chart,
//            YAxis yAxis, XAxis xAxis, XYDataSeries<DataPointBar> barSeries) {
//
//        ArrayList al = new ArrayList<>();
//        al.add(barSeries);
//
//        drawLinesOrPoints(g, chart, yAxis, xAxis, al);
//
//    }
//    
    
    public static void drawLinesOrPoints(Graphics2D g, Chart chart, YAxis yAxis, XAxis xAxis,
            ArrayList<? extends XYDataSeries> xYDataSerieses) {

        double xFactor = ((double) chart.widthChart / (double) (xAxis.maxValue - xAxis.minValue));
        double yfactor = ((double) chart.heightChart / (double) (yAxis.maxValue - yAxis.minValue));

        XYFactor xyFactor = new XYFactor(xFactor, yfactor);
        xyFactor.xZeroOffsetInPixel = (double) ((-xAxis.minValue / (xAxis.maxValue - xAxis.minValue)) * chart.widthChart);
        xyFactor.yZeroOffsetInPixel = (double) ((-yAxis.minValue / (yAxis.maxValue - yAxis.minValue)) * chart.heightChart);

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

                        drawPoint(g, xyFactor, xShift, yShift,
                                xYDataSeries, dataPoint, chart);
                    }

                } else {

                    if (xYDataSeries.line != null) {

                        drawLine(g, xyFactor, xShift, yShift,
                                lastPoint, xYDataSeries, dataPoint);
                    }
                    if (xYDataSeries.pointType != null) {

                        drawPoint(g, xyFactor, xShift, yShift,
                                xYDataSeries, dataPoint, chart);
                    }
                    lastPoint = dataPoint;
                }
            }
        }
    }

    private static void drawPoint(Graphics2D g, 
    		XYFactor xyFactor,
            int xShift,
            int yShift,
            XYDataSeries xYDataSeries,
            DataPoint dataPoint, 
            Chart chart) {

        int x = (int) ((dataPoint.x * xyFactor.xFactor) + xShift + xyFactor.xZeroOffsetInPixel);
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

    private static void drawLine(Graphics2D g, XYFactor xyFactor,
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


}
