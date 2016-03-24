package com.bluewalrus.chart.draw;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.Orientation;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.AbstractInterval;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.renderer.XYFactor;

public class EnumerationAxisScalingX extends EnumerationAxisScaling {

	public EnumerationAxisScalingX() {
		super(Orientation.X);
	}
	
	public double getMaxValue() {
		return 100;
	}
	public double getMinValue() {
		return 0;
	}

	@Override
	public void drawAll(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {

		double xMax = this.maxValue;
		double xMin = this.minValue;

		double yMax = this.maxValue;
		double yMin = this.minValue;

		double xFactor = ((double) xyChart.widthChart / (double) (xMax - xMin));
		double yfactor = ((double) xyChart.heightChart / (double) (yMax - yMin));


		ArrayList<DataPoint> dataPoints = data.get(0).dataPoints;

		for (DataPoint dataPoint : dataPoints) {
			drawXLabel(g2d, xyChart, (DataPoint) dataPoint, ((int)(dataPoint.x * xFactor)+xyChart.leftOffset));
		}
	}

	private static void drawXLabel(Graphics2D g, XYChart chart,
			DataPoint dp, int x) {

		if (dp.name != null) {
			drawText(chart, dp.name, g, x);
		} else {
			drawText(chart, "" + dp.x, g, x);
		}
		drawTickLine(g, chart, x);
	}

	protected static void drawText(XYChart chart, String name, Graphics g, int x) {

		FontMetrics fm = chart.getFontMetrics(chart.xAxis.axisCatFont);
		int widthStr = fm.stringWidth(name);
		int heightStr = fm.getHeight();

		g.setFont(chart.xAxis.axisCatFont);
		g.setColor(Color.BLACK);

		int xPosition = x - (widthStr / 2);
		int yPosition = chart.topOffset + chart.xAxis.marginOffset
				+ chart.heightChart + chart.xAxis.labelOffset - heightStr / 2;

		System.out.println("draw dp.xName " + name + " x " + x + " yPosition "
				+ yPosition);

		// draw tick
		g.drawString(name, xPosition, yPosition);
	}

	protected static void drawTickLine(Graphics g, XYChart chart, int x) {
		g.drawLine(
				(int) (x),
				(int) (chart.topOffset + chart.xAxis.marginOffset + chart.heightChart),
				(int) (x), (int) (chart.topOffset + chart.xAxis.marginOffset
						+ chart.heightChart + 2));
	}
	


	@Override
	protected void drawGridLineOnZero(Graphics2D g) {
		//ignore here.
	}

	@Override
	protected double getMultiplicationFactor(XYChart chart) {
		// ???
		return 0;
	}

	@Override
	protected void drawGridLines(AbstractInterval interval, Graphics2D g,
			XYChart chart) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void drawGridFills(AbstractInterval interval, Graphics2D g,
			XYChart chart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawAllPre(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected double getToFirstIntervalValueFromMinInPixels(Double interval,
			double factor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected double getFromStart(XYChart chart, double toFirstInPixels,
			double incrementInPixel, int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public String toString() {
		return "EnumerationAxisDrawX [maxValue=" + maxValue + ", minValue="
				+ minValue + ", orientation=" + orientation + ", interval1="
				+ interval1 + ", interval2=" + interval2 + ", interval3="
				+ interval3 + "]";
	}

}
