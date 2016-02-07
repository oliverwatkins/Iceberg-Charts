package com.bluewalrus.chart.draw;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.AbstractInterval;

public abstract class AxisDraw {

	public double maxValue = 100; //arbitrary
	public double minValue = 0; //arbitrary
	
	public AbstractInterval interval1; // = new AbstractInterval(0, 0.0);
	public AbstractInterval interval2; //= new AbstractInterval(0, 0.0);
	public AbstractInterval interval3; //= new AbstractInterval(0, 0.0);
	

	public abstract void drawAll(Graphics2D g2d, XYChart xyChart, ArrayList<XYDataSeries> data);
	
//	public abstract void drawAllIntervalTickAndLabels(Graphics g, XYChart chart);

	protected abstract void drawGridLineOnZero(Graphics2D g);
	
	protected abstract double getMultiplicationFactor(XYChart chart);
	
	public void drawGridLines(Graphics2D g, XYChart chart) {
		
		if (interval1 != null && interval1.isValid()
			&& interval1.graphLine != null) {
			this.drawGridLine(interval1, g, chart);
		}
		if (interval2 != null && interval2.isValid()
			&& interval2.graphLine != null) {
			this.drawGridLine(interval2, g, chart);
		}
		if (interval3 != null && interval3.isValid()
			&& interval3.graphLine != null) {
			this.drawGridLine(interval3, g, chart);
		}
	}
	
	protected abstract void drawGridLine(AbstractInterval interval, Graphics2D g, XYChart chart);
	
	

}
