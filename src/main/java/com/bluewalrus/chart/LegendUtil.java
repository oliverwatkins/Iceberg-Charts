package com.bluewalrus.chart;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.chart.datapoint.DataPointMultiBar;

public class LegendUtil {

	public static ArrayList<Category> setupLegendYAxis2(XYChart chart, Graphics2D g) {
		ArrayList<Category> categories;
		/**
		 * 
		 */
		
		categories = new ArrayList<Category>();

		for (XYDataSeries series : chart.data) {

		    Category category;

		    if (series.type == XYDataSeriesType.BUBBLE) {
		        category = new Category(series.name, series.seriesColor);
		    } else {
		        category = new Category(series.name, series.pointType, series.line);
		    }
		    categories.add(category);
		}
		int offset = chart.yAxis2.tickLabelOffset + chart.yAxis2.labelOffset;
		
		
//		chart.legend.paddingLegendLeft = offset;
//		chart.rightOffset = 200;
		
		
		Legend legend = new Legend(chart.legendFont, chart);
		
		legend.paddingLegendLeft = offset;
		chart.rightOffset = 200;
		
		legend.drawLegend(g, chart, categories);
		
		
		return categories;
	}

	public static void setupLegendManySeries(XYChart chart, Graphics2D g) {
		
		ArrayList<Category> categories = new ArrayList<Category>();
		/**
		 * If more than one series then we need a legend.
		 */
		for (XYDataSeries series : chart.data) {

			Category category = null;

			if (series.type == XYDataSeriesType.BUBBLE) {
				category = new Category(series.name, series.seriesColor);
				categories.add(category);
			} 
			else if(series.type == XYDataSeriesType.MULTI_BAR){
				
				DataPointMultiBar mb = (DataPointMultiBar)series.dataPoints.get(0);
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
		
		chart.rightOffset = 200;
		
		Legend legend = new Legend(chart.legendFont, chart);
		
		chart.rightOffset = 200;
		
		legend.drawLegend(g, chart, categories);
	}

	public static void setupLegendMultiBar(XYChart chart, Graphics2D g) {
		
		XYDataSeries series = chart.data.get(0);
		DataPoint dp = (DataPoint) series.dataPoints.get(0);
		
		/**
		 * if DataPointMultiBar then we pull the categories out of a single datapoint in
		 * its array
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
		
		
		
		chart.rightOffset = 200;
		
		Legend legend = new Legend(chart.legendFont, chart);
		
		chart.rightOffset = 200;
		
		legend.drawLegend(g, chart, categories);
	}
}
