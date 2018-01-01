package com.frontangle.ichart.scaling;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.frontangle.ichart.chart.ChartUtils;
import com.frontangle.ichart.chart.Orientation;
import com.frontangle.ichart.chart.Utils;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.AbstractInterval;
import com.frontangle.ichart.chart.axis.Axis;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.XAxisDrawUtil;
import com.frontangle.ichart.chart.draw.YAxisDrawUtil;

public class LinearNumericalAxisScaling extends AxisScaling {

	public Line zeroLine = new Line(Color.GRAY, false, 1);

	public DecimalFormat decimalFormat;

	/*
	 * Default constructor
	 */
	public LinearNumericalAxisScaling() {
		this(0.0, 100.0, 50.0, 10.0, 5.0); // arbitrary values!!
	}

	public LinearNumericalAxisScaling(double min, double max) {

		this(min, max, new NumericalInterval(0, 0.0), new NumericalInterval(0,
				0.0), new NumericalInterval(0, 0.0));
	}

	public LinearNumericalAxisScaling(Double primaryIncrements,
			Double secondaryIncrements, Double tertiaryIncrements) {

		this(0.0, 100.0, primaryIncrements, secondaryIncrements,
				tertiaryIncrements);
	}

	public LinearNumericalAxisScaling(Double minValue, Double maxValue,
			Double primaryIncrements, Double secondaryIncrements,
			Double tertiaryIncrements) {

		this(minValue, maxValue, new NumericalInterval(8, primaryIncrements),
				new NumericalInterval(4, secondaryIncrements),
				new NumericalInterval(2, tertiaryIncrements));
	}

	public LinearNumericalAxisScaling(Double minValue, Double maxValue,
			NumericalInterval interval1, NumericalInterval interval2,
			NumericalInterval interval3) {

		this.maxValue = maxValue;
		this.minValue = minValue;

		if (interval1 == null) {
			this.interval1 = new NumericalInterval(0, 0.0);
			this.interval1.setActive(false);
		} else {
			this.interval1 = interval1;
		}
		if (interval2 == null) {
			this.interval2 = new NumericalInterval(0, 0.0);
			this.interval2.setActive(false);
		} else {
			this.interval2 = interval2;
		}
		if (interval3 == null) {
			this.interval3 = new NumericalInterval(0, 0.0);
			this.interval3.setActive(false);
		} else {
			this.interval3 = interval3;
		}
	}

	@Override
	public void drawAllPre(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {

		drawGridFills(g2d, xyChart);
	}

	@Override
	public void drawAll(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {

		if (this.interval1.isValid() && this.interval1.isActive()) {
			drawIntervalTickAndLabelsAndGridLines(this.interval1, g2d, xyChart,
					true);
		}
		if (this.interval2.isValid() && this.interval2.isActive()) {
			drawIntervalTickAndLabelsAndGridLines(this.interval2, g2d, xyChart,
					false);
		}
		if (this.interval3.isValid() && this.interval3.isActive()) {
			drawIntervalTickAndLabelsAndGridLines(this.interval3, g2d, xyChart,
					false);
		}
	}

	/*
	 * Draw the intervals ticks and labels for one particular interval:
	 * 
	 * 10--| | | 20--| |
	 * 
	 */
	public void drawIntervalTickAndLabelsAndGridLines(
			AbstractInterval interval, Graphics2D g, XYChart chart,
			boolean showLabel) {

		NumericalInterval inter = (NumericalInterval) interval;

		Double increment = inter.getInterval();

		int incrementNo = (int) ((maxValue - minValue) / increment);

		double factor = this.getMultiplicationFactor(chart);

		double incrementInPixel = (double) (increment * factor);

		for (int i = 0; i < (incrementNo + 1); i++) {

			double pixelsFromEdge = getPixelPositionFromEdge(inter, chart, i,
					incrementInPixel);

			/**
			 * check if the pixel position is within the bounds of the chart. If
			 * not ignore it.
			 */
			if (!ChartUtils.inBounds(pixelsFromEdge, chart, orientation)) {
				continue;
			}

			drawIntervalTick(inter, g, chart, pixelsFromEdge);

			if (showLabel)
				drawIntervalLabel(inter, g, chart, getAxis(chart),
						pixelsFromEdge, i);

			if (inter != null && inter.isValid() && inter.styling != null
					&& inter.styling.graphLine != null) {

				drawGridLine(inter, g, chart, i, incrementInPixel);
			}
		}
	}

	public void drawGridLines(AbstractInterval interval, Graphics2D g,
			XYChart chart) {

		NumericalInterval inter = (NumericalInterval) interval;

		Double increment = inter.getInterval();

		int incrementNo = (int) ((maxValue - minValue) / increment);

		// divide height of chart by actual height of chart to get the
		// multiplaying factor
		double factor = getMultiplicationFactor(chart);

		double incrementInPixel = (double) (inter.getInterval() * factor);

		for (int i = 0; i < (incrementNo + 1); i++) {
			drawGridLine(inter, g, chart, i, incrementInPixel);
		}
	}

	/*
	 * Draw the tick of the interval. Usually just a small line coming out
	 * perpendicular to the axis.
	 * 
	 */
	protected void drawIntervalTick(NumericalInterval interval, Graphics g,
			XYChart chart, double pixelsFromEdge) {

		if (orientation == Orientation.X) {
			XAxisDrawUtil.drawIntervalTick(interval, g, chart, pixelsFromEdge,
					chart.xAxis);
		} else if (orientation == Orientation.Y) {
			YAxisDrawUtil.drawIntervalTick(interval, g, chart, pixelsFromEdge,
					chart.yAxis);
		} else if (orientation == Orientation.Y2) {
			YAxisDrawUtil.drawIntervalTick(interval, g, chart, pixelsFromEdge,
					chart.yAxis2);
		} else {
			throw new RuntimeException("not supported");
		}
	}

	protected void drawIntervalLabel(NumericalInterval interval, Graphics2D g,
			XYChart chart, Axis axis, double pixelsFromEdge, int incrementNumber) {

		// text to display
		String labelValueFormatted = getLabelValue(interval, incrementNumber);

		if (orientation == Orientation.X) {
			XAxisDrawUtil.drawXIntervalLabel(g, chart, pixelsFromEdge,
					labelValueFormatted, chart.xAxis, interval);
		} else if (orientation == Orientation.Y) {
			YAxisDrawUtil.drawYIntervalLabel(g, chart, pixelsFromEdge,
					labelValueFormatted, chart.yAxis);
		} else if (orientation == Orientation.Y2) {
			YAxisDrawUtil.drawYIntervalLabel(g, chart, pixelsFromEdge,
					labelValueFormatted, chart.yAxis2);
		} else {
			throw new RuntimeException("not supported");
		}

	}

	private String getLabelValue(NumericalInterval interval, int incrementNumber) {
		Double increment = interval.getInterval();

		double toFirst = getToFirstIntervalValueFromMin(increment);

		double labelValue = ((incrementNumber * increment) + toFirst);

		String labelValueFormatted = null;
		if (decimalFormat != null) {
			labelValueFormatted = ChartUtils.formatNumberValue(labelValue,
					interval, decimalFormat);
		}else {
			labelValueFormatted = "" + labelValue;
		}
		return labelValueFormatted;
	}

	protected void drawGridLine(NumericalInterval interval, Graphics g,
			XYChart chart, int i, double incrementInPixel) {

		double pixelsFromEdge = getPixelPositionFromEdge(interval, chart, i,
				incrementInPixel);

		if (orientation == Orientation.X) {
			XAxisDrawUtil.drawGridLine(interval, (Graphics2D) g, chart,
					pixelsFromEdge);
		} else if (orientation == Orientation.Y) {
			YAxisDrawUtil.drawGridLine(interval, (Graphics2D) g, chart,
					pixelsFromEdge);
		} else if (orientation == Orientation.Y2) {
			// TODO do we support grids on other Y axis too?
			// YAxisDrawUtil.drawGridLine(interval, (Graphics2D)g, chart,
			// fromStart);
		} else {
			throw new RuntimeException("not supported");
		}
	}

	protected void drawGridFills(AbstractInterval interval, Graphics2D g,
			XYChart chart) {

		double factor = getMultiplicationFactor(chart);

		// to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				((NumericalInterval) interval).getInterval(), factor);

		int incrementNo = (int) ((maxValue - minValue) / ((NumericalInterval) interval)
				.getInterval());

		incrementNo++;

		double incrementInPixel = (double) (((NumericalInterval) interval)
				.getInterval() * factor);

		g.setColor(interval.styling.graphLine.color);

		for (int i = 0; i < (incrementNo + 1); i++) {

			double fromStart = getFromStart(chart, toFirstInPixels,
					incrementInPixel, i);

			fromStart = fromStart - incrementInPixel; // start left of the y
														// Axis (invisible part)

			/**
			 * Draw Grid line
			 */

			if (this.orientation == Orientation.X)

				interval.styling.graphFill.fillAreaX(g, (int) fromStart,
						incrementInPixel, chart, i);

			else if (this.orientation == Orientation.Y)

				interval.styling.graphFill.fillAreaY(g, (int) fromStart,
						incrementInPixel, chart, i);
			else {
				throw new RuntimeException("asdf");
			}
		}
	}

	protected double getFromStart(XYChart chart, double toFirstInPixels,
			double incrementInPixel, int i) {
		if (this.orientation == Orientation.X) {
			double fromLeft = chart.leftOffset + (i * incrementInPixel)
					+ toFirstInPixels;
			return fromLeft;
		} else if (this.orientation == Orientation.Y) {
			double fromTop = chart.heightChart + chart.topOffset
					- (i * incrementInPixel) - toFirstInPixels;
			return fromTop;
		} else if (this.orientation == Orientation.Y2) {
			double fromTop = chart.heightChart + chart.topOffset
					- (i * incrementInPixel) - toFirstInPixels;
			return fromTop;
		} else {
			throw new RuntimeException();
		}
	}

	/*
	 * The distance in pixels to the first displayable interval
	 */
	protected double getToFirstIntervalValueFromMinInPixels(Double increment,
			double factor) {

		double val = getToFirstIntervalValueFromMin(increment);

		return (val - minValue) * factor;
	}


	private double getPixelPositionFromEdge(NumericalInterval interval,
			XYChart chart, int i, double incrementInPixel) {

		// divide height of chart by actual height of chart to get the
		// multiplaying factor
		double factor = getMultiplicationFactor(chart);

		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				interval.getInterval(), factor);

		double fromStart = getFromStart(chart, toFirstInPixels,
				incrementInPixel, i);

		return fromStart;
	}

	/*
	 * Get the first interval that should be displayed on the axis. Eg. if the
	 * interval increment is 50, then we want the first value to be a multiple
	 * of 50.
	 * 
	 * if the min/max range is 3/101, then the first value would be 50 (and not
	 * 3)
	 * 
	 * if min/max range is 3/40, then?? TODO
	 * 
	 * if min/max range is 3.0005/4 and the interval is 0.1, then should be 3.1
	 * 
	 * if min/max range is -0.0001/4 ....
	 * 
	 */
	protected double getToFirstIntervalValueFromMin(Double increment) {

		double val = this.minValue;

		/**
		 * Convert increment to a whole number. Get the multiplication factor
		 * and use that on the axis values. Find the first interval, then divide
		 * again.
		 */

		long multiplicationFactor = 1;

		double adjustedInc = increment;

		/**
		 * 1. Adjust the increment
		 */

		while (!(isWholeNumber(adjustedInc))) {
			adjustedInc = adjustedInc * 10;
			multiplicationFactor = multiplicationFactor * 10;
		}

		double adjustedMinValue = this.minValue * multiplicationFactor;

		long multiplicationFactor2 = 1; // reset

		/**
		 * 2. Adjust starting point. Convert it to a whole number
		 */

		while (!(isAboveOrBelow1andMinus1OrZero(adjustedMinValue))) {
			adjustedMinValue = adjustedMinValue * 10;
			multiplicationFactor2 = multiplicationFactor2 * 10; // overflows :(
		}

		adjustedMinValue = Math.round(adjustedMinValue); // round up??

		adjustedInc = adjustedInc * multiplicationFactor2;

		if (adjustedInc < 0) {
			throw new RuntimeException("increment cannot be less than zero");
		}

		val = adjustedMinValue;

		while (val % adjustedInc != 0) {
			val++;
		}

		val = val / multiplicationFactor / multiplicationFactor2;

		return val;
	}

	private boolean isWholeNumber(double val) {
		return (val == Math.floor(val)) && !Double.isInfinite(val);
	}

	private boolean isAboveOrBelow1andMinus1OrZero(double val) {
		return ((val > 1 || val < -1) || (val == 0.0));
	}

	@Override
	protected void drawGridLineOnZero(Graphics2D g) {
		// TODO
	}

	public void drawGridLineOnZero(Graphics2D g, XYChart chart) {

		// TODO draw X Line
		// XAxisDrawUtil.drawXGridLineOnZero(g, chart, -999, chart.xAxis);

		if (orientation == Orientation.Y) {
			double factor = Utils.getFactor(chart.heightChart, maxValue,
					minValue);

			if (minValue < 0) {

				int fromTop = (int) (chart.heightChart + chart.topOffset + (minValue * factor));

				// TODO draw Y Line
				// YAxisDrawUtil.drawYGridLineOnZero(g, chart, fromTop,
				// chart.yAxis);
			}
		}
	}

	/*
	 * Basically the width (or height) of the chart in pixels, divided by
	 * difference in max and min values.
	 */
	@Override
	public double getMultiplicationFactor(XYChart chart) {

		if (orientation == Orientation.X) {
			return ((double) chart.widthChart / (double) (maxValue - minValue));
		} else if (orientation == Orientation.Y) {
			return ((double) chart.heightChart / (double) (getMaxValue() - minValue));
		} else if (orientation == Orientation.Y2) {
			return ((double) chart.heightChart / (double) (getMaxValue() - minValue));
		} else {
			throw new RuntimeException("");
		}
	}
}
