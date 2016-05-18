package com.bluewalrus.chart.draw.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYFactor;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointBoxPlot;
import com.bluewalrus.chart.datapoint.DataPointCandleStick;

public class UIPointCandleStick  extends UIPointComplexXY{

	private int y1;
	private int y2;
//	private int y3;
	private int y4;
	private int y5;
	
	private int x1;
	private int x2;
	private int x3;
//	private Color color = Color.RED;
	private DataPointCandleStick dp;



	public UIPointCandleStick(Color color) {
		super(color);
	}

	public void draw(Graphics2D g, Point point, Point lastPoint, DataPoint dataPoint, XYFactor xyFactor, XYChart chart, int pixBtnFirst2Pts) {


		
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
			topFactored = point.y + ((dp.high - dp.open) * xyFactor.yFactor);
			bottomFactored = point.y + (dp.high - dp.close) * xyFactor.yFactor;
			bottomWhiskerFactored = point.y + (dp.high - dp.low)  * xyFactor.yFactor;
		}else {
			topWhiskerFactored = point.y;
			topFactored = point.y + ((dp.high - dp.close) * xyFactor.yFactor);
			bottomFactored = point.y + (dp.high - dp.open) * xyFactor.yFactor;
			bottomWhiskerFactored = point.y + (dp.high - dp.low)  * xyFactor.yFactor;
		}
		
		int width = 6;
		y1 = (int)(point.y) ; 
		y2 = (int)topFactored ; 
		y4 = (int)bottomFactored ; 
		y5 = (int)bottomWhiskerFactored ; 
		
		x1 = (int)point.x - width/2;
		x2 = (int)point.x;
		x3 = (int)point.x + width/2;

        this.clipAndDrawPoint(g, chart);

        
	}
	
	
	@Override
	public void drawPoint(Graphics2D g) {
		
        g.setColor(color);

        //top
        g.drawLine(x2,
        		y1,
        		x2,
                y2);
        
        g.drawRect(x1,
        		y2,
        		x3 - x1,
                y4 - y2);
        
		if (dp.filled) {
	        //top rect
	        g.fillRect(x1,
	        		y2,
	        		x3 - x1,
	                y4 - y2);
		}
        
        g.drawLine(x2,
        		y4,
        		x2,
        		y5);
	}
	
	

	@Override
	public boolean doesShapeContainPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}





}
