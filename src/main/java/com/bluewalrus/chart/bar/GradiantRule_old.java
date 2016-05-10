package com.bluewalrus.chart.bar;

import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.chart.Utils;

/**
 * A gradient rule creates an array of colors based on a range between two colors, and a range of integers. 
 *
 * @author oliver
 */
public class GradiantRule_old {

    ArrayList<Color> colors;
    int totalRange;
    int numberOfShades;
    double minValue;

    /**
     * This constructor is deprecated
     * 
     * @deprecated
     * @param minValue
     * @param maxValue
     * @param color1
     * @param color2
     * @param numberOfShades
     */
    public GradiantRule_old(int minValue, int maxValue, Color color1, Color color2, int numberOfShades) {

        this.minValue = minValue;

        totalRange = (maxValue - minValue);
        this.numberOfShades = numberOfShades;

        colors = Utils.makeGradients(color1, color2, numberOfShades);
    }
    
    
//    public GradiantRule(int minValue, int maxValue, Color color1, Color color2) {
//
//        this.minValue = minValue;
////        this.maxValue = minValue;
//        
//
//        totalRange = (maxValue - minValue);
//
//        colors = Utils.makeGradients(color1, color2, numberOfShades);
//    }

    /**
     * Return the color in color array based on numerical value.
     * 
     * @param value
     * @return 
     */
    public Color getColor(double value) {

        double x = (value + minValue) / totalRange;

        double i = (double) (x * this.numberOfShades);

        return colors.get((int) i);
    }

}
