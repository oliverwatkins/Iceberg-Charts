package com.bluewalrus.chart.draw;

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


    
    public static void drawLinesOrPoints(Graphics2D g, XYChart chart, YAxis yAxis, XAxis xAxis,
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
            XYChart chart) {

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
        
        
        if (dataPoint instanceof DataPointBar) {
            drawXLabel(g, chart, (DataPointBar)dataPoint, new Point(x, y));
        }
        
        
    }
    
    private static void drawXLabel(Graphics2D g, XYChart chart, DataPointBar dp, Point point) {
        //TODO bar refactor : remove this out of here! This class is not responsible for drawing the axis labels. 
        
//      System.out.println("1 xName " + dp.xName);
      if (dp.xName != null) {
//      	System.out.println("2 xName " + dp.xName);
    	  LineRenderer.drawText(chart, dp.xName, g, point.x);
      }else {
    	  LineRenderer.drawText(chart, "" + dp.x, g, point.x);
      }

      LineRenderer.drawTickLine(g, chart, point.x);
		
	}
    
    protected static void drawText(XYChart chart, String name,
            Graphics g, int x) {

        FontMetrics fm = chart.getFontMetrics(chart.xAxis.axisCatFont);
        int widthStr = fm.stringWidth(name);
        int heightStr = fm.getHeight();

        g.setFont(chart.xAxis.axisCatFont);
        g.setColor(Color.BLACK);

        int xPosition = x - (widthStr / 2);
        int yPosition = chart.topOffset + chart.xAxis.marginOffset + chart.heightChart + chart.xAxis.labelOffset - heightStr / 2;

        //draw tick
        g.drawString(name, xPosition, yPosition);
    }

    @Deprecated //this will be done by the axis, not this point. TODO bar refactor
    protected static void drawTickLine(Graphics g, XYChart chart, int x) {
        g.drawLine((int) (x),
                (int) (chart.topOffset + chart.xAxis.marginOffset + chart.heightChart),
                (int) (x),
                (int) (chart.topOffset + chart.xAxis.marginOffset + chart.heightChart + 2));
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
