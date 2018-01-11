package com.frontangle.ichart.chart.draw.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.DataPointBoxPlot;

public class UIPointBoxPlot extends UIPointComplexXY{

	private int y1;
	private int y2;
	private int y3;
	private int y4;
	private int y5;
	private int x1;
	private int x2;
	private int x3;
	private Color muchmuchdarker;

	public UIPointBoxPlot(Color color) {
		super(color);
	}

	public void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor, XYChart chart, int pixBtnFirst2Pts) {

		DataPointBoxPlot dp = (DataPointBoxPlot)dataPoint;

		double pointYFactored = (point.y); //has already been factored
		
		double topWhiskerFactored = (dp.topWhisker * xyFactor.getyFactor());
		double topFactored = (dp.top * xyFactor.getyFactor());
		double middleFactored = (dp.middle * xyFactor.getyFactor());
		double bottomFactored = (dp.bottom * xyFactor.getyFactor());
		double bottomWhiskerFactored = (dp.bottomWhisker * xyFactor.getyFactor());


		int firstQinPix = (int)(topWhiskerFactored - topFactored);
		int secondQinPix = (int)(topFactored - middleFactored);
		int thirdQinPix = (int)(middleFactored - bottomFactored);
		int fourthQinPix = (int)(bottomFactored - bottomWhiskerFactored);
		
		int width = 20;
		y1 = (int)(pointYFactored - (firstQinPix + secondQinPix));
		y2 = (int)(pointYFactored - (secondQinPix));
		y3 = (int) pointYFactored;
		y4 = (int)(pointYFactored + (thirdQinPix));
		y5 = (int)(pointYFactored + (thirdQinPix + fourthQinPix));
		
		x1 = (int)point.x - width/2;
		x2 = (int)point.x;
		x3 = (int)point.x + width/2;

        muchmuchdarker = color.darker().darker().darker(); 

        
        this.clipAndDrawPoint(g, chart);
        
	}
	
	
	@Override
	public void drawPoint(Graphics2D g) {
		
        g.setColor(muchmuchdarker);

        //top
        g.drawLine(x2,
        		y1,
        		x2,
                y2);

        
        g.setColor(color);
        
        //top rect
        g.fillRect(x1,
        		y2,
        		x3 - x1,
                y3-y2);

        g.setColor(muchmuchdarker);

        //top rect
        g.drawRect(x1,
        		y2,
        		x3 - x1,
                y3-y2);
        
        g.setColor(color);
        
        //bottom rect
        g.fillRect(x1,
        		y3,
        		x3-x1,
                y4-y3);
        
        g.setColor(muchmuchdarker);
        
        //bottom rect
        g.drawRect(x1,
        		y3,
        		x3-x1,
                y4-y3);
  
        g.setColor(muchmuchdarker);
        
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
