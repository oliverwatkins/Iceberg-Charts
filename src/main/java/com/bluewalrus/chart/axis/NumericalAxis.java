package com.bluewalrus.chart.axis;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bluewalrus.bar.Interval;
import com.bluewalrus.chart.Chart;

public class NumericalAxis extends Axis{

	
	public NumericalAxis(Double minValue, Double maxValue, Double primaryIncrements,
			Double secondaryIncrements, Double tertiaryIncrements, String name) {
		
		super(minValue, maxValue, primaryIncrements, secondaryIncrements,
				tertiaryIncrements, name);
		
		
	}

	@Override
	protected double getMultiplicationFactor(Chart chart) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void drawIntervalTick(Interval interval, Graphics g, Chart chart,
			int i, double incrementInPixel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void drawIntervalLabel(Interval interval, Graphics g,
			Chart chart, int i, double incrementInPixel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawLabel(Graphics g, Chart chart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawBorderLine(Graphics g, Chart chart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawGridLine(Interval interval, Graphics2D g, Chart chart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void drawYGridLineOnZero(Graphics2D g, Chart chart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
