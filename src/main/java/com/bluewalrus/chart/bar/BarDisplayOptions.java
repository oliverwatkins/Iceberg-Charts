package com.bluewalrus.chart.bar;

import java.awt.Color;

import com.bluewalrus.chart.draw.point.UIPointBar;

public class BarDisplayOptions {
	
	UIPointBar point = new UIPointBar(Color.PINK, 50);

	GradiantRule gradiantRule;

	public Color positiveColor;
	public Color negativeColor;
	
	public void setGradiantRule(GradiantRule gradiantRule) {
		this.gradiantRule = gradiantRule;
	}

}
