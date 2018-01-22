package com.frontangle.ichart.chart.draw.plotter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.ArrayList;
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
	 * @param xyDataSeries
	 * @param prevXYDataSeries
	 * @param dataPoints
	 */
	public static void drawAreasOverlap(Graphics2D g, XYChart chart, XYFactor xyFactor,
			XYDataSeries xyDataSeries, XYDataSeries prevXYDataSeries,
			CopyOnWriteArrayList<DataPoint> dataPoints) {

		ArrayList<Point> points2d = new ArrayList<Point>();
		Area area = xyDataSeries.area;

		points2d = addPointsToBaseline(chart, xyFactor, dataPoints, points2d);
		
		Shape cachedClip = ChartUtils.clipChart(g, chart);

		drawArea(g, points2d, area);
		
		g.setClip(cachedClip);

	}
	
	public static void drawAreasStacked(Graphics2D g, XYChart chart, XYFactor xyFactor, XYDataSeries<DataPoint> xyDataSeries, ArrayList<XYDataSeries> xyDataSerieses) {

		ArrayList<Point> points2d = new ArrayList<Point>();
		Area area = xyDataSeries.area;
		
		points2d = addPointsToBaseline(chart, xyFactor, new CopyOnWriteArrayList(xyDataSeries.dataPoints), points2d);
		
		Shape cachedClip = ChartUtils.clipChart(g, chart);
		
		drawArea(g, points2d, area);
		
		g.setClip(cachedClip);
			
	}

	private static  ArrayList<Point> addPointsToBaseline(XYChart chart, XYFactor xyFactor,
			CopyOnWriteArrayList<DataPoint> dataPoints, ArrayList<Point> points2d) {
		
		points2d.add(ChartPlotter.getPoint(xyFactor, new DataPoint(dataPoints.get(0).x, 0), chart));

		for (DataPoint dataPoint : dataPoints) {
			points2d.add(ChartPlotter.getPoint(xyFactor, dataPoint, chart));
		}

		points2d.add(ChartPlotter.getPoint(xyFactor,
				new DataPoint(dataPoints.get(dataPoints.size() - 1).x, 0), chart));
		
		return points2d;
	}

	public static void drawArea(Graphics2D g, ArrayList<Point> points, Area area) {

		Polygon polygon = new Polygon();

		for (int i = 0; i < points.size(); i++) {
			polygon.addPoint(points.get(i).x, points.get(i).y);
		}

		g.setColor(area.color);
		g.fillPolygon(polygon);
	}



	static ArrayList<XYDataSeries> getAdjustValuesArray(ArrayList<XYDataSeries> xyDataSerieses) {
		
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
			prevSeries = currSeries;
			
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
			
			System.out.println("dataPoint.y " + dataPoint.y);
			newDataPoint.y = dataPoint.y + prevSeries.dataPoints.get(i).y;
			System.out.println("newDataPoint.y " + newDataPoint.y);
			
			dpsAdjsuted.add(newDataPoint);
		}
		return dpsAdjsuted;
	}

}
