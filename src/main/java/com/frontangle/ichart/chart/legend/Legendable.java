package com.frontangle.ichart.chart.legend;

import java.awt.Graphics2D;

/**
 * A chart that can have a legend. Pretty much all types of charts
 * 
 * @author Oliver Watkins
 *
 */
public interface Legendable {
	public void drawLegend(Graphics2D g);
}
