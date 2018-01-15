package com.frontangle.ichart.chart.draw.plotter;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.frontangle.ichart.chart.ChartUtils;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Area;
import com.frontangle.ichart.chart.draw.Line;

public class AreaPlotter extends AbstractPlotter {

	public static void drawAreas(Graphics2D g, XYChart chart, XYFactor xyFactor,
			XYDataSeries xYDataSeries, CopyOnWriteArrayList<DataPoint> dataPoints) {
		
		ArrayList<Point> points = new ArrayList<Point>();
		
		points.add(ChartPlotter.getPoint(xyFactor, new DataPoint(dataPoints.get(0).x,0), chart));
		
		for (DataPoint dataPoint : dataPoints) {
			points.add(ChartPlotter.getPoint(xyFactor, dataPoint, chart));
		}		

		points.add(ChartPlotter.getPoint(xyFactor, new DataPoint(dataPoints.get(dataPoints.size()-1).x,0), chart));
		
		Area area = xYDataSeries.area;
		
		Shape cachedClip = ChartUtils.clipChart(g, chart);
		
		area.drawArea(g, points);
		
		g.setClip(cachedClip);
		
	}


}
