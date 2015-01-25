package com.bluewalrus.bar;

import java.awt.Color;
import java.util.ArrayList;

public class GradiantRule {
	
	ArrayList<Color> colors;
	int totalRange;
	int numberOfShades;
	double minValue;
	public GradiantRule (int minValue, int maxValue, Color color1, Color color2, int numberOfShades) {
		
		this.minValue = minValue;
		
		totalRange = (maxValue - minValue);
		this.numberOfShades = numberOfShades;
				
		colors = Utils.makeGradients(color1, color2, numberOfShades);
	}

	public Color getColor(double value) {
		
		double x = (value + minValue) / totalRange;
		
		double i = (double)(x * this.numberOfShades);
		
		return colors.get((int)i);
	}

}
