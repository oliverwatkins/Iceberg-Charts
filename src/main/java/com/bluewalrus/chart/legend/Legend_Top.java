package com.bluewalrus.chart.legend;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;
import java.util.ArrayList;

import com.bluewalrus.chart.Category;
import com.bluewalrus.chart.Chart;

public class Legend_Top extends AbstractLegend implements Serializable {

	public LegendPosition legendPosition = LegendPosition.BOTTOM;

	public Legend_Top(Font legendFont, Chart chart, int paddingLeft) {
		super(legendFont, chart);
		this.legendFont = legendFont;
		this.paddingLegendLeft = paddingLeft;
	}

	public Legend_Top(Font legendFont, Chart chart) {
		super(legendFont, chart);
	}

	public void drawLegend(Graphics2D g, Chart chart, ArrayList<Category> data) {

		this.legendX = getStartingXPointHorizontal(chart, data);
		
		
		this.legendY =  50;
				
		this.legendHeight = (squareWidth); // - (2 *
											// paddingBetweenChartAndLegend);
		this.legendWidth = (data.size() * squareWidth) + 300; // chart.rightOffset
																// -
																// (paddingLegendLeft);

		// draw outside rectangle
		g.setColor(legendBackgroundColor);

		g.fill(new RoundRectangle2D.Double(legendX, legendY, legendWidth
				- paddingLegendRight, legendHeight, 10, 10));

		// draw outside rectangle
		g.setColor(outsideRectangleColor);

		g.draw(new RoundRectangle2D.Double(legendX, legendY, legendWidth
				- paddingLegendRight, legendHeight, 10, 10));

		int i = 0;

		for (Category category : data) {

			drawCategoryHorizontal(g, chart, i, category);
			i++;
		}
	}

//
//
//	private int calculateWidthLegendHorizontal(ArrayList<Category> data) {
//		int s = (data.size() * squareWidth) + (data.size() * fixedTextLength);
//		return s;
//	}
}
