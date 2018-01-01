package com.frontangle.ichart.chart.bar;

import java.awt.Color;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Utils;

/**
 * A gradient rule creates an array of colors based on a range between two colors, and a range of integers. 
 *
 * @author oliver
 */
public class GradiantRule {

    private Color color1;
    private Color color2;
    
    public GradiantRule(int minValue, int maxValue, Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    //Return the color in color array based on numerical value.
    public Color getColor(double percentChange) {
    	
        int red = color1.getRed();
        int blue = color1.getBlue();
        int green = color1.getGreen();

        int red2 = color2.getRed();
        int blue2 = color2.getBlue();
        int green2 = color2.getGreen();

        int incrementRed = (int)((red2 - red) * percentChange);
        int incrementBlue = (int)((blue2 - blue) * percentChange);
        int incrementGreen = (int)((green2 - green) * percentChange);

        int newRed = red + incrementRed;
        int newGreen = green + incrementGreen;
        int newBlue = blue + incrementBlue;
        Color c = new Color(newRed, newGreen, newBlue);
            
        return c;
    }

}
