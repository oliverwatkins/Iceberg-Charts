package com.bluewalrus.chart.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.XYChart;

public abstract class AxisDraw {

	public double maxValue = 100; //arbitrary
	public double minValue = 0; //arbitrary

	public abstract void drawAll(Graphics2D g2d, XYChart xyChart, ArrayList<XYDataSeries> data);
	
//	public abstract void drawAllIntervalTickAndLabels(Graphics g, XYChart chart);

	protected abstract void drawGridLineOnZero(Graphics2D g);

}
