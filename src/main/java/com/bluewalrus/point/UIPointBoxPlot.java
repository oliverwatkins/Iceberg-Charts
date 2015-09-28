package com.bluewalrus.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBoxPlot;
import com.bluewalrus.renderer.XYFactor;

public class UIPointBoxPlot extends UIPointComplexXY{

	public UIPointBoxPlot(Color color) {
		super(color);
	}

	public void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor) {

		DataPointBoxPlot dp = (DataPointBoxPlot)dataPoint;

		double pointYFactored = (point.y); //has already been factored
		
		double topWhiskerFactored = (dp.topWhisker * xyFactor.yFactor);
		double topFactored = (dp.top * xyFactor.yFactor);
		double middleFactored = (dp.middle * xyFactor.yFactor);
		double bottomFactored = (dp.bottom * xyFactor.yFactor);
		double bottomWhiskerFactored = (dp.bottomWhisker * xyFactor.yFactor);


		int firstQinPix = (int)(topWhiskerFactored - topFactored);
		int secondQinPix = (int)(topFactored - middleFactored);
		int thirdQinPix = (int)(middleFactored - bottomFactored);
		int fourthQinPix = (int)(bottomFactored - bottomWhiskerFactored);
		
		int width = 20;
		int y1 = (int)(pointYFactored - (firstQinPix + secondQinPix));
		int y2 = (int)(pointYFactored - (secondQinPix));
		int y3 = (int) pointYFactored;
		int y4 = (int)(pointYFactored + (thirdQinPix));
		int y5 = (int)(pointYFactored + (thirdQinPix + fourthQinPix));
		
		int x1 = (int)point.x - width/2;
		int x2 = (int)point.x;
		int x3 = (int)point.x + width/2;

        Color muchmuchdarker = color.darker().darker().darker(); 

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



}
