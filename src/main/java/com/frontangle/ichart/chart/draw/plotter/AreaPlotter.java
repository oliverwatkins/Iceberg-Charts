package com.frontangle.ichart.chart.draw.plotter;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import com.frontangle.ichart.chart.ChartUtils;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Area;

public class AreaPlotter extends AbstractPlotter {

	
	/**
	 * draws one area.
	 * 
	 * @param g
	 * @param chart
	 * @param xyFactor
	 * @param currSeries
	 * @param prevSeries
	 * @param dataPoints
	 */
	public static void drawAreaOverlap(Graphics2D g, XYChart chart, XYFactor xyFactor,
			XYDataSeries currSeries, XYDataSeries prevSeries,
			CopyOnWriteArrayList<DataPoint> dataPoints) {

		ArrayList<Point> points2d = new ArrayList<Point>();
		Area area = currSeries.area;

		dataPoints = addPointsToBaseline(chart, xyFactor, dataPoints, points2d);
		
		points2d = convertDataPointsToXYPoints(dataPoints, xyFactor, chart);
		
		Shape cachedClip = ChartUtils.clipChart(g, chart);

		drawArea(g, points2d, area);
		
		g.setClip(cachedClip);

	}
	
	/**
	 * Draws a stacked area. Its x value is added to the previous series value.
	 * 
	 * @param g
	 * @param chart
	 * @param xyFactor
	 * @param currSeries
	 * @param xyDataSerieses
	 * @param prevSeries
	 */
	public static void drawAreaStacked(Graphics2D g, XYChart chart, XYFactor xyFactor, XYDataSeries<DataPoint> currSeries, ArrayList<XYDataSeries> xyDataSerieses, XYDataSeries<DataPoint> prevSeries) {

		ArrayList<Point> points2d = new ArrayList<Point>();
		ArrayList<Point> points2dPrev = new ArrayList<Point>();
		Area area = currSeries.area;
		
		CopyOnWriteArrayList<DataPoint> dataPoints = new CopyOnWriteArrayList<DataPoint>(currSeries.dataPoints);
		
		
				
		if (prevSeries == null) {
			//first series, create polygon to baseline (x == 0). All other series will stack on top of this.
			dataPoints = addPointsToBaseline(chart, xyFactor, new CopyOnWriteArrayList(currSeries.dataPoints), points2d);
		}
		
		points2d = convertDataPointsToXYPoints(dataPoints, xyFactor, chart);

		if (prevSeries != null) {
			
			CopyOnWriteArrayList<DataPoint> dataPointsPrev = new CopyOnWriteArrayList<DataPoint>(prevSeries.dataPoints);
			
			points2dPrev = convertDataPointsToXYPoints(new CopyOnWriteArrayList<DataPoint>(dataPointsPrev), xyFactor, chart);
			
			Collections.reverse(points2dPrev);
			
			points2d.addAll(points2dPrev);
		}
		
		Shape cachedClip = ChartUtils.clipChart(g, chart);
		
		drawArea(g, points2d, area);
		
		g.setClip(cachedClip);
			
	}

	private static  CopyOnWriteArrayList<DataPoint> addPointsToBaseline(XYChart chart, XYFactor xyFactor,
			CopyOnWriteArrayList<DataPoint> dataPoints, ArrayList<Point> points2d) {
		
		CopyOnWriteArrayList<DataPoint> newDataPoints = new CopyOnWriteArrayList<DataPoint>();
		
		newDataPoints.add(new DataPoint(dataPoints.get(0).x, 0));

		for (DataPoint dataPoint : dataPoints) {
			newDataPoints.add(dataPoint);
		}

		newDataPoints.add(new DataPoint(dataPoints.get(dataPoints.size() - 1).x, 0));
		
		return newDataPoints;
	}
	
	private static  ArrayList<Point>  convertDataPointsToXYPoints(CopyOnWriteArrayList<DataPoint> dataPoints, XYFactor xyFactor, XYChart chart) {
	
		ArrayList<Point> points2d = new ArrayList<Point>();
		for (DataPoint dataPoint : dataPoints) {
			points2d.add(ChartPlotter.getPoint(xyFactor, dataPoint, chart));
		}
		return points2d;
	}
	

	private static void drawArea(Graphics2D g, ArrayList<Point> points, Area area) {

		Polygon polygon = new Polygon();

		for (int i = 0; i < points.size(); i++) {
			polygon.addPoint(points.get(i).x, points.get(i).y);
		}

		g.setColor(area.color);
		g.fillPolygon(polygon);
	}


	/**
	 * The stacked area charts add on top of each other. Each subsequent data series needs to have the previous 
	 * data series values added to it.
	 * 
	 * @param xyDataSerieses
	 * @return adjusted series
	 */
	static ArrayList<XYDataSeries> getAdjustedValuesArrayForStackedArea(ArrayList<XYDataSeries> xyDataSerieses) {
		
		XYDataSeries<DataPoint> prevSeries = null;

		ArrayList<XYDataSeries> xyDataSeriesesAdjusted = new ArrayList<XYDataSeries>();
		
		for (XYDataSeries<DataPoint> currSeries : xyDataSerieses) {

			XYDataSeries<DataPoint> xyDataSeriesAdj = new XYDataSeries<DataPoint>(currSeries.name);
			xyDataSeriesAdj.area = new Area(currSeries.area.color, currSeries.area.type);
			
			if (prevSeries != null) {
				ArrayList<DataPoint> dpsAdjsuted = adjustPoints(prevSeries, currSeries);
				xyDataSeriesAdj.dataPoints = dpsAdjsuted;
			}else {
				xyDataSeriesAdj.dataPoints = currSeries.dataPoints;
			}
			prevSeries = xyDataSeriesAdj;
			
			xyDataSeriesesAdjusted.add(xyDataSeriesAdj);
		}
		return xyDataSeriesesAdjusted;
	}
	
	
	

	private static ArrayList<DataPoint> adjustPoints(XYDataSeries<DataPoint> prevSeries,
			XYDataSeries<DataPoint> xyDataSeries) {
		ArrayList<DataPoint> dps = xyDataSeries.dataPoints;
		ArrayList<DataPoint> dpsAdjsuted = new ArrayList<DataPoint>();

		for (int i = 0; i < dps.size(); i++) {
			DataPoint dataPoint = dps.get(i);
			DataPoint newDataPoint = new DataPoint();
			
			newDataPoint.x = dataPoint.x;
			
			newDataPoint.y = dataPoint.y + prevSeries.dataPoints.get(i).y;
			
			dpsAdjsuted.add(newDataPoint);
		}
		return dpsAdjsuted;
	}

}
