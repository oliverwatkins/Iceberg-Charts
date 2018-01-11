package com.frontangle.ichart.chart.draw.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import com.frontangle.ichart.chart.ChartUtils;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.DataPointWithMagnitude;

/**
 * TODO offer option of setting magnitude as radius or area!!!
 * 
 * At the moment is radius only.
 */
public class UIPointBubble extends UIPointComplexXY {

	// by default scale on Y
	boolean scaleOnX = false;

	private Ellipse2D oval;

	private boolean mouseIsOverPoint = false;
	private RadialGradientPaint paint;
	private Paint graphicsPaint;
	private DataPointWithMagnitude dpWithM;
	private int x;
	private int y;

	private double mag;
	private Point point;

	public UIPointBubble(Color color) {
		super(color);
	}

	public void draw(Graphics2D g, Point point, DataPoint dataPoint,
			XYFactor xyFactor, XYChart chart, int pixBtnFirst2Pts) {

		this.point = point;
		dpWithM = (DataPointWithMagnitude) dataPoint;

		mag = 0;

		if (scaleOnX) {
			mag = dpWithM.magnitude * xyFactor.getxFactor();
		} else {
			mag = dpWithM.magnitude * xyFactor.getyFactor();
		}

		if (mag <= 0) {
			throw new RuntimeException("magnitude cannot be zero or less : " + mag);
		}
		/**
		 * Magnitude is equivalent to area of circle.
		 * 
		 * So we calculate the radius like this :
		 * 
		 * radius = sqrt(area / pie)
		 */
		double radius = Math.sqrt(mag / Math.PI);

		g.setColor(color);

		graphicsPaint = g.getPaint();

		Color c = new Color(color.getRed(), color.getGreen(), color.getBlue(), 100);
		Color c2 = new Color(Color.RED.getRed(), Color.RED.getGreen(), Color.RED.getBlue(), 100);

		Color[] colors = { c, c2 };

		float[] dist = { .3f, .7f };

		x = (int) (point.x - (radius));
		y = (int) (point.y - (radius));

		paint = new RadialGradientPaint(point, (int) mag, dist, colors);

		g.setPaint(paint);

		oval = new Ellipse2D.Double(x, y, (int) radius * 2, (int) radius * 2);

		this.clipAndDrawPoint(g, chart);
	}

	@Override
	public void drawPoint(Graphics2D g) {

		g.fill(oval);

		g.setPaint(graphicsPaint);

		// draw text
		g.setColor(Color.BLACK);

		if (dpWithM.name != null)
			g.drawString("" + dpWithM.name, x, y);

		if (mouseIsOverPoint) {
			g.setPaint(this.getHighlightedPaint(point, mag));
			g.fill(oval);
		} else {
			g.setPaint(paint);
		}
	}

	private Paint getHighlightedPaint(Point point, double mag) {

		Color c = new Color(color.getRed(), color.getGreen(), color.getBlue(),
				100);
		Color c2 = new Color(Color.PINK.getRed(), Color.PINK.getGreen(),
				Color.PINK.getBlue(), 100);

		Color[] colors = { c, c2 };

		float[] dist = { .4f, .6f };

		RadialGradientPaint rpaint = new RadialGradientPaint(new Point(point.x,
				point.y), (int) mag, dist, colors);

		return rpaint;
	}

	@Override
	public boolean doesShapeContainPoint(Point point) {
		if (oval.contains(point)) {
			mouseIsOverPoint = true;
			return true;
		} else {
			mouseIsOverPoint = false;
			return false;
		}
	}
}
