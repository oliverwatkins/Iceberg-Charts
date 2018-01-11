package com.frontangle.ichart.chart.draw.plotter;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.util.concurrent.CopyOnWriteArrayList;

import com.frontangle.ichart.chart.ChartUtils;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Line;

public class LinePlotter extends AbstractPlotter {

	public static boolean drawLines(Graphics2D g, XYChart chart, XYFactor xyFactor,
			XYDataSeries xYDataSeries, CopyOnWriteArrayList<DataPoint> dataPoints) {
		
		boolean firstRun;
		Point currentPoint;
		Point lastPoint;
		firstRun = true;
		currentPoint = null;
		lastPoint = null;

		for (DataPoint dataPoint : dataPoints) {
			if (firstRun) {

				firstRun = false;
				currentPoint = ChartPlotter.getPoint(g, xyFactor, xYDataSeries, dataPoint, chart);
			} else {
				currentPoint = ChartPlotter.getPoint(g, xyFactor, xYDataSeries, dataPoint, chart);
				if (xYDataSeries.line != null) {
					LinePlotter.drawLine(g, lastPoint, currentPoint, xYDataSeries, chart);
				}
			}
			lastPoint = currentPoint;
		}
		return firstRun;
	}
	
	private static void drawLine(Graphics2D g, Point lastPoint2, Point currentPoint,
			XYDataSeries xYDataSeries, XYChart chart) {
		Line line = xYDataSeries.line;
		Shape cachedClip = ChartUtils.clipChart(g, chart);

		if (lastPoint2 != null && currentPoint != null) {
			// clip away everything that is not in the chart.
			line.drawLine(g, lastPoint2.x, lastPoint2.y, currentPoint.x, currentPoint.y);
		}

		g.setClip(cachedClip);
	}

}
