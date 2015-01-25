package com.bluewalrus.point;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.bluewalrus.chart.XYChart;

public abstract class AbstractBarPoint extends ComplexXYPoint{

    int barWidth = 10;
	
	public AbstractBarPoint(Color color, int size) {
		super(color, size);
	}

	public AbstractBarPoint(Color color) {
		super(color);
	}

	
	protected void drawText(XYChart chart, String name,
			Graphics g, int x) {
        
		FontMetrics fm = chart.getFontMetrics(chart.xAxis.axisCatFont);
        int widthStr = fm.stringWidth(name);
        int heightStr = fm.getHeight();
        
        g.setFont(chart.xAxis.axisCatFont);
        g.setColor(Color.BLACK);
        
        int xPosition = x - (widthStr / 2);
        int yPosition = chart.topOffset + chart.xAxis.marginOffset + chart.heightChart + chart.xAxis.labelOffset - heightStr / 2;
        
        //draw tick
        g.drawString(name, xPosition, yPosition);
    }
	
	protected void drawTickLine(Graphics g, XYChart chart, int x) {
		g.drawLine((int)(x),
		        (int)(chart.topOffset + chart.xAxis.marginOffset + chart.heightChart),
		        (int)(x),
		        (int)(chart.topOffset + chart.xAxis.marginOffset + chart.heightChart + 2));
	}

}
