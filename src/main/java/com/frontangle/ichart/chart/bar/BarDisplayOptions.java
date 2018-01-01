package com.frontangle.ichart.chart.bar;

import java.awt.Color;

import com.frontangle.ichart.chart.draw.point.UIPointBar;

public class BarDisplayOptions {
	
	UIPointBar point = new UIPointBar(Color.PINK, 50);

	GradiantRule gradiantRule;

	public Color positiveColor, color;
	public Color negativeColor;

	public double transparancy = -1;

	
	public void setGradiantRule(GradiantRule gradiantRule) {
		this.gradiantRule = gradiantRule;
	}
}
