package com.bluewalrus.chart.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.bluewalrus.bar.Orientation;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.NumericalInterval;

public class LinearNumericalAxisScalingX extends LinearNumericalAxisScaling {

	public LinearNumericalAxisScalingX() {
		super(Orientation.X);
	}

	public LinearNumericalAxisScalingX(double min, double max) {
		super(min, max, Orientation.X);
	}

	public LinearNumericalAxisScalingX(Double primaryIncrements,
			Double secondaryIncrements, Double tertiaryIncrements) {

		super(primaryIncrements, secondaryIncrements, tertiaryIncrements, Orientation.X);
	}

	public LinearNumericalAxisScalingX(int minValue, int maxValue,
			NumericalInterval interval1, NumericalInterval interval2,
			NumericalInterval interval3) {

		this((double) minValue, (double) maxValue, interval1, interval2, interval3);
	}

	public LinearNumericalAxisScalingX(Double minValue, Double maxValue,
			NumericalInterval interval1, NumericalInterval interval2,
			NumericalInterval interval3) {

		super(minValue, maxValue, interval1, interval2, interval3, Orientation.X);
	}

	public LinearNumericalAxisScalingX(Double minValue, Double maxValue,
			Double primaryIncrements, Double secondaryIncrements,
			Double tertiaryIncrements) {

		super(minValue, maxValue, primaryIncrements, secondaryIncrements,
				tertiaryIncrements, Orientation.X);
	}


	@Override
	public void drawAll(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {

		// NOTE! data is ignored here. It's only used for enumeration
		drawAllIntervalTickAndLabels(g2d, xyChart);

		drawGridLines(g2d, xyChart);
	}

	@Override
	public void drawAllPre(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {

		drawGridFills(g2d, xyChart);
	}
	

	protected double getFromStart(XYChart chart, double toFirstInPixels, double incrementInPixel, int i) {
		double fromLeft = chart.leftOffset + (i * incrementInPixel)
				+ toFirstInPixels;
		return fromLeft;
	}

	@Override
	protected double getMultiplicationFactor(XYChart chart) {
		return ((double) chart.widthChart / (double) (maxValue - minValue));
	}
	

	@Override
	protected void drawGridLineOnZero(Graphics2D g) {
		// TODO

	}

	@Override
	public void drawGridLineOnZero(Graphics2D g, XYChart chart) {

		// TODO draw X Line
		// XAxisDrawUtil.drawXGridLineOnZero(g, chart, -999, chart.xAxis);
	}



}
