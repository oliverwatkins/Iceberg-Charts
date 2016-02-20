package com.bluewalrus.chart.draw;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.AbstractInterval;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.renderer.XYFactor;

public class EnumerationAxisDrawX extends EnumerationAxisDraw {

	@Override
	public void drawAll(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {

		double xMax = this.maxValue;
		double xMin = this.minValue;

		double yMax = this.maxValue;
		double yMin = this.minValue;

		double xFactor = ((double) xyChart.widthChart / (double) (xMax - xMin));
		double yfactor = ((double) xyChart.heightChart / (double) (yMax - yMin));

		XYFactor xyFactor = new XYFactor(xFactor, yfactor);
		xyFactor.xZeroOffsetInPixel = (double) ((-xMin / (xMax - xMin)) * xyChart.widthChart);
		xyFactor.yZeroOffsetInPixel = (double) ((-yMin / (yMax - yMin)) * xyChart.heightChart);

		ArrayList<DataPoint> dataPoints = data.get(0).dataPoints;

		double xRange = (double) (xMax - xMin);

		// distance between points (bars)
		double pointDistance = (double) (xyChart.widthChart / (dataPoints
				.size() + 1));

		System.out.println("range = " + xRange);
		System.out.println("pointDistance = " + pointDistance);

		int i = 1;
		for (DataPoint dataPoint : dataPoints) {

			double xShift2 = (pointDistance * i);

			int x = (int) (xyChart.leftOffset + (xShift2));

			drawXLabel(g2d, xyChart, (DataPoint) dataPoint, x);

			i++;
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
	protected void drawGridLine(AbstractInterval interval, Graphics2D g,
			XYChart chart) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void drawGridFill(AbstractInterval interval, Graphics2D g,
			XYChart chart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawAllPre(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {
		// TODO Auto-generated method stub
		
	}


}
