//package com.bluewalrus.chart.legend;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.FontMetrics;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Point;
//import java.awt.Rectangle;
//import java.awt.Shape;
//import java.awt.geom.RoundRectangle2D;
//import java.io.Serializable;
//import java.util.ArrayList;
//
//import com.bluewalrus.chart.Category;
//import com.bluewalrus.chart.Chart;
//import com.bluewalrus.chart.draw.Line;
//import com.bluewalrus.chart.draw.point.UIPointSimpleXY;
//import com.bluewalrus.chart.draw.point.UIPointXY;
//
//public class Legend_Bottom extends AbstractLegend implements Serializable {
//
//	public LegendPosition legendPosition = LegendPosition.BOTTOM;
//
//	public Legend_Bottom(Font legendFont, Chart chart, int paddingLeft) {
//		super(legendFont, chart);
//		this.legendFont = legendFont;
//		this.paddingLegendLeft = paddingLeft;
//	}
//
//	public Legend_Bottom(Font legendFont, Chart chart) {
//		super(legendFont, chart);
//	}
//
//	public void drawLegend(Graphics2D g, Chart chart, ArrayList<Category> data) {
//
//		this.legendX = getStartingXPointHorizontal(chart, data);
//		
//		this.legendY = chart.getHeight() - chart.bottomOffset + 60; // +
//																	// paddingBetweenChartAndLegend;
//
//		this.legendHeight = (squareWidth); // - (2 *
//											// paddingBetweenChartAndLegend);
//		this.legendWidth = (data.size() * squareWidth) + 300; // chart.rightOffset
//																// -
//																// (paddingLegendLeft);
//
//		// draw outside rectangle
//		g.setColor(legendBackgroundColor);
//
//		g.fill(new RoundRectangle2D.Double(legendX, legendY, legendWidth
//				- paddingLegendRight, legendHeight, 10, 10));
//
//		// draw outside rectangle
//		g.setColor(outsideRectangleColor);
//
//		g.draw(new RoundRectangle2D.Double(legendX, legendY, legendWidth
//				- paddingLegendRight, legendHeight, 10, 10));
//
//		int i = 0;
//
//		for (Category category : data) {
//
//			drawCategoryHorizontal(g, chart, i, category);
//			i++;
//		}
//	}
//
//
//}
