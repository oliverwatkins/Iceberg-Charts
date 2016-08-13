package com.bluewalrus.chart.axis;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.Orientation;
import com.bluewalrus.chart.draw.YAxisDrawUtil;
import com.bluewalrus.scaling.AxisScaling;

/**
 * @copyright 2014
 * @author Oliver Watkins (www.frontangle.com) All Rights Reserved
 */
public class YAxis extends Axis {



	public boolean rightSide = false;
	
    public YAxis(AxisScaling axisDraw, String name) {
        super(name, axisDraw);
        
        axisDraw.setOrientation(Orientation.Y);
    }
    
    @Override
    public void drawBorderLine(Graphics g, Chart chart) {
        int x1 = chart.leftOffset - marginOffset;
        int y1 = chart.topOffset;
        int x2 = x1; 
        int y2 = chart.topOffset + chart.heightChart;

        g.setColor(axisColor);
        g.drawLine(x1, y1, x2, y2);
    }
  
    public void drawLabel(Graphics g, Chart chart) {
    	YAxis axis = this;
    	
        Graphics2D g2d = (Graphics2D) g;
        
        YAxisDrawUtil.drawLabel(chart, axis, g2d);
    }
    
    public String getName() {
        return "Y Axis";
    }

	public void setMinValue(int val) {
		axisScaling.setMinValue(val);
	}

	public void setMaxValue(int val) {
		axisScaling.setMaxValue(val);
	}
	
    @Override
	public String toString() {
		return "YAxis [rightSide=" + rightSide + ", axisScaling=" + axisScaling
				+ ", labelText=" + labelText + ", getMinValue()="
				+ getMinValue() + ", getMaxValue()=" + getMaxValue() + "]";
	}
}
