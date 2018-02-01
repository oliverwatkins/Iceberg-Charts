package com.frontangle.ichart.chart.legend;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Category;
import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.XYDataSeriesType;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.DataPointBar;
import com.frontangle.ichart.chart.datapoint.DataPointMultiBar;

public class LegendUtil {

	/**
	 *  Setup for XY
	 * 
	 * @param chart the chart
	 * @param g graphics context
	 */
	public static void setUpLegend(XYChart chart, Graphics2D g) {

		ArrayList<Category> categories = new ArrayList<Category>();

		XYDataSeries series = chart.data.get(0);
		DataPoint dp = (DataPoint) series.dataPoints.get(0);

		if (dp instanceof DataPointMultiBar) {
			categories = LegendUtil.setupLegendMultiBarCategories(chart, g);
		}
		if (chart.data.size() > 1) {
			categories = LegendUtil.setupLegendManySeriesCategories(chart, g);
		}

		LegendUtil.drawLegend(chart, categories, g);

		if (chart.isYAxis2) {
			LegendUtil.setupLegendYAxis2(chart, g);
		}

	}

	public static ArrayList<Category> setupLegendYAxis2(XYChart chart,
			Graphics2D g) {
		ArrayList<Category> categories;
		/**
		 * 
		 */

		categories = new ArrayList<Category>();

		for (XYDataSeries series : chart.dataY2) {

			Category category;

			if (series.type == XYDataSeriesType.BUBBLE) {
				category = new Category(series.name, series.seriesColor);
			} else {
				category = new Category(series.name, series.pointType,
						series.line);
			}
			categories.add(category);
		}
		int offset = chart.yAxis2.tickLabelOffset + chart.yAxis2.labelOffset;

		// chart.legend.paddingLegendLeft = offset;
		// chart.rightOffset = 200;
		Point startingPoint = new Point(chart.widthChart,200);

		LegendVertical legend = new LegendVertical(chart.legendFont, chart, startingPoint);

		legend.paddingLegendLeft = offset;
		chart.rightOffset = 200;

		legend.drawLegend(g, chart, categories);

		return categories;
	}

	public static ArrayList<Category> setupLegendManySeriesCategories(
			XYChart chart, Graphics2D g) {

		ArrayList<Category> categories = new ArrayList<Category>();
		/**
		 * If more than one series then we need a legend.
		 */
		for (XYDataSeries series : chart.data) {

			Category category = null;

			if (series.type == XYDataSeriesType.BUBBLE) {
				category = new Category(series.name, series.seriesColor);
				categories.add(category);
				
			} else if (series.type == XYDataSeriesType.AREA) {
				category = new Category(series.name, series.area.color);
				category.block = true;
				categories.add(category);
				
			} else if (series.type == XYDataSeriesType.MULTI_BAR) {

				DataPointMultiBar mb = (DataPointMultiBar) series.dataPoints.get(0);
				
				ArrayList<DataPointBar> dps = mb.bars;
				for (DataPointBar dpb : dps) {

					category = new Category(dpb.name, series.pointType, null);

					category.block = true;
					category.color = dpb.color;
					categories.add(category);
				}
			} else {
				category = new Category(series.name, series.pointType, series.line);
				categories.add(category);
			}
		}
		return categories;

		// chart.rightOffset = 200;
		//
		// Legend_Right legend = new Legend_Right(chart.legendFont, chart);
		//
		// chart.rightOffset = 200;
		//
		// legend.drawLegend(g, chart, categories);
	}

	public static ArrayList<Category> setupLegendMultiBarCategories(
			XYChart chart, Graphics2D g) {

		XYDataSeries series = chart.data.get(0);
		DataPoint dp = (DataPoint) series.dataPoints.get(0);

		/**
		 * if DataPointMultiBar then we pull the categories out of a single
		 * datapoint in its array
		 */
		ArrayList<Category> categories = new ArrayList<Category>();

		XYDataSeries seriesX = chart.data.get(0);
		DataPointMultiBar p = (DataPointMultiBar) series.dataPoints.get(0);

		ArrayList<DataPointBar> dps = p.bars;
		for (DataPointBar dpb : dps) {
			Category category;

			category = new Category(dpb.name, series.pointType, null);

			category.block = true;
			category.color = dpb.color;
			categories.add(category);
		}

		return categories;
	}

	public static void drawLegend(Chart chart, ArrayList<Category> categories,
			Graphics2D g) {

		if (chart.legendPosition == LegendPosition.RIGHT) {
			chart.rightOffset = 200;
			
			Point startingPoint = new Point(chart.widthChart,200);
			
			LegendVertical legend = new LegendVertical(chart.legendFont, chart, startingPoint);

			legend.drawLegend(g, chart, categories);

		} else if (chart.legendPosition == LegendPosition.BOTTOM) {
			chart.bottomOffset = 200;

			Point startingPoint = new Point(200,200);
			
			LegendHorizontal legend = new LegendHorizontal(chart.legendFont, chart);
			
			legend.drawLegend(g, chart, categories);

		} else if (chart.legendPosition == LegendPosition.TOP) {
			Point startingPoint = new Point(200,200);
			chart.topOffset = 200;
			LegendHorizontal legend = new LegendHorizontal(chart.legendFont, chart);

			legend.drawLegend(g, chart, categories);

		}
	}
	
	/**
	 * Should a legend be able to be shown? (Doesn't necessarily mean that it will be shown... but does it have the capability)
	 * 
	 * NO:
	 * A single series of data
	 * 
	 * YES:
	 * multiple series of data. (line, bubble, scatter)
	 * a single series of multibar chart
	 * pie XY chart
	 * enumerable bar chart
	 * 
	 * @param xyChart xy chart
	 * @return is legendable
	 */
	public static boolean isChartLegendable(XYChart xyChart) {
		
		//TODO this method is messy as hell

		if (xyChart.data.isEmpty())
			return false;
		if (xyChart.data.get(0).dataPoints.isEmpty())
			return false;
		
		if (xyChart.data.size() == 1) {
			XYDataSeries<DataPoint> series = xyChart.data.get(0);

			if (series.type == XYDataSeriesType.MULTI_BAR) {
				return true;
			}
			
			if (xyChart.isYAxis2) {

				if (xyChart.dataY2.isEmpty())
					return false;
				if (xyChart.dataY2.get(0).dataPoints.isEmpty())
					return false;
				
				if (xyChart.dataY2.size() == 1) {
					XYDataSeries<DataPoint> series2 = xyChart.dataY2.get(0);

					if (series2.type == XYDataSeriesType.MULTI_BAR) {
						return true;
					}
				}else {
					return true;
				}
			}
			return false;
		}
		return true;
	}

}
