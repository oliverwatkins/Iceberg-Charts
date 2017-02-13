package com.bluewalrus.chart.legend;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import com.bluewalrus.chart.Category;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.XYDataSeriesType;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.chart.datapoint.DataPointMultiBar;

public class LegendUtil {

	/**
	 * Setup for XY
	 * 
	 * @param chart
	 * @param g
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
				category = new Category(series.name, series.pointType,
						series.line);
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

}
