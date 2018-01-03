package com.frontangle.ichart.chart.draw;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.ChartUtils;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.axis.AbstractInterval;
import com.frontangle.ichart.chart.axis.Axis;
import com.frontangle.ichart.chart.axis.TimeInterval;
import com.frontangle.ichart.chart.axis.XAxis;

/**
 * Tick, GridLine, Label, XLabel
 * 
 * @author Oliver Watkins
 */
public class XAxisDrawUtil {

	 //Draw Tick. If the position (from left) is passed the edge of the chart, then don't draw it.
	public static void drawIntervalTick(AbstractInterval interval, Graphics g,
			Chart chart, double fromLeft, Axis axis) {

		if (interval.styling == null) {
			return;
		}

		int y1 = (chart.topOffset + chart.heightChart + axis.marginOffset);
		int y2 = (chart.topOffset + chart.heightChart + axis.marginOffset + interval.styling.lineLength);

		if (isXPositionInsideChart(chart, fromLeft))
			g.drawLine((int) fromLeft, y1, (int) fromLeft, y2);

	}

	 //Draw Grid Line
	public static void drawGridLine(AbstractInterval interval, Graphics2D g,
			Chart chart, double fromLeft) {

		int y1 = chart.topOffset + chart.heightChart;
		int y2 = chart.topOffset;

		Shape clip = ChartUtils.clipChart(g, chart);

		interval.styling.graphLine.drawLine(g, (int) fromLeft, y1,
				(int) fromLeft, y2);
		g.setClip(clip);
	}
	
	public static void drawGridFill(TimeInterval interval, Graphics2D g,
			Chart chart, double fromLeft, double width, int incrementNo) {

		Shape clip = ChartUtils.clipChart(g, chart);

		interval.styling.graphFill.fillAreaX(g, fromLeft, width, chart, incrementNo);
		
		g.setClip(clip);
	}

	

	 //Draw interval label. By default only first level labels are drawn in. 
	public static void drawXIntervalLabel(Graphics g, XYChart chart, double fromLeft,
			String xLabel, Axis axis, AbstractInterval interval) {
		
		int level = interval.getLevel();
		
		FontMetrics fm = chart.getFontMetrics(axis.axisCatFont);
		int widthStr = fm.stringWidth(xLabel);

		fromLeft = fromLeft - (widthStr / 2); // move back half text length

		int yPos = chart.topOffset + chart.heightChart + axis.tickLabelOffset;

		// TODO
		if (level == 1) {
//			yPos = yPos + 100;
		} else if (level == 2) {
			yPos = yPos - 20;
		} else if (level == 3) {
			yPos = yPos - 35;
		}
		
		
		if (interval.isCentered()) {
						
			double pics = ChartUtils.getIncrementInPixels(interval, chart, axis.axisScaling);
			
			fromLeft = fromLeft + pics/2;
		}
		
		if (interval.styling != null && interval.styling.intervalFont != null)
			g.setFont(interval.styling.intervalFont);

		if (isXPositionInsideChart(chart, fromLeft))
			g.drawString(xLabel + "", (int) fromLeft, yPos);
	}

	public static void drawLabel(Chart chart, XAxis axis, Graphics2D g2d) {

		FontMetrics fmX = chart.getFontMetrics(axis.font);
		int xAxisStringWidth = fmX.stringWidth(axis.labelText);

		// X Label
		int xAxesLabelHeight = chart.bottomOffset - axis.labelOffset;

		// x label
		g2d.setFont(axis.font);
		
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.drawString(axis.labelText, chart.widthChart / 2 + chart.leftOffset
				- xAxisStringWidth / 2, chart.topOffset + chart.heightChart
				+ axis.labelOffset + xAxesLabelHeight / 2);
	}

	private static boolean isXPositionInsideChart(Chart chart, double fromLeft) {

		int rightSideChart = (chart.widthChart + chart.leftOffset);

		if (fromLeft < rightSideChart && fromLeft > chart.leftOffset) {
			return true;
		}
		return false;
	}
}
