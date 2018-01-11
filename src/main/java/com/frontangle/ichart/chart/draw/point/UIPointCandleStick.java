package com.frontangle.ichart.chart.draw.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.DataPointBoxPlot;
import com.frontangle.ichart.chart.datapoint.DataPointCandleStick;

public class UIPointCandleStick  extends UIPointComplexXY{

	private int y1;
	private int y2;
//	private int y3;
	private int y4;
	private int y5;
	
	private double x1;
	private double x2;
	private double x3;
//	private Color color = Color.RED;
	private DataPointCandleStick dp;


	double width = 6;
	int widthPercent = 50;
	
	public UIPointCandleStick(Color color) {
		super(color);
	}
	
	public UIPointCandleStick(Color color, int widthPercent) {
		super(color);
		this.widthPercent = widthPercent;
	}

	public void draw(Graphics2D g, 
			Point point, 
			DataPoint dataPoint, 
			XYFactor xyFactor, 
			XYChart chart, 
			int pixBtnFirst2Pts) {

		double barWidth = 0;
		
    	if (widthPercent != 0) {
    		barWidth = ((widthPercent * (double)pixBtnFirst2Pts)/ 100.0); 
    	}else {
    		barWidth = 10; //default
    	}
    	
    	width = (int) barWidth;
    	
    	

		
		dp = (DataPointCandleStick)dataPoint;

		double topWhiskerFactored;
		double topFactored;
		double bottomFactored;
		double bottomWhiskerFactored;
		
		
		if (dp.filled && dp.open < dp.close) {
			throw new RuntimeException("dp.filled && dp.high < dp.close");
		}
		
		if (!dp.filled && dp.close < dp.open) {
			throw new RuntimeException("!dp.filled && dp.close < dp.high");
		}
		
		
		if (dp.filled) {
			topWhiskerFactored = point.y;
			topFactored = point.y + ((dp.high - dp.open) * xyFactor.getyFactor());
			bottomFactored = point.y + (dp.high - dp.close) * xyFactor.getyFactor();
			bottomWhiskerFactored = point.y + (dp.high - dp.low)  * xyFactor.getyFactor();
		}else {
			topWhiskerFactored = point.y;
			topFactored = point.y + ((dp.high - dp.close) * xyFactor.getyFactor());
			bottomFactored = point.y + (dp.high - dp.open) * xyFactor.getyFactor();
			bottomWhiskerFactored = point.y + (dp.high - dp.low)  * xyFactor.getyFactor();
		}
		

		y1 = (int)(point.y) ; 
		y2 = (int)topFactored ; 
		y4 = (int)bottomFactored ; 
		y5 = (int)bottomWhiskerFactored ; 
		
		x1 = point.x - width/2;
		x2 = (int)point.x;
		x3 = point.x + width/2;

        this.clipAndDrawPoint(g, chart);

        
	}
	
	
	@Override
	public void drawPoint(Graphics2D g) {
		
        g.setColor(color);

        //top
        g.drawLine((int)x2,
        		y1,
        		(int)x2,
                y2);
        
        g.drawRect((int)x1,
        		y2,
        		(int)(x3 - x1),
                y4 - y2);
        
		if (dp.filled) {
	        //top rect
	        g.fillRect((int)x1,
	        		y2,
	        		(int)(x3 - x1),
	                y4 - y2);
		}
        
        g.drawLine((int)x2,
        		y4,
        		(int)x2,
        		y5);
	}
	
	

	@Override
	public boolean doesShapeContainPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}





}
