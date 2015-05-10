package com.bluewalrus.renderer;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import com.bluewalrus.chart.Axis;
import com.bluewalrus.bar.Bar;
import com.bluewalrus.bar.MultiBar;
import com.bluewalrus.chart.XAxis;
import com.bluewalrus.chart.Chart;

public class BarRenderer {
	
    public static void drawBars(Graphics g, Chart chart, Axis yAxis, XAxis xAxis, ArrayList<Bar> bars, int barWidth) {

        int i = 0;
        int barNumber = bars.size();

        double pointDistance = (chart.widthChart / (barNumber + 1));
        double factor = ((double) chart.heightChart / (double) (yAxis.maxValue - yAxis.minValue));

        for (Bar bar : bars) {

            i++;

            int scaledBarHeight = (int) (bar.value * factor);

            double fromTop = 0;
            if (bar.value < 0 ) {
                fromTop = chart.topOffset + chart.heightChart + (yAxis.minValue * factor);
                scaledBarHeight = -scaledBarHeight;
            }else {
                fromTop = chart.topOffset + chart.heightChart - scaledBarHeight + (yAxis.minValue * factor);
            }
            
            double fromLeft = chart.leftOffset + (i * pointDistance) - (barWidth / 2);

            g.setColor(bar.color);
            
            g.fillRect((int)fromLeft, (int)fromTop, barWidth, scaledBarHeight);

            
            
            BarRenderer.drawTickLine(g, chart, i, pointDistance);
            
            
            BarRenderer.drawText(chart, xAxis, bar.name, g, i, (int)pointDistance);
        }
    }



	
	private static void drawTickLine(Graphics g, Chart chart, int i,
			double pointDistance) {
		g.drawLine((int)(chart.leftOffset + (i * pointDistance)),
		        (int)(chart.topOffset + chart.heightChart),
		        (int)(chart.leftOffset + (i * pointDistance)),
		        (int)(chart.topOffset + chart.heightChart + 2));
	}
	
	
	private static void drawText(Chart chart, XAxis xAxis, String name,
			Graphics g, int i, int pointDistance) {
        
		FontMetrics fm = chart.getFontMetrics(xAxis.axisCatFont);
        int widthStr = fm.stringWidth(name);
        int heightStr = fm.getHeight();
        
        g.setFont(xAxis.axisCatFont);
        g.setColor(Color.BLACK);
        
        int xPosition = chart.leftOffset + (i * pointDistance) - (widthStr / 2);
        int yPosition = chart.topOffset + chart.heightChart + xAxis.labelOffset - heightStr / 2;
        
        //draw tick
        g.drawString(name, xPosition, yPosition);
    }
}
