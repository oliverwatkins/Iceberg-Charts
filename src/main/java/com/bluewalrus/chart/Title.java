package com.bluewalrus.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Title above the chart. All charts should have a title
 * 
 * @author Oliver Watkins
 *
 */
public class Title {
	
	public Font titleFont = new Font("Arial", Font.BOLD, 18);
	public Color titleColor = Color.BLACK;
	public String title = "My Fruits";
	
	protected void drawTitle(Graphics g, Chart chart) {

		Graphics2D g2d = (Graphics2D) g;

		FontMetrics fmT = chart.getFontMetrics(titleFont);
		int titleStringWidth = fmT.stringWidth(title);
		int titleStringHeight = fmT.getHeight();

		g2d.setFont(titleFont);
		int titleX = (chart.leftOffset + chart.rightOffset + chart.widthChart) / 2
				- titleStringWidth / 2;
		int titleY = chart.topOffset / 2 + titleStringHeight / 2;

		g2d.setColor(titleColor);
		g2d.drawString(title, titleX, titleY);

		// drawTrialVersion(g2d);
	}
}
