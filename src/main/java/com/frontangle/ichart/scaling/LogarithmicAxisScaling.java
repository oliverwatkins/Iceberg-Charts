package com.frontangle.ichart.scaling;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Orientation;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.AbstractInterval;
import com.frontangle.ichart.chart.axis.Axis;
import com.frontangle.ichart.chart.axis.IntervalStyling;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.XAxisDrawUtil;
import com.frontangle.ichart.chart.draw.YAxisDrawUtil;

public class LogarithmicAxisScaling extends AxisScaling{

	public LogarithmicAxisScaling(double minValue, double maxValue) {
		this.maxValue = maxValue;
		this.minValue = minValue;
	}

	@Override
	public void drawAllPre(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {
		
		drawGridFills(g2d, xyChart);
	}

	@Override
	public void drawAll(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {
		
		this.interval1 = new NumericalInterval(1);
		this.interval1.styling = new IntervalStyling(6, new Line(Color.BLACK, false, 2), null);

		this.interval2 = new NumericalInterval(1);
		this.interval2.styling = new IntervalStyling(1, new Line(Color.LIGHT_GRAY, false, 1), null);

		drawIntervalTickAndLabelsAndGridLines(this.interval1, g2d, xyChart, true);
	}
	
	@Override
	public void drawIntervalTickAndLabelsAndGridLines(
			AbstractInterval interval, Graphics2D g, XYChart chart,
			boolean showLabel) {
		
		NumericalInterval inter = (NumericalInterval)interval;
		
		double d1 = Math.log10(maxValue);
		double d2 = Math.log10(minValue);
		
		int incrementNo =  (int)(d1 - d2);
		double incrementInPixel = chart.widthChart / incrementNo;
		
		double pixelsFromEdge = chart.leftOffset;
		
		for (int i = 0; i < (incrementNo + 1); i++) {
			
			if (orientation == Orientation.Y) {
				//TODO!!!! need to do for Y axis as well
			}
			
			drawIntervalTick(inter, g, chart, pixelsFromEdge);
			
			drawIntervalLabel(inter, g, chart, getAxis(chart), pixelsFromEdge, i);
			
			if (inter != null && inter.isValid()
					&& inter.styling != null
					&& inter.styling.graphLine != null) {
				
				XAxisDrawUtil.drawGridLine(inter, (Graphics2D)g, chart, pixelsFromEdge);
			}
			
			drawLogLines(pixelsFromEdge, incrementInPixel, g, chart);
			
			pixelsFromEdge = pixelsFromEdge + incrementInPixel;
		}
	}

	private void drawLogLines(double pixelsFromEdge, double incrementInPixel, Graphics2D g, XYChart chart) {
		
		for (int i = 0; i < 10; i++) {
			double val = Math.log10(i);

			double v2 = pixelsFromEdge + (incrementInPixel * val);
			drawIntervalTick(this.interval2, g, chart, v2);
			XAxisDrawUtil.drawGridLine(this.interval2, (Graphics2D)g, chart, v2);
		}
	}


	private void drawIntervalLabel(NumericalInterval inter, Graphics2D g,
			XYChart chart, Axis axis, double pixelsFromEdge, int i) {
		
		//text to display
		String labelValueFormatted = "" + (int)Math.pow(10.0, i);
		
		if (orientation == Orientation.X) {
			XAxisDrawUtil.drawXIntervalLabel(g, chart, pixelsFromEdge, labelValueFormatted, chart.xAxis, inter);
		}else if (orientation == Orientation.Y) {
			YAxisDrawUtil.drawYIntervalLabel(g, chart, pixelsFromEdge, labelValueFormatted, chart.yAxis);
		}else if (orientation == Orientation.Y2) {
			YAxisDrawUtil.drawYIntervalLabel(g, chart, pixelsFromEdge, labelValueFormatted, chart.yAxis2);
		}else {
			throw new RuntimeException("not supported");
		}
	}

	private void drawIntervalTick(AbstractInterval inter, Graphics2D g,
			XYChart chart, double pixelsFromEdge) {
		
		if (orientation == Orientation.X) {
			XAxisDrawUtil.drawIntervalTick(inter, g, chart, pixelsFromEdge, chart.xAxis);
		}else if (orientation == Orientation.Y) {
			YAxisDrawUtil.drawIntervalTick(inter, g, chart, pixelsFromEdge, chart.yAxis);
		}else if (orientation == Orientation.Y2) {
			YAxisDrawUtil.drawIntervalTick(inter, g, chart, pixelsFromEdge, chart.yAxis2);
		}else {
			throw new RuntimeException("not supported");
		}
	}


	@Override
	protected void drawGridLineOnZero(Graphics2D g) {
		// TODO irrelevant here?
		
	}

	@Override
	public double getMultiplicationFactor(XYChart chart) {
		// TODO irrelevant here
		return 0;
	}

	@Override
	protected void drawGridFills(AbstractInterval interval12, Graphics2D g,
			XYChart chart) {
		// TODO irrelevant here?
	}

	@Override
	protected double getToFirstIntervalValueFromMinInPixels(Double interval,
			double factor) {
		// TODO irrelevant here
		return 0;
	}

	@Override
	protected double getFromStart(XYChart chart, double toFirstInPixels,
			double incrementInPixel, int incrementNo) {
		// TODO Auto-generated method stub
		return 0;
	}



}
