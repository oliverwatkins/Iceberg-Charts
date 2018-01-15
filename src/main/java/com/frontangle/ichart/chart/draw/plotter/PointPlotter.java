package com.frontangle.ichart.chart.draw.plotter;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.concurrent.CopyOnWriteArrayList;

import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.point.UIPointXY;

public class PointPlotter extends AbstractPlotter {

	
	public static boolean drawPoints(Graphics2D g, XYChart chart, XYFactor xyFactor,
			XYDataSeries xYDataSeries, CopyOnWriteArrayList<DataPoint> dataPoints,
			int pixBtnFirst2Pts) {
		boolean firstRun = true;
		
		Point currentPoint = null;
		Point lastPoint = null;

		for (DataPoint dataPoint : dataPoints) {
			if (firstRun) {
				firstRun = false;
				currentPoint = drawPoint(g, xyFactor, xYDataSeries, dataPoint, chart,
						pixBtnFirst2Pts);
			} else {
				currentPoint = drawPoint(g, xyFactor, xYDataSeries, dataPoint, chart,
						pixBtnFirst2Pts);
			}
			lastPoint = currentPoint;
		}
		return firstRun;
	}
	
	
	private static Point drawPoint(Graphics2D g, XYFactor xyFactor, XYDataSeries xYDataSeries,
			DataPoint dataPoint, XYChart chart, int pixBtnFirst2Pts) {

		//get point in ui
		Point point = ChartPlotter.getPoint(xyFactor,dataPoint, chart); 
		
		ChartPlotter.checkForRunawayProcess(xyFactor, point.x, point.y);

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
			// TODO I do not know why the clipping is happening in the point. It
			// is probably better to clip here in this method
			// see draw line.
			dataPoint.uiPointXY.draw(g, point, dataPoint, xyFactor, chart, pixBtnFirst2Pts);

		}
		return  ChartPlotter.getPoint(xyFactor,dataPoint, chart); 
	}
	
	
	
}
