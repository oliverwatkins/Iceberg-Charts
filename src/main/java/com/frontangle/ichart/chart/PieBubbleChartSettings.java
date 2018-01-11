/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.frontangle.ichart.chart;

import java.awt.Color;


/**
 * 
 * @author Oliver Watkins
 */
public class PieBubbleChartSettings {
    
    public int frontalTransparancy = 256;
        
    public Color c1 = new Color(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), 100);
    public Color c2 = new Color(Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.BLACK.getBlue(), 100);

    public void setRadialColorStartingPoint1(Color c) {
        c1 = new Color(c.getRed(), c.getGreen(), c.getBlue(), frontalTransparancy);
    }
    
    public void setRadialColorStartingPoint2(Color c) {
        c2 = new Color(c.getRed(), c.getGreen(), c.getBlue(), frontalTransparancy);
    }
    
}
